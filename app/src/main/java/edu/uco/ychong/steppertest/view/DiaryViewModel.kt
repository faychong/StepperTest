package edu.uco.ychong.steppertest.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class DiaryViewModel: ViewModel() {
    var feeling = MutableLiveData<String>()
    var feelingsExplaination = MutableLiveData<String>()
    var improveExplanation = MutableLiveData<String>()
}