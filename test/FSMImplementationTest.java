package test;
/**
 * Test Framework for testing the FSM from Practice 3
 * @author Thomas Lehmann
 * @version 2015-11-18
 */
import static org.junit.Assert.*;
import fsm.IFSM;
import implementation.FSMImplementation;
import implementation.FSMState;

import org.junit.Before;
import org.junit.Test;

import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;

public class FSMImplementationTest {
	private PumpStub pumpA;
	private PumpStub pumpB;
	private GateStub gate;
	private OpticalSignalsStub signals;
	private HumiditySensorStub sensor;
	private HumidifierStub humidifier;
	private ManualControlStub operatorPanel;
	private TimerStub time;
	private IFSM uut;
	

	@Before
	public void testSetup(){
		pumpA = new PumpStub();
		pumpB = new PumpStub();
		gate = new GateStub();
		signals = new OpticalSignalsStub();
		sensor = new HumiditySensorStub();
		humidifier = new HumidifierStub();
		operatorPanel = new ManualControlStub();
		time = new TimerStub();
		uut = new FSMImplementation(  pumpA,  pumpB,  gate,  signals,
				humidifier,  sensor,  operatorPanel, time) ;
	}
	
	@Test
	public void testPath() {
		int [] humidity = {19, 21, 61, 61, 61, 59, 59, 59};
		time.startTime(6);
		for (int i = 0; i < 8; i++){
			HumiditySensorStub.humidity= humidity[i];
			uut.evaluate();
		}
		
	}

}
