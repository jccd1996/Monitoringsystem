package com.jccd.monitoringsystem.utils

class Constants {
    companion object {
        val API_KEY_THING_SPEAK = "HL56NN0FFFEG4ICN"
        val URL_DOWNLOAD_ALL_DATA = "https://api.thingspeak.com/channels/765396/feeds.csv?api_key=HL56NN0FFFEG4ICN"
        val BASE_URL = "https://api.thingspeak.com/channels/765396/"
        val CORRECT_DATA = 100
        val EMPTY_FUllNAME = 101
        val EMPTY_EMAIL = 102
        val EMPTY_PASSWORD = 103
        val REPEAT_PASSWORD = 104
        val SHORT_FIELD = 105
        val INVALID_EMAIL = 106
        val EMPTY_CELL = 107
        val INCOMPLETE_CELL = 108
        val SHORT_PASSWORD = 109
        val NO_ACCEPT_CONDITIONS = 110
        val MINIMUN_FIELD = 3
        val MINIMUN_CELL_NUMBER = 10
        val MINIMUN_CHARACTER_PASSWORD = 6
        val REQUEST_STORAGE = 1
        val REQUEST_PHONE = 2
        val REQUEST_PICTURE = 2
        val ZERO = 0
        val ACTION_PICK = "image/*"
        val CHOOSE_PICTURE = "Seleccionar una foto"
        val NO_PHOTO = "NoPhoto"
        val EMPTY = ""
        val PICTURE_PATH = "/userProfilePicture/"
        val LOGOUT_USER = 111

        val ERROR_LOGIN_ACCOUNT_NOT_EXIST_FIREBASE =
            "com.google.firebase.auth.FirebaseAuthInvalidUserException: There is no user record corresponding to this identifier. The user may have been deleted."
        val ERROR_LOGIN_ACCOUNT_NOT_EXIST = "No existe una cuenta creada con ese correo"
        val ERROR_REGISTER_EMAIL_EXIST_FIREBASE =
            "com.google.firebase.auth.FirebaseAuthUserCollisionException: The email address is already in use by another account."
        val ERROR_REGISTER_EMAIL_EXIST = "Ya existe una cuenta con este correo"
        val ERROR_LOGIN_PASSWORD_INVALID_FIREBASE =
            "com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The password is invalid or the user does not have a password."
        val ERROR_LOGIN_PASSWORD_INVALID = "Contraseña invalida"
        val USER_CREATED = "Usuario creado exitosamente"
        val TITLE = "title"
        val QUALITY = 30
    }
}