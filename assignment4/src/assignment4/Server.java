package assignment4;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
	
	private Map<String, Hangman> games;
	private Vector<ServerThread> serverThreads;
	
	public Server(int port) {
		// to do --> implement your constructor
		try {
			System.out.println("Binding to port " + port);
			@SuppressWarnings("resource")
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Bound to port " + port);
			games = new HashMap<String, Hangman>();
			serverThreads = new Vector<ServerThread>();
			while(true) {
				Socket s = ss.accept(); // blocking
				System.out.println("Connection from: " + s.getInetAddress());
				ServerThread st = new ServerThread(s, this);
				serverThreads.add(st);
			}
		} catch (IOException ioe) {
			System.out.println("ioe in Server constructor: " + ioe.getMessage());
		}
	}
	
	public static void main(String [] args) {
		// to do --> implement your main()
		System.out.println("Welcome to the Hangman Server");
		@SuppressWarnings("unused")
		Server server = new Server(6789);
	}
	
	public void log(String message) {
		System.out.println(message);
	}
	
	public void broadcast(String name, String message) {
		for(ServerThread threads : games.get(name).players) {
			threads.sendMessage(message);
		}
	}
	
	public void send(String name, String message, ServerThread st) {
		for(ServerThread threads : games.get(name).players) {
			if (st != threads) threads.sendMessage(message);
		}
	}
	
	public Hangman getGame(String name) {
		return games.get(name);
	}
	
	public void createGame(String name, int size, String file, ServerThread player) {
		try {
			Hangman h = new Hangman(name, size, file, player);
			games.put(name, h);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public boolean addPlayer(String name, ServerThread player) {
		return games.get(name).addPlayer(player);
	}
	
	public boolean userExists(String name, ServerThread player) {
		for (ServerThread st : serverThreads) {
			if (st != player && st.getUsername().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
