package com.jccd.monitoringsystem.ui.main

import android.app.Activity

interface IMainMVP {
    interface view{
        fun finishActivity()
        fun getMainActivity(): Activity
    }
    interface presenter{
        fun validateLogUser()
        fun closeSession()
    }
}