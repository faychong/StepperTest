package edu.uco.ychong.steppertest.view

import android.content.Context
import android.graphics.Color
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class FeelingsSpinnerAdapter(context: Context, resource: Int, feelingsList: List<String>) :
    ArrayAdapter<String>(context, resource, feelingsList) {

    private val feelingsList = ArrayList<String>()

    override fun isEnabled(position: Int): Boolean {
        if(position == 0) return false
        return true
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent)
        val textView = view as TextView

        if(position == 0) textView.setTextColor(Color.GRAY)
        else textView.setTextColor(Color.BLACK)

        return textView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getDropDownView(position, convertView, parent)
        val textView = view as TextView

        if(position == 0) textView.setTextColor(Color.GRAY)
        else textView.setTextColor(Color.BLACK)

        return view
    }
}
