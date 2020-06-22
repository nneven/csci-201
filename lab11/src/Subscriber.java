public class Subscriber extends Thread {

	private Util u;
	private MessageQueue mq;
	
	public Subscriber(MessageQueue mq) {
		// TODO Auto-generated constructor stub
		this.mq = mq;
	}
	
	public void run() {
		int count = 0;
		while (count < 20) {
			String m = mq.getMessage();
			if (m != null) {
				count++;
				System.out.println("Read message: " + count + " at time " + u.getCurrentTime());
			} else {
				System.out.println("No Message");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				System.out.println("Interrupted");
				return;
			}
		}
	}

}
