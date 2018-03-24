package thread;
import java.util.concurrent.Exchanger;
/*
 * Exchanger is a utility class which is used to exchange objects between two threads. 
 * Both pair of threads enters into the exchange method with some objects and by calling the 
 * exchange method, they can exchange the objects between them.
 */
public class ExchangerExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Exchanger<String> exchanger = new Exchanger<String>();
		
		Thread t1 = new Thread(new Preferences("Coffee....", exchanger));
		Thread t2 = new Thread(new Preferences("Tea....", exchanger));
		
		t1.start();
		t2.start();
	}
	
	public static class Preferences implements Runnable{
		private String preference;
		private Exchanger<String> exchangeObj;
		
		public Preferences(String pref,Exchanger<String> exch){
			this.preference = pref;
			this.exchangeObj = exch;
		}
		@Override
		public void run() {
			System.out.println("Before exchange-----");
			System.out.println(Thread.currentThread().getName() + " likes "+ preference);
			try {
				preference = exchangeObj.exchange(preference);   //this call exchanges the objects between the threads.
			} catch (InterruptedException e){
				e.printStackTrace();
			}
			System.out.println("After exchange-----");
			System.out.println(Thread.currentThread().getName() + " likes "+ preference);
		}
		
	}

}
