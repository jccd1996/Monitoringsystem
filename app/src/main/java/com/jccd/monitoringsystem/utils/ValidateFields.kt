package com.jccd.monitoringsystem.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Patterns
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import com.jccd.monitoringsystem.R
import java.util.regex.Pattern

class ValidateFields {
    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo: NetworkInfo?
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

    fun valideEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    fun getValideRegister(
        fullName: String,
        email: String,
        cellPhone: String,
        password: String,
        repeatPassword: String
    ): Int {
        if (fullName.isEmpty()) {
            return Constants.EMPTY_FUllNAME
        }
        if (fullName.length < Constants.MINIMUN_FIELD) {
            return Constants.SHORT_FIELD
        }
        if (email.isEmpty()) {
            return Constants.EMPTY_EMAIL
        }
        if (!valideEmail(email.trim())) {
            return Constants.INVALID_EMAIL
        }
        if (cellPhone.isEmpty()) {
            return Constants.EMPTY_CELL
        }
        if (cellPhone.length < Constants.MINIMUN_CELL_NUMBER) {
            return Constants.INCOMPLETE_CELL
        }
        if (password.isEmpty()) {
            return Constants.EMPTY_PASSWORD
        }
        if (password.length < Constants.MINIMUN_CHARACTER_PASSWORD) {
            return Constants.SHORT_PASSWORD
        }
        if (password != repeatPassword) {
            return Constants.REPEAT_PASSWORD
        }
        return Constants.CORRECT_DATA
    }

    fun getValideLogin(email: String, password: String): Int {
        if (email.isEmpty()) {
            return Constants.EMPTY_EMAIL
        }
        if (!valideEmail(email.trim())) {
            return Constants.INVALID_EMAIL
        }
        if (password.isEmpty()) {
            return Constants.EMPTY_PASSWORD
        }
        if (password.length < Constants.MINIMUN_CHARACTER_PASSWORD) {
            return Constants.SHORT_PASSWORD
        }
        return Constants.CORRECT_DATA
    }

    fun getIdError(state: Int): Int {
        var error: Int = Constants.ZERO
        when (state) {
            Constants.EMPTY_FUllNAME -> error = R.string.empty_field_fullname
            Constants.SHORT_FIELD -> error = R.string.invalid_name
            Constants.EMPTY_EMAIL -> error = R.string.empty_field_email
            Constants.INVALID_EMAIL -> error = R.string.invalid_email
            Constants.EMPTY_CELL -> error = R.string.empty_field_cellphone
            Constants.INCOMPLETE_CELL -> error = R.string.incomplete_cell_number
            Constants.EMPTY_PASSWORD -> error = R.string.empty_field_password
            Constants.SHORT_PASSWORD -> error = R.string.short_field_password
            Constants.REPEAT_PASSWORD -> error = R.string.different_field_password
            Constants.NO_ACCEPT_CONDITIONS -> error = R.string.need_accept_conditions
        }
        return error
    }

    fun setErrorField(id: Int, view: View) {
        var error = getIdError(id)
        var labError = view.context.getString(error)
        lateinit var editText: EditText
        lateinit var checkBox: CheckBox
        var idView = Constants.ZERO
        when (id) {
            Constants.EMPTY_FUllNAME -> {
                idView = R.id.etFullName
                editText = view.findViewById(idView)
                editText.error = labError
            }
            Constants.SHORT_FIELD -> {
                idView = R.id.etFullName
                editText = view.findViewById(idView)
                editText.error = labError
            }
            Constants.EMPTY_EMAIL -> {
                idView = R.id.etEmail
                editText = view.findViewById(idView)
                editText.error = labError
            }
            Constants.INVALID_EMAIL -> {
                idView = R.id.etEmail
                editText = view.findViewById(idView)
                editText.error = labError
            }
            Constants.EMPTY_CELL -> {
                idView = R.id.etCellPhone
                editText = view.findViewById(idView)
                editText.error = labError
            }
            Constants.INCOMPLETE_CELL -> {
                idView = R.id.etCellPhone
                editText = view.findViewById(idView)
                editText.error = labError
            }
            Constants.EMPTY_PASSWORD -> {
                idView = R.id.etRegisterPassword
                editText = view.findViewById(idView)
                editText.error = labError
            }
            Constants.SHORT_PASSWORD -> {
                idView = R.id.etRegisterPassword
                editText = view.findViewById(idView)
                editText.error = labError
            }
            Constants.REPEAT_PASSWORD -> {
                idView = R.id.etRegisterRepeatPassword
                editText = view.findViewById(idView)
                editText.error = labError
            }
        }
    }
}