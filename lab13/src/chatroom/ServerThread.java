package chatroom;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerThread extends Thread {

	private PrintWriter pw;
	private BufferedReader br;
	private ChatRoom cr;
	private Lock lock;
	private Condition condition;
	boolean onlyThread;

	
	public ServerThread(Socket s, ChatRoom cr, Lock lock, Condition condition, boolean onlyThread) {
		try {
			this.onlyThread = onlyThread;
			this.cr = cr;
			this.lock = lock;
			this.condition = condition;
			pw = new PrintWriter(s.getOutputStream());
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			this.start();
		} catch (IOException ioe) {
			System.out.println("ioe in ServerThread constructor: " + ioe.getMessage());
		}
	}

	public void sendMessage(String message) {
		pw.println(message);
		pw.flush();
	}
	
	public void run() {
		try {
			
			while(true) {
				lock.lock();
				if(!onlyThread) {
					condition.await();
					onlyThread = false;
				}
				String line = "It is now your turn to send messages";
				sendMessage(line);
				line = br.readLine();
				while(!line.contains("END_OF_MESSAGE")) {
					cr.broadcast(line, this);
					line = br.readLine();
				}
				sendMessage("You now must wait until your next turn.");
				cr.callNextClient(this);
				lock.unlock();
			}
		} catch (IOException ioe) {
			System.out.println("ioe in ServerThread.run(): " + ioe.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
