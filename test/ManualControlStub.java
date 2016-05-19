package test;

import boundaryclasses.IManualControl;

public class ManualControlStub implements IManualControl {

	@Override
	public boolean receivedAcknowledgement() {
		System.out.println("MANUELLE EINGABE ist erfolgt");
		return true;
	}

}
