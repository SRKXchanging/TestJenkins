package thread;
import java.util.concurrent.Semaphore;
/*
 * Semaphore is another thread concurrency management mechanism where consumer threads have to wait until producer thread produces the material. In below example 4 threads
 * are there with two semaphore. So two threads have to wait until the semaphore is released. This the best suitable mechanism for creating thread pool.
 */
public class SemaphoreExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		  
		final Semaphore sp  = new Semaphore(1);
		
		Thread t1 = new Thread(new Task(sp));
		Thread t2 = new Thread(new Task(sp));
		Thread t3 = new Thread(new Task(sp));
		Thread t4 = new Thread(new Task(sp));
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
	private static class Task implements Runnable{
		Semaphore spObj;
		public Task(Semaphore sp){
			this.spObj = sp;
		}
		@Override
		public void run() {
			try {
				spObj.acquire();
				System.out.println(Thread.currentThread().getName() + " has acquired semaphore.");
				try{
					//Thread.sleep(3000);
				}catch(Exception ex){}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				spObj.release();
				System.out.println(Thread.currentThread().getName() + " has released semaphore.");
			}
			
		}
		
	}

}
