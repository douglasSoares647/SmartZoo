package com.br.smartzoo.model.timer;

import java.util.Timer;
import java.util.TimerTask;

public class DigestionTimer extends Timer{
	
	boolean isDigesting;
	
	public DigestionTimer(){
		isDigesting = false;
	}
	
	@Override
	public void schedule(TimerTask task, long delay) {
		// TODO Auto-generated method stub
		super.schedule(task, delay);
		isDigesting = true;
	}

	public boolean getIsDigesting() {
		return isDigesting;
	}

	public void setIsDigesting(boolean isDigesting) {
		this.isDigesting = isDigesting;
	}


}
