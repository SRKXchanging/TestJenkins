package thread;

import java.util.concurrent.*;
import java.util.concurrent.Phaser;

/*
 * A reusable synchronization barrier, similar in functionality to CyclicBarrier & CountDownLatch but with some extended functionality.
 * This class is added in JDK 1.7 only!
 * Read this link for details - https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/Phaser.html
 */
public class PhaserExample {

	
	public static void main(String[] args) {
		
		Phaser phaserObj = new Phaser();
		
		Thread t1 = new Thread(new Task(phaserObj, 1000));
		Thread t2 = new Thread(new Task(phaserObj, 1000));
		Thread t3 = new Thread(new Task(phaserObj, 1000));
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	private static class Task implements Runnable{
		Phaser phaser;
		int sleep;
		public Task(Phaser phaser,int sleep){
			this.phaser = phaser;
			this.sleep = sleep;
		}
		
		@Override
		public void run() {
			phaser.register();
			System.out.println(Thread.currentThread().getName() + " begin");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName() + " in middle");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " end");
		}
		
	}

}
/*
 * Output :--------------------------------------
 * Thread-0 begin
 * Thread-1 begin
 * Thread-2 begin
 * Thread-0 in middle
 * Thread-1 in middle
 * Thread-2 in middle
 * Thread-0 end
 * Thread-1 end
 * Thread-2 end
 * ---------------------------------------------------
 */



