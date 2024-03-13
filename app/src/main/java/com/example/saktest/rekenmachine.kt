package com.example.saktest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.Navigation

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [rekenmachine.newInstance] factory method to
 * create an instance of this fragment.
 */
class rekenmachine : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // de plus knop
        val view = inflater.inflate(R.layout.fragment_rekenmachine, container, false)
        view.findViewById<Button>(R.id.addButton)!!.setOnClickListener {
            view.findViewById<TextView>(R.id.resultTextView)!!.text = (
                    view.findViewById<EditText>(R.id.num1EditText)!!.text.toString().toInt() +
                            view.findViewById<EditText>(R.id.num2EditText)!!.text.toString().toInt()
                    ).toString()
        }
// de min knop
        view.findViewById<Button>(R.id.subtractButton)!!.setOnClickListener {
            view.findViewById<TextView>(R.id.resultTextView)!!.text = (
                    view.findViewById<EditText>(R.id.num1EditText)!!.text.toString().toInt() -
                            view.findViewById<EditText>(R.id.num2EditText)!!.text.toString().toInt()
                    ).toString()
        }
// de keer knop
        view.findViewById<Button>(R.id.multiplyButton)!!.setOnClickListener {
            view.findViewById<TextView>(R.id.resultTextView)!!.text = (
                    view.findViewById<EditText>(R.id.num1EditText)!!.text.toString().toInt() *
                            view.findViewById<EditText>(R.id.num2EditText)!!.text.toString().toInt()
                    ).toString()
// de delen door knop
        }
        view.findViewById<Button>(R.id.divideButton)!!.setOnClickListener {
            view.findViewById<TextView>(R.id.resultTextView)!!.text = (
                    view.findViewById<EditText>(R.id.num1EditText)!!.text.toString().toInt() /
                            view.findViewById<EditText>(R.id.num2EditText)!!.text.toString().toInt()
                    ).toString()
        }
// de navigatie naar de andere pagina's
        view.findViewById<ImageButton>(R.id.rekenmachine_to_agenda).setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_rekenmachine_to_agenda)
        }

        view.findViewById<ImageButton>(R.id.rekenmachine_to_kompas).setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_rekenmachine_to_kompas)
        }
        view.findViewById<ImageButton>(R.id.rekenmachine_to_clock).setOnClickListener { Navigation.findNavController(view).navigate(R.id.action_rekenmachine_to_klok)
        }
        // return de view om alles te kunnen zien
        return view
    }
}

