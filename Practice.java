class Hi extends Thread {
	public void run()  {
		for (int i = 0; i <= 5; i++) {
			try{Thread.sleep(1000);} catch (Exception e){}
			System.out.println("Hi");
		}
	}
}

class Hello extends Thread{
	public void run() {
		for (int i = 0; i <= 5; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}

			System.out.println("Hello");
		}
	}

}
public class Practice {

	public static void main(String[] args)  {
		Hi hi = new Hi();
		Hello hello = new Hello();
		
		
		
		hi.start();
		try{Thread.sleep(10);} catch (Exception e){}		
		hello.start();
		
//		hi.run();
//		hello.run();
		
//		hi.show();
//		hello.show();
//		
		

	}

}
