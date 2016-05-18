package test;

public class Spray extends Thread{
	@Override
	public void run() {
		while(!isInterrupted()){
			HumiditySensorStub.humidity++;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}
