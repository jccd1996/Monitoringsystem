package com.jccd.monitoringsystem.ui.login

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.repository.ILoginRepository
import com.jccd.monitoringsystem.db.repository.LoginRepository
import com.jccd.monitoringsystem.utils.Constants
import com.jccd.monitoringsystem.utils.ValidateFields

class LoginPresenter(private var view: ILoginMVP.view) : ILoginMVP.presenter {

    private lateinit var auth: FirebaseAuth
    private var repository: ILoginRepository = LoginRepository(this)
    private var context = MonitoringSystem.sInstance.getContext()

    override fun loginButtonClicked() {
        view.showProgressView()
        val validateCode = ValidateFields().getValideLogin(view.getEmail(), view.getPassword())

        if (validateCode == Constants.CORRECT_DATA) {
            repository.login(view.getEmail(), view.getPassword())
        } else {
            ValidateFields().setErrorField(validateCode, view.getView())
            view.hideProgressView()
        }
    }

    override fun loginSuccesfull() {
        view.hideProgressView()
        view.showWelcomeMessage()
        view.navigateToMainActivity()
    }

    override fun sendMessageError(errorMessage: String) {
        view.showError(errorMessage)
        view.hideProgressView()
    }

    override fun showDialogRememberPassword() {
        auth=FirebaseAuth.getInstance()
        val mDialogView = LayoutInflater.from(view.getActivityFragment()).inflate(R.layout.send_email_password, null)
        val dialog = AlertDialog.Builder(
            view.getActivityFragment(),
            R.style.Base_ThemeOverlay_MaterialComponents_Dialog_Alert
        )
            .setView(mDialogView)
            .setPositiveButton(
                R.string.label_send_rember_password,
                DialogInterface.OnClickListener { dialogInterface, i -> })
            .setNegativeButton(R.string.lab_dialog_cancel_message) { _, _ ->
            }
            .setTitle(R.string.label_remember_password)
            .create()
        val mDialogAlert = dialog.show()
        mDialogAlert

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            //validarCorreos(etContrasenaNueva?.text.toString(),etRepetirContrasena?.text.toString())
            val etEmail = mDialogView.findViewById<EditText>(R.id.etSendEmail)?.text.toString().replace(" ","")
            view.showProgressView()
            if (!TextUtils.isEmpty(etEmail)) {
                auth.sendPasswordResetEmail(etEmail)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            view.hideProgressView()
                            dialog.dismiss()
                            Toast.makeText(context, R.string.label_succesfull_send_email, Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, R.string.label_error_send_email, Toast.LENGTH_SHORT).show()
                            view.hideProgressView()
                        }
                    }
            }
        }
    }
}