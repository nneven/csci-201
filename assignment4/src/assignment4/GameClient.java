package assignment4;

import java.io.*;
import java.util.*;
import java.net.Socket;

public class GameClient extends Thread {
	
	private PrintWriter pw;
	private BufferedReader br;
	
	public GameClient(String[] config, Scanner input) {
		try {
			System.out.println("Trying to connect to server...");
			@SuppressWarnings("resource")
			Socket s = new Socket(config[0], Integer.parseInt(config[1]));
			System.out.println("Connected!");
			this.pw = new PrintWriter(s.getOutputStream());
			this.br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw.println(config[5]);
			pw.println(config[2]);
			pw.flush();
			this.start();
			while(true) {
				String line = input.nextLine();
				pw.println(line);
				pw.flush();
			}

		} catch (IOException ioe) {
			System.out.println("Unable to connect to server");
		}
	}
	public void run() {
		try {
			while(true) {
				String line = br.readLine();
				System.out.println(line);
			}
		} catch (IOException ioe) {
			System.out.println("ioe in GameClient.run(): " + ioe.getMessage());
		}
		
	}
	public static void main(String [] args) {
		System.out.print("Welcome to Hangman!");
		String[] config = new String[6];
		Scanner input = new Scanner(System.in);
		boolean success = false;
		while (!success) {
			try {
				System.out.print("\nWhat is the name of your configuration file? ");
				File f = new File(input.nextLine());
				@SuppressWarnings("resource")
				Scanner line = new Scanner(f);
				for (int i = 0; i < 6; i++) {
					if (line.hasNextLine()) {
						config[i] = line.nextLine().split("=", 2)[1];
					} else {
						System.out.println("ERROR: Incorrect file format");
						success = false;
						break;
					}
					success = true;
				}
			} catch (FileNotFoundException e) {
				System.out.println("ERROR: File not found");
				success = false;
			}
		}
		
		System.out.println("\nSUCCESS:");
		System.out.println("Server Hostname - " + config[0]);
		System.out.println("Server Port - " + config[1]);
		System.out.println("Database Connection String - " + config[2]);
		System.out.println("Database Username - " + config[3]);
		System.out.println("Database Password - " + config[4]);
		System.out.println("Secret Word File - " + config[5]);
		@SuppressWarnings("unused")
		GameClient gc = new GameClient(config, input);
	}
}
