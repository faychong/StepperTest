package edu.uco.ychong.steppertest.view.fragments


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.uco.ychong.steppertest.R
import edu.uco.ychong.steppertest.view.DiaryViewModel
import kotlinx.android.synthetic.main.fragment_reason.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ReasonFragment : Fragment() {

    private lateinit var diaryModel: DiaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reason, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // Initialize the ViewModel
        activity?.let {
            diaryModel = ViewModelProviders.of(it).get(DiaryViewModel::class.java)
        }

        setUpPager()
    }

    private fun setUpPager() {
        val pager = activity?.findViewById<ViewPager>(edu.uco.ychong.steppertest.R.id.id_pager)
        id_next_1.setOnClickListener {
            pager?.setCurrentItem(2, true)
            diaryModel.feelingsExplaination.value = id_reasonInput.text.toString()
        }

        id_back_1.setOnClickListener {
            pager?.setCurrentItem(0, true)
        }
    }


}
