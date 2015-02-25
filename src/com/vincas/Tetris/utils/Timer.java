package com.vincas.Tetris.utils;

public class Timer {
	private float eventTime;
	private float deltaStock;

	public Timer(int eventTime) {
		this.eventTime = eventTime;
	}

	public void update(int delta) {
		deltaStock += delta;
		if (deltaStock >= eventTime) {
		}
	}
	
	public boolean isTimeComplete() {
		return deltaStock >= eventTime;
	}

	public void resetTime() {
		deltaStock = 0;
	}
	
	public void restartTimer(int eventTime) {
		this.eventTime = eventTime;
		resetTime();
	}
}
