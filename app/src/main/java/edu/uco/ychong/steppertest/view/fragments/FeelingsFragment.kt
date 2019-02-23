package edu.uco.ychong.steppertest.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import edu.uco.ychong.steppertest.R
import edu.uco.ychong.steppertest.model.Feelings
import edu.uco.ychong.steppertest.view.FeelingsSpinnerAdapter
import kotlinx.android.synthetic.main.fragment_feelings.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FeelingsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feelings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typesOfFeelings = Feelings.getAllFeelings()

        id_feelings_spinner.adapter = FeelingsSpinnerAdapter(
            activity!!.applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            typesOfFeelings
        )

        /*button_why.setOnClickListener {
            val feelings = findViewById<Spinner>(R.id.id_feelings_spinner).selectedItem.toString().trim()

            //loadFragment

        }*/

    }

    private fun setFeelings(position: Int) {
        id_feelings_spinner.setSelection(position)
    }
}
