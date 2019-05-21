package com.jccd.monitoringsystem.ui.importants.detail

import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jccd.monitoringsystem.db.model.ImportantFeed

interface ImportantDetailMVP {

    interface view{
        fun getData(): ImportantFeed
        fun showProgressView()
        fun hideProgressView()
        fun finishActivity()
        fun setEtCommentError(): TextInputLayout

    }
    interface presenter{
        fun setDataToLoad()
        fun hideProgressView()
        fun finishActivity()
    }
}
