import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageTest {

	public MessageTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MessageQueue mq = new MessageQueue();
		Messenger m = new Messenger(mq);
		Subscriber s = new Subscriber(mq);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(m);
		executor.execute(s);
		executor.shutdown();
		while (!executor.isTerminated()) {
			Thread.yield();
		}
		System.out.println("Finished");
	}

}
