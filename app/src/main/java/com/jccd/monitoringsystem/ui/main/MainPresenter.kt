package com.jccd.monitoringsystem.ui.main

import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.User
import com.jccd.monitoringsystem.db.prefs.SessionManager

class MainPresenter(private val view: IMainMVP.view) : IMainMVP.presenter {
    private val sessionManager = SessionManager(MonitoringSystem.getInstance().getContext()!!)
    override fun closeSession() {
        val builder = AlertDialog.Builder(view.getMainActivity())
        builder.setTitle(R.string.close_session)
        builder.setMessage(R.string.lab_dialog_logout_confirmation_message)

        builder.setPositiveButton(R.string.lab_dialog_confirmation_message){dialog, which ->
            FirebaseAuth.getInstance().signOut()
            view.finishActivity()
        }

        builder.setNegativeButton(R.string.lab_dialog_cancel_message){dialog,which ->
            dialog.dismiss()
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun validateLogUser() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            SessionManager(MonitoringSystem.sInstance.getContext()!!).logOut()
            view.finishActivity()
        }
    }

    override fun loadDataUser(): User {
        val user = sessionManager.getUserProfile()
         return user!!
    }
}
