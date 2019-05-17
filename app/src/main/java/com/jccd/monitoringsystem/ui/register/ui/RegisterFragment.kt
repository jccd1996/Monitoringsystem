package com.jccd.monitoringsystem.ui.register.ui

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.ui.register.IRegisterMVP
import com.jccd.monitoringsystem.ui.register.RegisterPresenter
import com.jccd.monitoringsystem.utils.Constants
import com.jccd.monitoringsystem.utils.ValidateFields
import kotlinx.android.synthetic.main.progress_view.*
import kotlinx.android.synthetic.main.register_fragment.*


class RegisterFragment : Fragment(), IRegisterMVP.view {

    private lateinit var presenter: IRegisterMVP.presenter
    private lateinit var view: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customToolbar()
        view = inflater.inflate(R.layout.register_fragment, container, false)
        presenter = RegisterPresenter(this)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bRegister.setOnClickListener {
            if(ValidateFields().isNetworkAvailable(activity!!.applicationContext)){
                presenter.registerButtonClicked()
            }else{
                Toast.makeText(activity!!.applicationContext,R.string.check_internet_connection,Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getFullName(): String = etFullName.text.toString()
    override fun getCellPhone(): String = etCellPhone.text.toString()
    override fun getPassword(): String = etRegisterPassword.text.toString()
    override fun getRepeatPassword(): String = etRegisterRepeatPassword.text.toString()
    override fun getEmail(): String = etEmail.text.toString()
    override fun getView(): View = view
    override fun getContext(): Context = activity!!.applicationContext

    override fun showProgressView() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgressView() {
        progress_view.visibility = View.GONE
    }

    override fun showError(errorMessage: String) {
        Snackbar.make(view, errorMessage, Snackbar.LENGTH_LONG)
            .show()
    }

    override fun showSucces(succesMessage: String) {
        Toast.makeText(activity?.applicationContext, succesMessage, Toast.LENGTH_LONG).show()
    }
    override fun goToLogin() {
        view.findNavController().navigate(R.id.toLoginFragment,null, NavOptions.Builder()
            .setPopUpTo(R.id.registerFragment,true).build())
    }

    fun customToolbar(){
        (activity as AppCompatActivity).supportActionBar!!.title = Constants.EMPTY
        (activity as AppCompatActivity).supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor
            (R.color.unibague_blue)))
    }
}
