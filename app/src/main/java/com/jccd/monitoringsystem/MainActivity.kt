package com.jccd.monitoringsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.jccd.monitoringsystem.db.prefs.SessionManager
import com.jccd.monitoringsystem.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        validateUserLog()
        bLogOut.setOnClickListener {
            SessionManager(MonitoringSystem.sInstance.getContext()!!).logOut()
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
        }
    }

    fun validateUserLog() {
        val uid= FirebaseAuth.getInstance().uid
        if(uid==null){
            SessionManager(MonitoringSystem.sInstance.getContext()!!).logOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            finish()
        }
    }
}
