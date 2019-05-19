package com.jccd.monitoringsystem.db.model

import com.google.gson.annotations.SerializedName

data class WaterLevel (
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("entry_id")
    val entryId: Int,
    @SerializedName("field2")
    val waterLevel: String
)
