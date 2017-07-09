import java.util.Scanner;

class Processor extends Thread{
	private volatile boolean running = true;		// Adding volatile makes the value of running to always be read through the class
	public void run(){
		
		while(running){
			System.out.println("Hello");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {}
		}
	}
	
	public void shutdown(){
		running  = false;
	}
}
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Processor p1 = new Processor();
		p1.start();

		System.out.println("Press return to stop....");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		p1.shutdown();

	}

}
