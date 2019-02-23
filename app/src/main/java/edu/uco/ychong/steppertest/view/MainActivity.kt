package edu.uco.ychong.steppertest.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.badoualy.stepperindicator.StepperIndicator
import edu.uco.ychong.steppertest.R
import edu.uco.ychong.steppertest.model.Feelings
import edu.uco.ychong.steppertest.model.FeelingsDiary
import edu.uco.ychong.steppertest.view.fragments.FeelingsFragment
import edu.uco.ychong.steppertest.view.fragments.ImprovementFragment
import edu.uco.ychong.steppertest.view.fragments.ReasonFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_feelings.*
import kotlinx.android.synthetic.main.fragment_improvement.*
import kotlinx.android.synthetic.main.fragment_reason.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val stepper = findViewById<StepperIndicator>(R.id.id_stepper)
        stepper.stepCount = 3
        stepper.currentStep = 0

        val feelingsFragment = FeelingsFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.id_constraintLayout, feelingsFragment)
            .addToBackStack(null)
            .commit()

        val feelingsDiary = FeelingsDiary()

        id_nextButton.setOnClickListener {
            when(stepper.currentStep) {
                0 -> {
                    val feelings = id_feelings_spinner.selectedItem.toString().trim()

                    if(feelings.equals("I'm feeling...")) {
                        Toast.makeText(this, "Please choose a feelings option!", Toast.LENGTH_SHORT).show()
                    } else {
                        feelingsDiary.feelings = feelings

                        val reasonFragment = ReasonFragment()
                        val bundle = Bundle()
                        bundle.putString("FEELINGS", feelingsDiary.feelings)
                        reasonFragment.arguments = bundle

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.id_constraintLayout, reasonFragment)
                            .addToBackStack(null)
                            .commit()

                        stepper.currentStep = 1
                        Toast.makeText(this, "${bundle.get("FEELINGS")}", Toast.LENGTH_SHORT).show()
                    }
                }
                1 -> {
                    val reason = id_reasonInput.text.toString().trim()

                    if(reason.isNullOrBlank() || reason.isNullOrEmpty()) {
                        Toast.makeText(this, "Please enter a reason!", Toast.LENGTH_SHORT).show()
                    } else {
                        feelingsDiary.feelingsExplanation = reason

                        val improvementFragment = ImprovementFragment()
                        val bundle = Bundle()
                        bundle.putString("REASON", feelingsDiary.feelingsExplanation)

                        improvementFragment.arguments = bundle

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.id_constraintLayout, improvementFragment)
                            .addToBackStack(null)
                            .commit()

                        stepper.currentStep = 2
                        Toast.makeText(this, "${feelingsDiary.feelings}\n" +
                                "${bundle.get("REASON")}", Toast.LENGTH_SHORT).show()
                    }

                }
                2 -> {
                    val improvement = id_improvementInput.text.toString().trim()

                    if(improvement.isNullOrEmpty() || improvement.isNullOrBlank()) {
                        Toast.makeText(this, "Please enter an explanation!", Toast.LENGTH_SHORT).show()
                    } else {
                        feelingsDiary.improvementExplanation = improvement

                        val bundle = Bundle()

                        bundle.putString("FEELINGS", bundle.getString("FEELINGS"))
                        bundle.putString("REASON", bundle.getString("REASON"))


                        val feels = feelingsDiary.feelings
                        val reason = feelingsDiary.feelingsExplanation
                        val string = "$feels\n$reason\n$improvement"

                        stepper.currentStep = 3
                        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        id_backButton.setOnClickListener {
            when(stepper.currentStep) {
                0 -> {
                    super.onBackPressed()
                }
                1 -> {
                    val feelings = feelingsDiary.feelings

                    if(feelings.equals("I'm feeling...")) {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    } else {
                        //supportFragmentManager.popBackStackImmediate()

                        val backFeelingsFragment = FeelingsFragment()
                        val bundle = Bundle()
                        bundle.putString("FEELINGS", feelingsDiary.feelings)

                        backFeelingsFragment.arguments = bundle

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.id_constraintLayout, backFeelingsFragment)
                            .addToBackStack(null)
                            .commit()

                        stepper.currentStep = 0

                        feelingsDiary.feelings = ""
                        Toast.makeText(this, "${feelingsDiary.feelings}", Toast.LENGTH_SHORT).show()
                    }
                }
                2 -> {
                    val reason = feelingsDiary.feelingsExplanation

                    if(reason.isNullOrBlank() || reason.isNullOrEmpty()) {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    } else {
                        supportFragmentManager.popBackStackImmediate()

                        stepper.currentStep = 1

                        feelingsDiary.feelingsExplanation = ""
                        Toast.makeText(this, "${feelingsDiary.feelingsExplanation}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
