package thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/*
 * BlockingQueue provides put/take method for adding/removing elements respectively.
 * Producer-Consumer example demonstrated using BlockingQueue......
 * In this example the producer produces the goods and consumer takes it. The producer can't produce more if the bucket is full, 
 * while the consumer can't consume if the bucket is empty.
 * It comes in two flavour :-
 * 1) ArrayBlockingQueue  (A bounded blocking queue backed by an array(fixed size). Process elements in FIFO list)
 * 2) LinkedBlockingQueue (An optionally bounded blocking queue based on linked nodes. Process elements in FIFO list)
 */
public class BlockingQueueExample {

	public static void main(String[] args) {
		
		 BlockingQueue<String> bq = new ArrayBlockingQueue<String>(2);
		 
		 Thread producerThread = new Thread(new Producer(bq));
		 Thread consumerThread = new Thread(new Consumer(bq));
		 
		 System.out.println("starting producer...");
		 producerThread.start();
		System.out.println("starting consumer...");
		 consumerThread.start();
	}

	//nested class
	private static class Producer implements Runnable{
		private BlockingQueue bqObj;
		public Producer(BlockingQueue bq){
			this.bqObj = bq;
		}
		@Override
		public void run() {
			try {
				System.out.println("Tea produced....");
				bqObj.put("Tea");
				Thread.sleep(1000);
				System.out.println("Coffee produced....");
				bqObj.put("Coffee");
				Thread.sleep(1000);
				System.out.println("Dosa produced....");
				bqObj.put("Dosa");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	private static class Consumer implements Runnable{
		private BlockingQueue bqObj;
		public Consumer(BlockingQueue bq){
			this.bqObj = bq;
		}
		@Override
		public void run() {
			try {
				System.out.println(bqObj.take()+ " .. consumed");
				System.out.println(bqObj.take()+ " .. consumed");
				System.out.println(bqObj.take()+ " .. consumed");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
