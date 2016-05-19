package implementation;
import test.TimerStub;
import boundaryclasses.IGate;
import boundaryclasses.IHumidifier;
import boundaryclasses.IHumiditySensor;
import boundaryclasses.IManualControl;
import boundaryclasses.IOpticalSignals;
import boundaryclasses.IPump;
import boundaryclasses.ITimer;
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
	private ITimer time;
	private final double upperBound;
	private final double lowerBound;

	public FSMImplementation( IPump pumpA, IPump pumpB, IGate gate, IOpticalSignals signals,
			IHumidifier humidifier, IHumiditySensor sensor, IManualControl operatorPanel,ITimer time) {
		this.state = FSMState.HumidityOkay;
		this.pumpA = pumpA;
		this.pumpB = pumpB;
		this.gate = gate;
		this.signals = signals;
		this.sensor = sensor;
		this.humidifier = humidifier;
		this.operatorPanel = operatorPanel;
		this.time = time;
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
				if(sensor.getHumidity()>=lowerBound){
					humidifier.sendSprayOff();
					signals.switchLampAOff();
					state=FSMState.HumidityOkay;
					break;
				}
				break;
			case OverHumidityMax:
				System.out.println("Zustand: OverHumidityMax");
				signals.switchLampBOn();
				gate.sendCloseGate();
				if(gate.receivedGateClosed()){
					state=FSMState.Pumpen;
					signals.switchLampBOff();
				}
				break;
			case Pumpen:
				System.out.println("Zustand: Pumpen");
				pumpA.sendActivate();
				pumpB.sendActivate();
				if(time.isTimerExpired()&&pumpA.receivedActivated() && pumpB.receivedActivated()){
						state=FSMState.Erfolg;
				}else{
					state = FSMState.ERROR;
				}
				break;
			case Erfolg:
				System.out.println("Zustand: Erfolg");
				if(sensor.getHumidity()<=upperBound){
					state=FSMState.UnderHumidityMax;
				}
				break;
			case ERROR:
				System.out.println("Zustand: ERROR");
				pumpA.sendDeactivate();
				pumpB.sendDeactivate();
				signals.switchLampBOn();
				gate.sendOpenGate();
				if(gate.receivedGateOpen() && operatorPanel.receivedAcknowledgement()){
					signals.switchLampBOff();
					state=FSMState.HumidityOkay;
				}
				break;
			case UnderHumidityMax:
				System.out.println("Zustand: UnderHumidityMax");
				pumpA.sendDeactivate();
				pumpB.sendDeactivate();
				signals.switchLampBOn();
				gate.sendOpenGate();
				if(gate.receivedGateOpen()){
					signals.switchLampBOff();
					state=FSMState.HumidityOkay;
				}
				break;
			default:
				break;
			}
	}
	
}
