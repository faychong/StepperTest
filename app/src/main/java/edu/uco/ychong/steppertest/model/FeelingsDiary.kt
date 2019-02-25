package edu.uco.ychong.steppertest.model

import android.os.Parcel
import android.os.Parcelable

class FeelingsDiary(var feelings: String,
                    var feelingsExplanation: String,
                    var isImprovable: Boolean,
                    var improvementExplanation: String) : Parcelable {

    constructor() : this("", "", false, "")

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(feelings)
        parcel.writeString(feelingsExplanation)
        parcel.writeByte(if (isImprovable) 1 else 0)
        parcel.writeString(improvementExplanation)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FeelingsDiary> {
        override fun createFromParcel(parcel: Parcel): FeelingsDiary {
            return FeelingsDiary(parcel)
        }

        override fun newArray(size: Int): Array<FeelingsDiary?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "\nfeeling: $feelings\n" +
                "feelingExplaination: $feelingsExplanation\n" +
                "isImprove: $isImprovable\n" +
                "improvementExplaination: $improvementExplanation\n"
    }

}