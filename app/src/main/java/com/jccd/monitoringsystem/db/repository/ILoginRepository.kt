package com.jccd.monitoringsystem.db.repository

interface ILoginRepository {
    fun login(email: String, password: String)
}