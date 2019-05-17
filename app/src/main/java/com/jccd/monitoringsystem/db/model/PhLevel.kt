package com.jccd.monitoringsystem.db.model

import com.google.gson.annotations.SerializedName

data class PhLevel (
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("entry_id")
    val entryId: Int,
    @SerializedName("field3")
    val waterLevel: String
)