// This string is autogenerated by ChangeAppSettings.sh, do not change spaces amount
package com.sourceforge.sc2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.os.Vibrator;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;

import android.widget.TextView;


class AccelerometerReader implements SensorEventListener {

	private SensorManager _manager = null;

	public AccelerometerReader(Activity context) {
		_manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		if( _manager != null )
		{
			if( Globals.UseAccelerometerAsArrowKeys )
			{
				_manager.registerListener(this, _manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
			}
			else
			{
				if( Globals.AppUsesJoystick )
				{
					if( ! _manager.registerListener(this, _manager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME) )
						_manager.registerListener(this, _manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
				}
			}
		}
	}
	
	public synchronized void stop() {
		if( _manager != null )
		{
			_manager.unregisterListener(this);
		}
	}

	public synchronized void onSensorChanged(SensorEvent event) {

		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) 
		{
			if( Globals.HorizontalOrientation )
				nativeAccelerometer(event.values[1], -event.values[0], event.values[2]);
			else
				nativeAccelerometer(event.values[0], event.values[1], event.values[2]);
		}
		else
		{
			if( Globals.HorizontalOrientation )
				nativeOrientation(event.values[1], -event.values[0], event.values[2]);
			else
				nativeOrientation(event.values[0], event.values[1], event.values[2]);
		}
		
	}

	public synchronized void onAccuracyChanged(Sensor s, int a) {
	}
	

	private native void nativeAccelerometer(float accX, float accY, float accZ);
	private native void nativeOrientation(float accX, float accY, float accZ);
}


