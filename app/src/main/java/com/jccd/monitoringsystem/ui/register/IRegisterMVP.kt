package com.jccd.monitoringsystem.ui.register

import android.content.Context
import android.view.View
import com.jccd.monitoringsystem.db.model.User

interface IRegisterMVP {

    interface view {
        fun getFullName(): String
        fun getCellPhone(): String
        fun getEmail(): String
        fun getPassword(): String
        fun getRepeatPassword(): String
        fun showProgressView()
        fun hideProgressView()
        fun showError(errorMessage: String)
        fun showSucces(succesMessage:String)
        fun getView(): View
        fun getContext(): Context
        fun goToLogin()
    }

    interface presenter {
        fun registerButtonClicked()
        fun sendMessageError(errorMessage: String)
        fun userSavedInDataBase()
    }

    interface model {
        fun sendUser(fullName: String, email: String, cellphone: String, password: String)
        fun userCreated(user: User)
        fun sendMessageError(errorMessage: String)
        fun userSavedInDataBase()
    }
}