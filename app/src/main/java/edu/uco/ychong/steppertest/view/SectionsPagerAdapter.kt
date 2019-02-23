package edu.uco.ychong.steppertest.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import edu.uco.ychong.steppertest.view.fragments.FeelingsFragment
import edu.uco.ychong.steppertest.view.fragments.ImprovementFragment
import edu.uco.ychong.steppertest.view.fragments.ReasonFragment

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return FeelingsFragment()
            1 -> return ReasonFragment()
            2 -> return ImprovementFragment()
        }

        return null
    }
}