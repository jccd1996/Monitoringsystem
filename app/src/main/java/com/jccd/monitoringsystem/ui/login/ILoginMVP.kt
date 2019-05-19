package com.jccd.monitoringsystem.ui.login

import android.app.Activity
import android.view.View

interface ILoginMVP {
    interface view {
        fun getEmail(): String
        fun getPassword(): String
        fun showError(errorMessage: String)
        fun showProgressView()
        fun hideProgressView()
        fun showWelcomeMessage()
        fun getView():View
        fun navigateToMainActivity()
        fun getActivityFragment(): Activity

    }

    interface presenter {
        fun loginButtonClicked()
        fun loginSuccesfull()
        fun sendMessageError(errorMessage: String)
        fun showDialogRememberPassword()
    }
}
