package assignment4;

import java.io.*;
import java.util.*;

public class Hangman {
	
	
	public char[] result;
	public String name, answer, current;
	public int max, guesses;
	public boolean ready, playing;
	public Vector<ServerThread> players;
	
	public Hangman(String name, int max, String filename, ServerThread player) throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.max = max;
		this.guesses = 7;
		this.current = player.getUsername();
		this.players = new Vector<ServerThread>();
		this.addPlayer(player);
		File f = new File(filename);
		@SuppressWarnings("resource")
		Scanner input = new Scanner(f);
		Vector<String> list = new Vector<String>();
		while (input.hasNextLine()) {
			list.add(input.nextLine());
		}
		Random rand = new Random();
		int choice = rand.nextInt(list.size());
		this.answer = list.get(choice);
		this.result = answer.toCharArray();
		for (int i = 0; i < answer.length(); i++) {
			result[i] = '_';
		}
		ready = players.size() == max;
	}
	
	public boolean guessLetter(char letter) {
		if (answer.indexOf(letter) != -1) {
			for (int i = 0; i < answer.length(); i++) {
				if (answer.charAt(i) == letter) {
					result[i] = letter;
				}
			}
			return true;
		} else {
			guesses--;
			return false;
		}
	}
	
	public boolean guessWord(String word) {
		return answer.toLowerCase().contentEquals(word.toLowerCase());
	}
	
	public boolean addPlayer(ServerThread player) {
		if (players.size() < max) {
			players.add(player);
			ready = players.size() == max;
			return true;
		} else {
			ready = players.size() == max;
			return false;
		}	
	}
	
	public String nextPlayer(ServerThread st) {
		int i = players.indexOf(st);
		if (i  == players.size() - 1) {
			current = players.elementAt(0).getUsername();
		} else {
			current = players.elementAt(i + 1).getUsername();
		}
		return current;
	}
	
}
