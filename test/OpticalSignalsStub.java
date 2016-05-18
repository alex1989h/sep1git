package test;

import boundaryclasses.IOpticalSignals;

public class OpticalSignalsStub implements IOpticalSignals {

	@Override
	public void switchLampAOn() {
		System.out.println("Lampe A eingeschaltet");

	}

	@Override
	public void switchLampAOff() {
		System.out.println("Lampe A ausgeschaltet");

	}

	@Override
	public void switchLampBOn() {
		System.out.println("Lampe B eingeschaltet");

	}

	@Override
	public void switchLampBOff() {
		System.out.println("Lampe B ausgeschaltet");

	}

}
