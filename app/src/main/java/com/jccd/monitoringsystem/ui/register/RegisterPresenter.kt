package com.jccd.monitoringsystem.ui.register

import android.widget.Toast
import com.jccd.monitoringsystem.utils.Constants
import com.jccd.monitoringsystem.utils.ValidateFields

class RegisterPresenter(): IRegisterMVP.presenter {

    private lateinit var view: IRegisterMVP.view
    private lateinit var model: IRegisterMVP.model

    constructor(view: IRegisterMVP.view) : this() {
        this.view = view
        model = RegisterModel(this)
    }

    override fun registerButtonClicked() {
        view.showProgressView()
        val validateCode = ValidateFields().getValideRegister(
            view.getFullName(), view.getEmail(), view.getCellPhone(),
            view.getPassword(), view.getRepeatPassword())

        if (validateCode == Constants.CORRECT_DATA) {
            model.sendUser(view.getFullName(), view.getEmail(), view.getCellPhone(), view.getPassword())
        } else {
            ValidateFields().setErrorField(validateCode, view.getView())
            view.hideProgressView()
        }
    }

    override fun sendMessageError(errorMessage: String) {
        view.hideProgressView()
        view.showError(errorMessage)
    }
    override fun userSavedInDataBase() {
        view.hideProgressView()
        view.showSucces(Constants.USER_CREATED)
        view.goToLogin()
    }
}
