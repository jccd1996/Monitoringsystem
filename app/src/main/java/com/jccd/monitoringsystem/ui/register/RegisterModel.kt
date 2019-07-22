package com.jccd.monitoringsystem.ui.register

import com.jccd.monitoringsystem.db.model.User
import com.jccd.monitoringsystem.db.repository.IRegisterRepository
import com.jccd.monitoringsystem.db.repository.RegisterRepository

class RegisterModel(private var presenter: IRegisterMVP.presenter) : IRegisterMVP.model {

    private var repository: IRegisterRepository = RegisterRepository(this)
    lateinit var user: User

    override fun sendUser(fullName: String, email: String, cellphone: String, password: String) {
        user = User(fullName, email, cellphone)
        repository.createUser(user, password)
    }

    override fun userCreated(user: User) {
        repository.saveUserInDataBase(user)

    }

    override fun sendMessageError(errorMessage: String) {
        presenter.sendMessageError(errorMessage)
    }

    override fun userSavedInDataBase() {
        presenter.userSavedInDataBase()
    }
}
