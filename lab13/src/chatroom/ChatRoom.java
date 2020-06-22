package chatroom;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChatRoom {

	private Vector<ServerThread> serverThreads;
	public Vector<Lock> locks = new Vector<Lock>();
	public Vector<Condition> conditions = new Vector<Condition>();
	boolean onlyThread;
	
	public ChatRoom(int port) {
		try {
			System.out.println("Binding to port " + port);
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Bound to port " + port);
			serverThreads = new Vector<ServerThread>();
			while(true) {
				Socket s = ss.accept(); // blocking
				System.out.println("Connection from: " + s.getInetAddress());
				onlyThread = serverThreads.isEmpty();
				Lock lock = new ReentrantLock(); 
				Condition condition = lock.newCondition();
				locks.add(lock);
				conditions.add(condition);
				ServerThread st = new ServerThread(s, this, lock, condition, onlyThread);
				serverThreads.add(st);
			}		
		} catch (IOException ioe) {
			System.out.println("ioe in ChatRoom constructor: " + ioe.getMessage());
		}
	}
	
	public void broadcast(String message, ServerThread st) {
		if (message != null) {
			System.out.println(message);
			for(ServerThread threads : serverThreads){
				if (st != threads) {
					threads.sendMessage(message);
				}
			}
		}
	}

	public void callNextClient(ServerThread st) {
		int index = serverThreads.indexOf(st);
		++index;
		if(index == serverThreads.size()) {
			index = 0;
		}
		Lock nextLock = locks.get(index);
		Condition nextCondition = conditions.get(index);
		nextLock.lock();
		nextCondition.signal();
		nextLock.unlock();
	}
	
	public static void main(String [] args) {
		ChatRoom cr = new ChatRoom(6789);
	}
	

}
