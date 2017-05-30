package proj.example.myapplication.Network.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by karan on 27/5/17.
 */
class LoanApplication(var userId:Int, var loanAmount:Int, var purpose:String, var duration:Int,
                      var street:String, var city:String, var state:String, var pincode:String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<LoanApplication> = object : Parcelable.Creator<LoanApplication> {
            override fun createFromParcel(source: Parcel): LoanApplication = LoanApplication(source)
            override fun newArray(size: Int): Array<LoanApplication?> = arrayOfNulls(size)
        }
    }
    constructor():this(0,0,"",0,"","","","")

    constructor(source: Parcel) : this(
    source.readInt(),
    source.readInt(),
    source.readString(),
    source.readInt(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(userId)
        dest.writeInt(loanAmount)
        dest.writeString(purpose)
        dest.writeInt(duration)
        dest.writeString(street)
        dest.writeString(city)
        dest.writeString(state)
        dest.writeString(pincode)
    }
}