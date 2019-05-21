package com.jccd.monitoringsystem.db.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImportantFeed(
    val autor: String = "",
    val entryId: Int = 0,
    val comment: String = "",
    val value: String = "",
    val type: Int = 0,
    val createdAt: String = "",
    val email: String = "",
    val status: Boolean = true,
    val uid: String = ""
): Parcelable