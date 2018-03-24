package thread;
import java.util.concurrent.ThreadFactory;
/*
 * An implementation of ThreadFactory pattern provides some configurable options for the threads it creates.
 * A ThreadFatory is implicitly/ by default used by the ExecutorService to create the thread & perform the task.
 * The configurable options that it provides are :-
 * 1. Name pattern for the thread can be specified. Which will be helpful while debugging.
 * 2. A flag can be specified whether the thread is demon thread or not.
 * 3. Priority of the thread can be specified.
 * 4. The UncaughtExceptionHandler for the threads can be specified. This handler is called if any exception occurs in the middle of execution.
 * 
 * Example :-
 * 
 * BasisThreadFactory factory = new BasicThreadFactory.Builder()
 * .namingPattern("workerthread-%d")
 * .daemon(true)
 * .priority(Thread.MAX_PRIORITY)
 * .build();
 * 
 * ExceutorService executor = Executors.newSingleThreadExcutor(factory);
 */
public class ThreadFactoryExample implements ThreadFactory{

	
	public static void main(String[] args) {
		ThreadFactoryExample tfe = new ThreadFactoryExample();
		Thread t1 = new Thread(new Task());
		t1.start();
	}
	
	private static class Task implements Runnable{

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " is under execution.");
			
		}
		
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r);
	}

}
