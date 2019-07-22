package com.jccd.monitoringsystem.db.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import com.jccd.monitoringsystem.db.model.User
import com.jccd.monitoringsystem.ui.register.IRegisterMVP
import com.jccd.monitoringsystem.utils.Constants

class RegisterRepository(private var model: IRegisterMVP.model) :
    IRegisterRepository {

    private var mAuth: FirebaseAuth? = null
    private var mStorageRef: StorageReference? = null

    override fun createUser(user: User, password: String) {
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.createUserWithEmailAndPassword(user.email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    model.userCreated(user)
                } else {
                    if (task.exception.toString() == Constants.ERROR_REGISTER_EMAIL_EXIST_FIREBASE) {
                        model.sendMessageError(Constants.ERROR_REGISTER_EMAIL_EXIST)
                    }
                }

            }
    }

    override fun saveUserInDataBase(user: User) {
        val uid = FirebaseAuth.getInstance().uid ?: Constants.EMPTY
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
        user.uid = uid
        ref.setValue(user)
            .addOnSuccessListener {
                model.userSavedInDataBase()
            }
    }
}
