package com.jccd.monitoringsystem.db.model


import com.google.gson.annotations.SerializedName

data class Channel(
    val id: Int,
    val name: String,
    val latitude: String,
    val longitude: String,
    @SerializedName("field1")
    val temperature: String,
    @SerializedName("field2")
    val waterLevel: String,
    @SerializedName("field3")
    val phLevel: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("last_entry_id")
    val lastEntryId: Int
)
