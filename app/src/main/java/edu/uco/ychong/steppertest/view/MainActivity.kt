package edu.uco.ychong.steppertest.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.badoualy.stepperindicator.StepperIndicator
import edu.uco.ychong.steppertest.model.FeelingsDiary
import kotlinx.android.synthetic.main.activity_main.*

const val MIN_STEP = 0
const val MAX_STEP = 2
const val TAG = "tag"

class MainActivity : AppCompatActivity() {

    private var currentStep = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(edu.uco.ychong.steppertest.R.layout.activity_main)

        // Here we create a storage. This storage has a bunch of data string that can be change.
        // Think of it as like a bank account that holds your money.
        val diaryViewModel = ViewModelProviders.of(this).get(DiaryViewModel::class.java)


        // This is just to show that the data is being updated.
        // The final data is in the last fragment (ImprovementFragment) inside of diaryModel.
        observeMyData(diaryViewModel)

        setUpStepperIndicator()
    }

    private fun setUpStepperIndicator() {
        val pager = findViewById<ViewPager>(edu.uco.ychong.steppertest.R.id.id_pager)
        pager.adapter = SectionsPagerAdapter(supportFragmentManager)

        val indicator = findViewById<StepperIndicator>(edu.uco.ychong.steppertest.R.id.id_stepper_indicator)
        indicator.apply {
            stepCount = 3
            currentStep = 0
            setViewPager(pager, false)
            addOnStepClickListener { step -> pager.setCurrentItem(step, true) }
        }
    }

    private fun observeMyData(diaryViewModel: DiaryViewModel) {
        val myDiary = FeelingsDiary()


        // Now we want to observe our storage and be notify if our data change.
        // This ensure we always have the latest data.
        // Think of it as like keeping an eye on your bank accounts balance.
        // Treat each MutableLiveData as like an account, Checking, Saving, Retirement, etc.

        diaryViewModel.feeling.observe(this, Observer {
            it?.let {
                myDiary.feelings = "$it"

                id_result.text = "${myDiary.feelings}\n" +
                        "${myDiary.feelingsExplanation}\n" +
                        "${myDiary.improvementExplanation}\n"
            }
        })

        diaryViewModel.feelingsExplaination.observe(this, Observer {
            it?.let {
                myDiary.feelingsExplanation= "$it"

                id_result.text = "${myDiary.feelings}\n" +
                        "${myDiary.feelingsExplanation}\n" +
                        "${myDiary.improvementExplanation}\n"
            }
        })

        diaryViewModel.improveExplanation.observe(this, Observer {
            it?.let {
                myDiary.improvementExplanation = "$it"

                id_result.text = "${myDiary.feelings}\n" +
                        "${myDiary.feelingsExplanation}\n" +
                        "${myDiary.improvementExplanation}\n"
            }
        })
    }
}
