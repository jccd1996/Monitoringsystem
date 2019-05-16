package com.jccd.monitoringsystem.db.model


import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("entry_id")
    val entryId: Int,
    @SerializedName("field1")
    val temperature: String,
    @SerializedName("field2")
    val waterLeve: String,
    @SerializedName("field3")
    val phLevel: String
)