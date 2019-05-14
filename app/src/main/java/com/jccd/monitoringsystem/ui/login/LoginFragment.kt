package com.jccd.monitoringsystem.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.utils.SafeClickListener
import kotlinx.android.synthetic.main.login_fragment.*



class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bRegister.setSafeOnClickListener {
            it.findNavController().navigate(R.id.toRegisterFragment)
        }

        bLogin.setOnClickListener {
            Toast.makeText(MonitoringSystem.getInstance().getContext(),"prueba",Toast.LENGTH_SHORT).show()
        }
    }


    fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            onSafeClick(it)
        }
        setOnClickListener(safeClickListener)
    }
}
