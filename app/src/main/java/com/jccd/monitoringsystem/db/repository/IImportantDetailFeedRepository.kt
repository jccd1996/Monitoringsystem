package com.jccd.monitoringsystem.db.repository

import com.jccd.monitoringsystem.db.model.ImportantFeed

interface IImportantDetailFeedRepository {
    fun saveImportantFeed(importantFeed: ImportantFeed)
}