package com.jccd.monitoringsystem.ui.ph

import com.jccd.monitoringsystem.db.model.PhLevel

interface IPhLevelMVP {
    interface view{
        fun setDataPhLevel(phLevel: PhLevel)
    }
    interface presenter{
        fun loadLastPhLevelField()
    }
}
