//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class Worker {
//	
//	
//	private static Random random = new Random();
//	
//	private static List<Integer> list1 = new ArrayList<Integer>();
//	private static List<Integer> list2 = new ArrayList<Integer>();
//	
//	public static void stageOne(){
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		list1.add(random.nextInt(100));
//		
//	}
//	
//	public static void stageTwo(){
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		list2.add(random.nextInt(100));
//		
//		
//	}
//	
//	public static void process(){
//		for(int i = 0; i<1000; i++){
//			stageOne();
//			stageTwo();
//			
//		}
//		
//	}
//	
//	public static void main() {
//		System.out.println("Starting....");
//		
//		long start = System.currentTimeMillis();
//		
//		process();
//		
//		long stop = System.currentTimeMillis();
//		
//		System.out.println("Duration: " + (stop - start));
//		
//		System.out.println("List1: " + list1.size());
//		System.out.println("List1: " + list2.size());
//
//	}
//}




import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	
	private static volatile Random random = new Random();
	
//	private static   List<Integer> list1 = new ArrayList<Integer>();
//	private static  List<Integer> list2 = new ArrayList<Integer>();
	
	private static  List<Integer> list3 = new ArrayList<Integer>();
	private static  List<Integer> list4 = new ArrayList<Integer>();
	
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();

//	public static void stageOne(){
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		list1.add(random.nextInt(100));
//		
//	}
//	
//	public static void stageTwo(){
//		try {
//			Thread.sleep(1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		list2.add(random.nextInt(100));
//		
//		
//	}
	
	
	
	public static  void stage1(){
		synchronized (lock1){
			System.out.println("stage 1");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			list3.add(random.nextInt(100));
		}
		
	}
	
	public static  void stage2(){
		synchronized (lock2){
			System.out.println("stage 2");

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			list4.add(random.nextInt(100));
		}
		
	}
	
//	public static void process1(){
//		for(int i = 0; i<1000; i++){
//			stageOne();
//			stageTwo();
//			
//		}
//		
//	}
	
	public static  void process2(){
		for(int i = 0; i<1000; i++){
			stage1();
			stage2();			
		}	
	}
	
	public static void main() {
		Thread t1 = new Thread(new Runnable(){
			public void run(){				
				process2();
//				System.out.println("after");

				
			}
		}, "First Thread");
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){	
//				System.out.println("before");

				process2();	
			}
		}, "Second Thread");
		System.out.println("Starting....");
		
		
//		long start = System.currentTimeMillis();
//		
//		process1();
//		process1();
//		
//		long stop = System.currentTimeMillis();
//		
//		System.out.println("Duration Method calling: " + (stop - start));
		
		long begin = System.currentTimeMillis();
		t1.start();
		t2.start();
		
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		long end = System.currentTimeMillis();
		System.out.println("Duration Thread calling: " + (end - begin));

		

//		System.out.println("List1: " + list1.size());
//		System.out.println("List2: " + list2.size());
		
		System.out.println("List3: " + list3.size());
		System.out.println("List4: " + list4.size());
		

	}
}