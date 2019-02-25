package edu.uco.ychong.steppertest.view.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import edu.uco.ychong.steppertest.R
import edu.uco.ychong.steppertest.model.Feelings
import edu.uco.ychong.steppertest.view.DiaryViewModel
import edu.uco.ychong.steppertest.view.FeelingsSpinnerAdapter
import kotlinx.android.synthetic.main.fragment_feelings.*

class FeelingsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var diaryModel: DiaryViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feelings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        activity?.let {
            diaryModel = ViewModelProviders.of(it).get(DiaryViewModel::class.java)
        }

        setUpSpinner(view)
        setUpPager()
    }

    private fun setUpSpinner(view: View) {
        val spinner = view.findViewById<Spinner>(R.id.id_feelings_spinner)
        spinner.onItemSelectedListener = this
        val feelings = Feelings.getAllFeelings()
        val spinnerAdapter = FeelingsSpinnerAdapter(context!!, android.R.layout.simple_spinner_item, feelings)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
    }

    private fun setUpPager() {
        val pager = activity?.findViewById<ViewPager>(edu.uco.ychong.steppertest.R.id.id_pager)
        id_next_0.setOnClickListener {
            pager?.setCurrentItem(1, true)
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        // Update the 'feeling' data in diaryModel
        // You can do it like this for multiple value
        diaryModel.feeling.apply {
            val feeling = id_feelings_spinner.selectedItem
            diaryModel.feeling.value = feeling.toString()
        }

        // or for one value
//        diaryModel.feeling.value = id_feelings_spinner.selectedItem.toString()
    }
}
