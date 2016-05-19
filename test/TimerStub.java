package test;

import boundaryclasses.ITimer;

public class TimerStub implements ITimer {
	
	private boolean zeitAbgelaufen = false;

	@Override
	public void startTime(double seconds) {
		if(seconds < 5){
		zeitAbgelaufen=true;
		}else{
			zeitAbgelaufen=false;
		}
	}

	@Override
	public boolean isTimerExpired() {
		return zeitAbgelaufen;
	}

}
