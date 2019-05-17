package com.jccd.monitoringsystem.ui.login.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.ui.login.ILoginMVP
import com.jccd.monitoringsystem.ui.login.LoginPresenter
import com.jccd.monitoringsystem.ui.main.MainActivity
import com.jccd.monitoringsystem.utils.SafeClickListener
import com.jccd.monitoringsystem.utils.ValidateFields
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.progress_view.view.*


class LoginFragment : Fragment(), ILoginMVP.view {

    private lateinit var presenter: ILoginMVP.presenter
    private lateinit var viewFragment: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = LoginPresenter(this)
        viewFragment = inflater.inflate(R.layout.login_fragment, container, false)
        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bRegister.setSafeOnClickListener {
            it.findNavController().navigate(R.id.toRegisterFragment)
        }

        bLogin.setSafeOnClickListener {
            if (ValidateFields().isNetworkAvailable(activity!!.applicationContext)) {
                presenter.loginButtonClicked()
            } else {
                Toast.makeText(activity!!.applicationContext, R.string.check_internet_connection, Toast.LENGTH_SHORT)
                    .show()
            }
        }
        bForgotPassword.setOnClickListener {
            presenter.showDialogRememberPassword()
        }
    }

    override fun getEmail(): String = etEmail.text.toString().replace(" ","")
    override fun getPassword(): String = etPassword.text.toString()
    override fun getView(): View = viewFragment
    override fun navigateToMainActivity() {
        val intent = Intent(activity?.applicationContext, MainActivity::class.java)
        intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun getActivityFragment() = activity!!

    override fun showError(errorMessage: String) {
        Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG)
            .show()
    }

    override fun showProgressView() {
        viewFragment.progress_view.visibility = View.VISIBLE
    }

    override fun hideProgressView() {
        viewFragment.progress_view.visibility = View.GONE
    }

    override fun showWelcomeMessage() {
        Toast.makeText(activity?.applicationContext, R.string.welcomeMessage,Toast.LENGTH_SHORT).show()
    }

    fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            onSafeClick(it)
        }
        setOnClickListener(safeClickListener)
    }
}
