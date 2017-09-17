package br.com.liveo.mvp.model

import android.os.Parcel
import android.os.Parcelable
import br.com.liveo.mvp.base.BaseModel

import com.google.gson.annotations.SerializedName

/**
 * Created by rudsonlima on 8/29/17.
 */

class User : BaseModel, Parcelable {

    var id: Int = 0

    @SerializedName("first_name")
    var name: String? = null

    @SerializedName("last_name")
    var lastName: String? = null

    var avatar: String? = null

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.id)
        dest.writeString(this.name)
        dest.writeString(this.lastName)
        dest.writeString(this.avatar)
    }

    constructor() {}

    private constructor(`in`: Parcel) {
        this.id = `in`.readInt()
        this.name = `in`.readString()
        this.lastName = `in`.readString()
        this.avatar = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}
