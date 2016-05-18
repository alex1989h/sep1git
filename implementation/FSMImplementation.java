package implementation;
import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;
import fsm.IFSM;


public class FSMImplementation implements IFSM {
	private FSMState state;
	private IPump pumpA;
	private IPump pumpB;
	private IGate gate;
	private IOpticalSignals signals;
	private IHumiditySensor sensor;
	private IHumidifier humidifier;
	private IManualControl operatorPanel;
	private final double upperBound;
	private final double lowerBound;

	public FSMImplementation( IPump pumpA, IPump pumpB, IGate gate, IOpticalSignals signals,
			IHumidifier humidifier, IHumiditySensor sensor, IManualControl operatorPanel) {
		this.state = FSMState.HumidityOkay;
		this.pumpA = pumpA;
		this.pumpB = pumpB;
		this.gate = gate;
		this.signals = signals;
		this.sensor = sensor;
		this.humidifier = humidifier;
		this.operatorPanel = operatorPanel;
		upperBound = 60;
		lowerBound = 20;
	}
	
	@Override
	public void evaluate() {
		switch (state) {
			case HumidityOkay:
				System.out.println("Zustand: HumidityOkey");
				if(sensor.getHumidity() < lowerBound){
					signals.switchLampAOn();
					state=FSMState.UnderHumidityMin;
				}
				if(sensor.getHumidity() > upperBound){
					state=FSMState.OverHumidityMax;
				}
				break;
			case UnderHumidityMin:
				System.out.println("Zustand: UnderHumidityMin");
				humidifier.sendSprayOn();
				while(true){
					sleep();
					if(sensor.getHumidity()>=lowerBound){
						humidifier.sendSprayOff();
						signals.switchLampAOff();
						state=FSMState.HumidityOkay;
						break;
					}
				}
				break;
			case OverHumidityMax:
				break;
			case Pumpen:
				break;
			case Erfolg:
				break;
			case ERROR:
				break;
			case UnderHumidityMax:
				break;
			default:
				break;
			}
	}
	
	private void sleep(){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
