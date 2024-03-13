package com.example.saktest;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MagnetometerActivity extends Activity implements SensorEventListener {

    // Sensors & SensorManager
    private final Sensor Magnetometer;
    private final SensorManager mSensorManager;

    public MagnetometerActivity() {
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Magnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, Magnetometer, SensorManager. SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
    }
}
