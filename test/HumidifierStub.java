package test;

import boundaryclasses.IHumidifier;

public class HumidifierStub implements IHumidifier {
	
	@Override
	public void sendSprayOn() {
		System.out.println("Spray eingeschaltet");

	}

	@Override
	public void sendSprayOff() {
		System.out.println("Spray ausgeschaltet");
		
	}

}
