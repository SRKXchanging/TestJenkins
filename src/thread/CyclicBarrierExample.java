package thread;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 *  Java program to demonstrate CyclicBarrier in java, which is a utility tool to control multiple threads. 
 *  It's just like CountDownLatch but you can restart the count again by calling the reset method. It has been added in java 1.5.
 *  Real life example :-
 *  If you are going to a picnic then you need to meet first at a common point from where all of you will begin the journey. 
 */
public class CyclicBarrierExample {
	
	public static void main(String args[]){
		// creating CyclicBarrier with 3 parties. i.e. three threads will be created and when each one reaches the barrier  the run method will be called
		 final CyclicBarrier cb = new CyclicBarrier(3, new Runnable() {
			
			@Override
			public void run() {
				//this task will be executed once all the threads reaches the barrier
				System.out.println("Now as all the parties has reached, lets start the game");				
			}
		});
		 
		Thread t1 = new Thread(new Task(cb), "Thread 1");
		Thread t2 = new Thread(new Task(cb), "Thread 2");
		Thread t3 = new Thread(new Task(cb), "Thread 3");
		
		t1.start();
		t2.start();
		t3.start();
		
		
	}
	
	//inner class which is static is called nested class
	private static class Task implements Runnable{
		
		private CyclicBarrier barrier;
		
		public Task(CyclicBarrier barrier){
			this.barrier = barrier;
		}

		@Override
		public void run() {
			try{
				System.out.println(Thread.currentThread().getName() + " is waiting for barrier");
				barrier.await(); //this call keeps the thread is waiting state until all the party reaches a specific point
				System.out.println(Thread.currentThread().getName() + " has crossed the barrier");
			}catch(InterruptedException ex){
				Logger.getLogger(CyclicBarrierExample.class.getName()).log(Level.SEVERE, null, ex);
			}catch(BrokenBarrierException ex){
				Logger.getLogger(CyclicBarrierExample.class.getName()).log(Level.SEVERE, null, ex);
			}
			
		}
		
	}
}















