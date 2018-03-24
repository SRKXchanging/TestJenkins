package thread;
import java.util.concurrent.CountDownLatch;
/*
 *  CountDownLatch is a utility which allows one or more threads to wait until a threshold point is reached.
 *  CountDownLatch works by having a counter initialized with number of threads, which is decremented each time a thread completes it's execution. When count reaches to 
 *  zero it means that all the threads have completed their execution and thread waiting on the latch resumes execution.  
 *  Two of it's imp methods - countDown - (decrease the counter) , await (keeps the thread waiting)
 *  It's limitation is that once the thread count reaches to zero it can not be re-started.
 *  Real life example :-
 *  An examiner wait for all the students to finish their task. Students do not wait for each others. Instead the examiner waits until the last student finishes.
 *  Usages :-----
 *  1) An application start-up class wants to ensure whether all N required systems are up & running before taking user request.
 *  2) Can be used to create a deadlock situation.
 *  3) To make a singleton class. Take the count as 1 & make all other threads to wait for the latch.
 */
public class CountDownLatchExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 
		final CountDownLatch latch= new CountDownLatch(3);
		
		Thread t1 = new Thread(new Task("T1", latch));
		Thread t2 = new Thread(new Task("T2",latch));
		Thread t3 = new Thread(new Task("T3", latch));
		
		//start all threads
		t1.start();
		t2.start();
		t3.start();
		
		try{
			latch.await();  // this keeps waiting the thread... till a threshold point is reached
		}catch(InterruptedException ex){
			System.out.println(ex);
		}
		
		//The system is ready to go
		System.out.println("All threads are ready....... Lets the game begin.....");
	}
	
	 public static class Task implements Runnable{
		private final String name;
		private final CountDownLatch latch;
		
		public Task(String name,CountDownLatch latch){
			this.name = name;
			this.latch = latch;
		}
		@Override
		public void run() {
			System.out.println(name + ".... Starting");	
			try{
				Thread.sleep(1000);	
			}catch(Exception ex){
				System.out.println(ex);
			}
			//Thread is ready. Now decrement the latch counter
			System.out.println(name + ".... Ready");	
			latch.countDown();  // this decreases the counter when the specified task is performed by the counter
		}
		
	}

}
