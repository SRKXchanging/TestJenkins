package thread;
import java.util.concurrent.ConcurrentLinkedQueue;
/*
 * ConcurrentLinkedQueue is a ideal choice when multiple threads working on a common collection. It doesn't allows null key & values.
 * ConcurrentLinkedQueue provides add/poll methods to add/remove element respectively
 * Below is an example describing producer/consumer pattern
 */
public class ConcurrentLinkedQueueEx {

	
	public static void main(String[] args) {
		final ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
		
		//create producer
		Thread t1 = new Thread(new Producer(queue));
		
		//create consumer
		Thread t2 = new Thread(new Consumer(queue));
		
		//start producer
		t1.start();
		
		//start consumer
		t2.start();
	}
	
	private static class  Producer implements Runnable{
		ConcurrentLinkedQueue<String> producerQueue;
		public Producer(ConcurrentLinkedQueue<String> queue){
			this.producerQueue = queue;
		}
		@Override
		public void run() {
			System.out.println("Producer started......");
			String str="";
			for(int i=0;i<5;i++){
				str = str.concat("Product : "+i);
				producerQueue.add(str);
				System.out.println("Producer Added : "+str);
				str = "";
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private static class  Consumer implements Runnable{
		ConcurrentLinkedQueue<String> consumerQueue;
		public Consumer(ConcurrentLinkedQueue<String> queue){
			this.consumerQueue = queue;
		}
		@Override
		public void run() {
			System.out.println("Consumer started......");
			String str;
			for(int i=0;i<5;i++){
				while((str = consumerQueue.poll())!=null){
					System.out.println("Consumer Removed : "+str);
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
