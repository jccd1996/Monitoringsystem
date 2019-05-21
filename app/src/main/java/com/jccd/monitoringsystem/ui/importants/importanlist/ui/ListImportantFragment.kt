package com.jccd.monitoringsystem.ui.importants.importanlist.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.ui.importants.importanlist.IListImportantMVP
import com.jccd.monitoringsystem.ui.importants.importanlist.ListImportantPresenter
import kotlinx.android.synthetic.main.fragment_list_important.*

class ListImportantFragment : Fragment(),IListImportantMVP.view {

    private lateinit var presenter: IListImportantMVP.presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter = ListImportantPresenter(this)
        customToolbar()
        return inflater.inflate(R.layout.fragment_list_important, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadRecyclerView()
        presenter.loadItems()
    }

    override fun getRecyclerView(): RecyclerView = rvListImportant

    fun customToolbar(){
        (activity as AppCompatActivity).supportActionBar!!.title =
            activity!!.applicationContext.getString(R.string.menu_archive)
    }
}
