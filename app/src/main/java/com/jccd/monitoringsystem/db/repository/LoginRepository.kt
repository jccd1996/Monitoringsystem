package com.jccd.monitoringsystem.db.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.db.model.User
import com.jccd.monitoringsystem.db.prefs.SessionManager
import com.jccd.monitoringsystem.ui.login.LoginPresenter
import com.jccd.monitoringsystem.utils.Constants

class LoginRepository(private var presenter: LoginPresenter) : ILoginRepository{

    private var mAuth: FirebaseAuth? = null

    override fun login(email: String, password: String) {
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    getUserData()
                } else {
                    var errorMessage = Constants.EMPTY
                    if (task.exception.toString() == Constants.ERROR_LOGIN_PASSWORD_INVALID_FIREBASE) {
                        errorMessage = Constants.ERROR_LOGIN_PASSWORD_INVALID
                    }
                    if (task.exception.toString() == Constants.ERROR_LOGIN_ACCOUNT_NOT_EXIST_FIREBASE) {
                        errorMessage = Constants.ERROR_LOGIN_ACCOUNT_NOT_EXIST
                    }
                    presenter.sendMessageError(errorMessage)
                }
            }
    }

    fun getUserData() {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach {
                    val fullName = p0.child("fullName").getValue(String::class.java)
                    val cellPhone = p0.child("cellPhone").getValue(String::class.java)
                    val email = p0.child("email").getValue(String::class.java)
                    val user = User(fullName!!, email!!, cellPhone!!)
                    user.uid = uid
                    saveUser(user)
                    presenter.loginSuccesfull()
                }
            }
        })
    }

    fun saveUser(user: User){
        SessionManager(MonitoringSystem.getInstance().getContext()!!).saveUserLogin(user)
    }
}