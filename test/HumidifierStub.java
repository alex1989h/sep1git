package test;

import boundaryclasses.IHumidifier;

public class HumidifierStub implements IHumidifier {
	
	Spray spray;
	
	@Override
	public void sendSprayOn() {
		spray= new Spray();
		spray.start();
		System.out.println("Spray eingeschaltet");

	}

	@Override
	public void sendSprayOff() {
		spray.interrupt();
		try {
			spray.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		System.out.println("Spray ausgeschaltet");

	}

}
