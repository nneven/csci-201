public class Messenger extends Thread {

	private Util u;
	private MessageQueue mq;
	
	public Messenger(MessageQueue mq) {
		// TODO Auto-generated constructor stub
		this.mq = mq;
	}
	
	public void run() {
		for (int i = 0; i < 20; i++) {
			mq.addMessage("Message: " + i);
			System.out.println("Insert message: " + i + " at time " + u.getCurrentTime());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				System.out.println("Interrupted");
				return;
			}
		}
	}

}
