package com.example.saktest


import android.app.Activity
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [kompas.newInstance] factory method to
 * create an instance of this fragment.
 */




class kompas : Fragment(), SensorEventListener {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    private var mSensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSensorManager = this.activity!!.getSystemService(Activity.SENSOR_SERVICE) as SensorManager

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_kompas, container, false)
        view.findViewById<Button>(R.id.agenda_to_klok).setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_kompas_to_klok)
        }
        mSensorManager!!.registerListener(this,
            mSensorManager!!.getSensorList(Sensor.TYPE_ACCELEROMETER)[0], SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager!!.registerListener(this,
            mSensorManager!!.getSensorList(Sensor.TYPE_MAGNETIC_FIELD)[0], SensorManager.SENSOR_DELAY_FASTEST);

        return view
    }


    override fun onSensorChanged(event: SensorEvent) {
        var geomagnetic = FloatArray(3)
        var gravity = FloatArray(3)
        var R = FloatArray(9)
        var I = FloatArray(9)
    var succes: Boolean = SensorManager.getRotationMatrix(R, I, gravity, geomagnetic )
        if (succes){
            var orientation = FloatArray(3)
            SensorManager.getOrientation(R, orientation)
            var azimuth = orientation[0]
            Log.println(Log.INFO, "ORI", "$azimuth")
//            this.view?.findViewById<TextView>(R.id.compasheading)?.text = "$orientation"
        }


        if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {

            geomagnetic = event.values
//            Log.println(Log.INFO, "SAK", "Magneto is$geomagnetic")


        }
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {

            gravity = event.values
//            Log.println(Log.INFO, "SAK", "Accelo is")
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)

        // First starts (gets called before everything else)
        if (mSensorManager == null) {
            return
        }
        if (menuVisible) {
            registerSensorListener()
        } else {
            unregisterSensorListener()
        }
    }

    override fun onStart() {
        super.onStart()
        if (this.userVisibleHint) {
            registerSensorListener()
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterSensorListener()
    }

    private fun registerSensorListener() {
        mSensorManager!!.registerListener(
            this,
            mSensorManager!!.getSensorList(Sensor.TYPE_ACCELEROMETER)[0],
            SensorManager.SENSOR_DELAY_FASTEST
        )
        mSensorManager!!.registerListener(
            this,
            mSensorManager!!.getSensorList(Sensor.TYPE_MAGNETIC_FIELD)[0],
            SensorManager.SENSOR_DELAY_FASTEST
        )

    }

    private fun unregisterSensorListener() {
        mSensorManager!!.unregisterListener(this)
    }




}

