package br.com.liveo.mvp.model

import android.os.Parcel
import android.os.Parcelable
import br.com.liveo.mvp.base.BaseModel
import com.google.gson.annotations.SerializedName

/**
 * Created by rudsonlima on 8/29/17.
 */

class User(var id: Int,
           @SerializedName("first_name")
           var name: String,
           @SerializedName("last_name")
           var lastName: String,
           var avatar: String) : BaseModel(), Parcelable {

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(name)
        writeString(lastName)
        writeString(avatar)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}
