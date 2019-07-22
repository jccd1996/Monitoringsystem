package com.jccd.monitoringsystem.db.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feed(
    @SerializedName("created_at")
    val createdAt: String= "",
    @SerializedName("entry_id")
    val entryId: Int= 0,
    @SerializedName("field1")
    val temperature: String = "",
    @SerializedName("field2")
    val waterLeve: String= "",
    @SerializedName("field3")
    val phLevel: String = ""
): Parcelable
