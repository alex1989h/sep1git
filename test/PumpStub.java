package test;

import boundaryclasses.IPump;

public class PumpStub implements IPump {
	private boolean pumpAn=false;
	@Override
	public void sendActivate() {
		pumpAn=true;
		System.out.println("Pumpe wird aktiviert");

	}

	@Override
	public void sendDeactivate() {
		pumpAn=false;
		System.out.println("Pumpe wird deaktiviert");

	}

	@Override
	public boolean receivedActivated() {
		// TODO Auto-generated method stub
		return pumpAn;
	}

}
