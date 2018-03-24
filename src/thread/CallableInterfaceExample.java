package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
/*
 * use java.util.concurrent.Callable interface if you expect your thread to return a value as well as throw a exception. 
 * This is the difference between runnanle & callable interface.
 * If implementing Runnable interface you have run method otherwise you have got call method. When you submit the callable object to executor framework, 
 * it returns java.util.concurrent.Future object. use this object to get the returned value from the call method
 * Below example creates 5 threads with 10 callables to them. Each callable returns the name of the thread. 
 * Since thread pool size id only 5 so only 5 callable can be executed.
 */
public class CallableInterfaceExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(5);
		
		List<Future<String>> list = new ArrayList<Future<String>>();
		
		for(int i=0;i<15;i++){
			Future<String> future = service.submit(new Task());
			list.add(future);
		}
		
		for(Future<String> future:list){
			try{
				System.out.println(future.get());
			}catch(Exception ex){
				
			}
		}
		
		service.shutdown();
	}

	private static class Task implements Callable{

		@Override
		public String call() throws Exception {
			Thread.sleep(1000);
			return (Thread.currentThread().getName());
		}	
		
	}
}
