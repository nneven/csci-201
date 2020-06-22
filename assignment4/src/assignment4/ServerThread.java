package assignment4;
import java.io.*;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class ServerThread extends Thread{
	
	private String username, password, game, file;
	private int wins, losses;
	private PrintWriter pw;
	private BufferedReader br;
	@SuppressWarnings("unused")
	private Socket socket;
	private Server server;
	
	public ServerThread(Socket sock, Server serv) {
		try {
			this.socket = sock;
			this.server = serv;
			pw = new PrintWriter(sock.getOutputStream());
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			this.start();
			
		} catch (IOException ioe) {
			System.out.println("ioe in ServerThread constructor: " + ioe.getMessage());
		}
	}

	public void sendMessage(String message) {
		pw.println(message);
		pw.flush();
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getWins() {
		return wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public void run() {

		try {
			file = br.readLine().trim();
			String url = br.readLine().trim();
			java.sql.Connection conn = null;
			java.sql.ResultSet rs = null;
			java.sql.Statement st = null;
			
			boolean login = false;
			while (!login) {		
				
				server.log("Trying to connect to database... ");
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(url);
				server.log("Connected!");
				sendMessage("\nUsername: ");
				username = br.readLine().trim();
				sendMessage("Password: ");
				password = br.readLine().trim();
				server.log(username + " - trying to log in with password " + password);
				st = conn.createStatement();
				rs = st.executeQuery("SELECT * FROM User WHERE username = '" + username + "' AND password = '" + password + "';");
				if (!rs.next()) {
					server.log(username + " - does not have an account so not successully logged in");
					sendMessage("No account exists with those credentials.");
					sendMessage("Would you like to create a new account (Yes/No) ? ");
					pw.flush();
					if (br.readLine().trim().toUpperCase().equals("YES")) {
						server.log(username + " - created an acount with password " + password);
						st.executeUpdate("INSERT into User (username, password, wins, losses)" + " VALUES ('" + username + "', '" + password + "', 0, 0);");
						rs = st.executeQuery("SELECT * FROM User WHERE username = '" + username + "' AND password = '" + password + "';");
						login = true;
					} else {
						sendMessage("Retry login.");
					}
				} else {
					if (server.userExists(username, this)) {
						sendMessage("User already online - Retry login");
					} else {
						login = true;
					}
				}
			}
			wins = rs.getInt("wins");
			losses = rs.getInt("losses");
			server.log(username + " - successfully logged in");
			server.log(username + " - has record " + wins + " and " + losses + " losses");
			pw.println("\nGreat! You are now logged in as " + username);
			pw.println(username + "'s Record");
			pw.println("--------------------");
			pw.println("Wins - " + wins);
			pw.println("Losses - " + losses);
			pw.flush();
			
			String response;
			while (true) {
				pw.println("1) Start a game");
				pw.println("2) Join a game");
				pw.println("Would you like to start a game or join a game (1/2) ?");
				pw.flush();
				response = br.readLine().trim();
				if (response.equals("1") || response.equals("2")) {
					sendMessage("What is the name of the game?");
					game = br.readLine().trim();
					break;
				} else {
					sendMessage("Please enter a valid choice");
				}
			}
			
			if (response.equals("1")) {
				while (true) { // CREATE GAME
					server.log(username + " - wants to start a game called " + game);
					if (server.getGame(game) == null) {
						break;
					} else {
						server.log(username + " - " + game + " already exists, so unable to start game " + game);
						sendMessage(game + " already exists");
						sendMessage("Please enter a valid choice");
						sendMessage("What is the name of the game?");
						game = br.readLine().trim();
					}
				}
				while (true) { // # PLAYERS
					sendMessage("How many players will be playing (1-4) ?");
					int size = Integer.parseInt(br.readLine().trim());
					if (0 < size && size < 5) {
						server.log(username + " - successfully started game " + game);
						server.createGame(game, size, file, this);
						break;
					} else {
						sendMessage("Please enter a valid choice");
					}
				}
			} else {
				while (true) { // JOIN GAME
					server.log(username + " - wants to join a game called " + game);
					if (server.getGame(game) != null) {
						if (server.addPlayer(game, this)) {
							server.log(username + " - successfully joined game " + game);
							server.addPlayer(game, this);
							for (int i = 0; i < server.getGame(game).players.size(); i++) {
								if (server.getGame(game).players.elementAt(i) != this) {
									pw.println("User " + server.getGame(game).players.elementAt(i).getUsername() + " is in the game");
									pw.println(server.getGame(game).players.elementAt(i).getUsername()  + "'s Record");
									pw.println("--------------------");
									pw.println("Wins - " + server.getGame(game).players.elementAt(i).getWins());
									pw.println("Losses - " + server.getGame(game).players.elementAt(i).getLosses());
									pw.flush();
								}
							}
							server.send(game, "User " + username + " is in the game", this);
							server.send(game, username + "'s Record", this);
							server.send(game, "--------------------", this);
							server.send(game, "Wins - " + wins, this);
							server.send(game, "Losses - " + losses, this);
							server.send(game, "", this);
							break;
						} else {
							server.log(username + " - " + game + " exists, but " + username + " is unable to join because the maximum number of playesr have already joined " + game);
							sendMessage("The game " + game + " does not have space for another user to join");
						}
					} else {
						sendMessage("There is no game with name " + game);
					}
					sendMessage("Please enter a valid choice");
					sendMessage("What is the name of the game?");
					game = br.readLine().trim();
				}
			}
			
			if (!server.getGame(game).ready) {
				server.log(username + " - " + game + " needs " + server.getGame(game).max + " to start game");
				sendMessage("Waiting for " + (server.getGame(game).max - 1) + " other user(s) to join...");
				while (!server.getGame(game).ready) {
					// WAIT UNTIL GAME IS READY
				}
			}
			TimeUnit.SECONDS.sleep(1);
			server.log(username + " - " + game + " has " + server.getGame(game).max + " so starting game with secret word " + server.getGame(game).answer);
			sendMessage("All users have joined");
			sendMessage("Determining secret word...");
			server.getGame(game).playing = true;
			while (server.getGame(game).playing) {
				
				if (server.getGame(game).current.equals(username)) {
					TimeUnit.SECONDS.sleep(1);
					server.broadcast(game, "Secret Word: " + String.valueOf(server.getGame(game).result));
					server.broadcast(game, "You have " + server.getGame(game).guesses + " incorrect guesses remaining");
					server.send(game, "Waiting for " + username + " to do something...", this);
					sendMessage("1) Guess a letter");
					sendMessage("2) Guess the word");
					sendMessage("What would you like to do (1/2) ?");
					String choice = br.readLine();
					if (choice.equals("1")) {
						while (true) {
							sendMessage("Letter to guess:");
							String letter = br.readLine().trim();
							if (letter.length() == 1) {
								server.log(game + " " + username + " - guessed letter " + letter);
								server.send(game, username + " has gussed the letter " + letter, this);
								if (server.getGame(game).guessLetter(letter.charAt(0))) {
									server.log(game + " " + username + " - " + letter + " is in " + server.getGame(game).answer + " in position(s) " + server.getGame(game).answer.indexOf(letter));
									server.log("Secret word now shows " + String.valueOf(server.getGame(game).result));
									server.broadcast(game, "The letter '" + letter + "' is in the secret word");
								} else {
									server.log(game + " " + username + " - " + letter + " is not in " + server.getGame(game).answer);
									server.log(game + " now has " + server.getGame(game).guesses + " guesses remaining");
									server.broadcast(game, "The letter '" + letter + "' is not in the secret word");
								}
								break;
							} else {
								sendMessage("That is not a valid option");
							}
						}
					} else if (choice.equals("2")){
						sendMessage("What is the secret word?");
						String word = br.readLine().trim();
						server.log(game + " " + username + " - guess word " + word);
						server.send(game, username + " has guessed the word " + word, this);
						if (server.getGame(game).guessWord(word)) {
							sendMessage("That is correct! You win!");
							server.log(game + " " + username + " - " + word + " is correct");
							server.log(username + " wins game and other players have lost the game");
							server.send(game, username + " guessed the word correctly. You lose!", this);
							st.executeUpdate("UPDATE User SET wins = "  + (wins + 1) + " WHERE username = '" + username + "';");
							server.getGame(game).playing = false;
						} else {
							sendMessage("That is incorrect! You lose!");
							sendMessage("The word was '" + server.getGame(game).answer + "'");
							server.log(game + " " + username + " - " + word + " is incorrect");
							server.log(username + " has lost and is no longer in the game");
							server.send(game, username + " guessed the word correctly. They lost!", this);
							st.executeUpdate("UPDATE User SET losses = "  + (losses + 1) + " WHERE username = '" + username + "';");
							if (server.getGame(game).players.size() == 1) {
								server.getGame(game).playing = false;
							} else {
								server.getGame(game).players.remove(this);
							}
						}
					} else {
						sendMessage("Please enter a valid option");
					}
					if (server.getGame(game).guesses == 0) {
						server.broadcast(game, "You have 0 guesses remaining. You lose!");
						server.getGame(game).playing = false;
						st.executeUpdate("UPDATE User SET losses = " + (losses + 1) + " WHERE username = '" + username + "';");
					}
					server.getGame(game).nextPlayer(this);
				}
				
			}
			rs = st.executeQuery("SELECT * FROM User WHERE username = '" + username + "' AND password = '" + password + "';");
			rs.next();
			TimeUnit.SECONDS.sleep(1);
			server.broadcast(game, username + "'s Record");
			server.broadcast(game, "--------------------");
			server.broadcast(game, "Wins - " + rs.getInt("wins"));
			server.broadcast(game, "Losses - " + rs.getInt("losses"));
			server.broadcast(game, "");
			TimeUnit.SECONDS.sleep(1);
		} catch (SQLException | ClassNotFoundException e) {
			server.log("Unable to connect to database");
			e.printStackTrace();
		} catch (IOException ioe) {
			server.log("ioe in ServerThread.run(): " + ioe.getMessage());
		} catch (InterruptedException e) {
			server.log("InterruptedException in ServerThread.run(): " + e.getMessage());
		}
		
		sendMessage("Thank you for playing Hangman!");
	}
}
