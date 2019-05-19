package com.jccd.monitoringsystem.ui.ph.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.PhLevel
import com.jccd.monitoringsystem.ui.ph.IPhLevelMVP
import com.jccd.monitoringsystem.ui.ph.PhLevelPresenter
import kotlinx.android.synthetic.main.fragment_ph.*

class PhLevelFragment : Fragment(), IPhLevelMVP.view {

    private lateinit var presenter: IPhLevelMVP.presenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = PhLevelPresenter(this)
        customToolbar()
        return inflater.inflate(R.layout.fragment_ph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadLastPhLevelField()
    }

    override fun setDataPhLevel(phLevel: PhLevel) {
        tvDate.text = phLevel.createdAt
        tvPhLevel.text = phLevel.phLevel
    }
    fun customToolbar(){
        (activity as AppCompatActivity).supportActionBar!!.title =
            activity!!.applicationContext.getString(R.string.menu_ph)
    }
}