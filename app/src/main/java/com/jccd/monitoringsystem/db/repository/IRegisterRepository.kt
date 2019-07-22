package com.jccd.monitoringsystem.db.repository

import com.jccd.monitoringsystem.db.model.User

interface IRegisterRepository {

    fun createUser(user: User, password: String)
    fun saveUserInDataBase(user: User)

}
