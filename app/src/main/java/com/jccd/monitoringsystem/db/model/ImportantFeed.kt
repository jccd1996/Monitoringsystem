package com.jccd.monitoringsystem.db.model

data class ImportantFeed(
    val autor: String,
    val entryId: Int,
    val comment: String,
    val value: String,
    val type: Int,
    val createdAt: String,
    val email: String,
    val status: Boolean,
    val uid: String
)