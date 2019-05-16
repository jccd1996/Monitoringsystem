package com.jccd.monitoringsystem.db.network.response

import com.jccd.monitoringsystem.db.model.Channel
import com.jccd.monitoringsystem.db.model.Feed


data class ThingSpeakResponse(
    val channel: Channel,
    val feeds: List<Feed>
)