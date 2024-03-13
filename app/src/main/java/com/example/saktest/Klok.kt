//https://medium.com/@nipunvirat0/how-to-schedule-alarm-in-android-using-alarm-manager-7a1c3b23f1bb

package com.example.saktest

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Klok.newInstance] factory method to
 * create an instance of this fragment.
 */
class Klok : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var currentTime: String

    private var alarmMgr: AlarmManager? = null
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {


        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                tick()
                mainHandler.postDelayed(this, 1000)
            }
        })
        alarmMgr = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 0, intent, 0)
        }


        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    // tijd vragen voor de klok
    private fun tick() {
        var calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
// plaats maken voor de klok waar hij de tijd kan neerzetten
        val currentTime = simpleDateFormat.format(calendar.time)
        this.view?.findViewById<TextView>(R.id.digitale_klok)?.text = currentTime

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // knopjes om naar de andere pagina te gaan
        val view = inflater.inflate(R.layout.fragment_klok, container, false)

        view.findViewById<ImageButton>(R.id.klok_to_agenda).setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_klok_to_agenda)

        }

        view.findViewById<ImageButton>(R.id.rekenmachine_to_kompas).setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_klok_to_kompas)
        }

        view.findViewById<ImageButton>(R.id.kaart_to_grotekaart).setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_klok_to_grotekaart)
        }

        view.findViewById<ImageButton>(R.id.rekenmachine_to_rekenmachine).setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_klok_to_rekenmachine)
        }

// poging tot alarm maken maar duurde te lang
        val alarmManager = context!!.getSystemService(Context.ALARM_SERVICE) as AlarmManager
       view.findViewById<SwitchMaterial>(R.id.s_7_00).setOnCheckedChangeListener { _, isChecked ->
           if (isChecked) {
               Toast.makeText(activity, "Wakker worden!!!!!" , Toast.LENGTH_SHORT).show();
               val calendar: Calendar = Calendar.getInstance().apply {
                   timeInMillis = System.currentTimeMillis()
                   set(Calendar.HOUR_OF_DAY, 14)
               }
               alarmMgr?.set(
                   AlarmManager.ELAPSED_REALTIME_WAKEUP,
                   SystemClock.elapsedRealtime() + 60 * 1000,
                   alarmIntent
               )
               //val calendar = Calendar.getInstance()
               // krijg de current tijd van calender
               //calendar.set(Calendar.MINUTE, 15)
               //calendar.set(Calendar.HOUR, 7)









           } else {
               // The switch isn't checked.
           }



       }

        return view
    }



}





