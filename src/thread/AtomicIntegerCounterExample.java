package thread;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
 * In multi threading environment some times it happens that even if you declare a counter as volatile/or use a synchronize block, 
 * if concurrent threads are updating it then there are chances of error. To avoid such cases where counter needs to be updated 
 * automatically AtomicInterger is used. An AtomicCounter has an integer value that is automatically incremented. 
 */
public class AtomicIntegerCounterExample {

	public static void main(String[] args) throws InterruptedException{
		
		final Counter count = new Counter();
		
		List<Task> threadList = new ArrayList<Task>();
		
		for(int i=0;i<5;i++){
			threadList.add(new Task(count));
		}
		
		//start all threads
		Iterator<Task> iter1 = threadList.iterator();
		while(iter1.hasNext()){
			Task taskObj = (Task)iter1.next();
			taskObj.start();
		}
		//wait for all threads to finish
		Iterator<Task> iter2 = threadList.iterator();
		while(iter2.hasNext()){
			Task taskObj = (Task)iter2.next();
			taskObj.join();
		}
		
		System.out.println("Final count : "+ count.getCount());
	}
	
	private static class Task extends Thread{
		Counter counter;
		public Task(Counter counter){
			this.counter = counter;
		}
		@Override
		public void run() {
			for(int i=0;i<5;i++){
				counter.incrementCount();
			}
			
		}
		
	}
	
	private static class Counter{
		private AtomicInteger count = new AtomicInteger(0);
		 
		public void incrementCount(){
			count.incrementAndGet();
		}
		
		public int getCount(){
			return count.get();
		}
	}
	
	
}
