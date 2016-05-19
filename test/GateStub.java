package test;

import boundaryclasses.IGate;

public class GateStub implements IGate {
	private boolean gateClosed = false;
	
	@Override
	public void sendCloseGate() {
		gateClosed = true;
		System.out.println("Gate wird geschlossen");

	}

	@Override
	public void sendOpenGate() {
		gateClosed = false;
		System.out.println("Gate wird geöffnet");

	}

	@Override
	public boolean receivedGateClosed() {
		System.out.println("Gate ist geschlossen");
		return gateClosed;
	}

	@Override
	public boolean receivedGateOpen() {
		System.out.println("Gate ist geöffnet");
		return !gateClosed;
	}

}
