package test;

import boundaryclasses.IHumiditySensor;

public class HumiditySensorStub implements IHumiditySensor {
	
	public static double humidity;
	
	@Override
	public double getHumidity() {
		// TODO Auto-generated method stub
		return humidity;
	}

}
