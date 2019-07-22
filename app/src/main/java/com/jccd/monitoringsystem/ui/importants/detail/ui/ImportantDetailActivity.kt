package com.jccd.monitoringsystem.ui.importants.detail.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputLayout
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.db.model.ImportantFeed
import com.jccd.monitoringsystem.db.prefs.SessionManager
import com.jccd.monitoringsystem.ui.importants.detail.ImportantDetailMVP
import com.jccd.monitoringsystem.ui.importants.detail.ImportantDetailPresenter
import com.jccd.monitoringsystem.utils.Constants
import com.jccd.monitoringsystem.utils.ConvertDate
import kotlinx.android.synthetic.main.activity_important_detail.*
import kotlinx.android.synthetic.main.progress_view.*


class ImportantDetailActivity : AppCompatActivity(), ImportantDetailMVP.view {

    private lateinit var toolbar: Toolbar
    private var type: Int = 0
    private val TYPE_KEY = "type"
    private val FEED_DETAIL = "feed"
    private lateinit var feed: Feed
    private lateinit var presenter: ImportantDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_important_detail)
        toolbar = findViewById(R.id.toolbar)
        presenter = ImportantDetailPresenter(this)
        getIntents()
        customToolbar()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_important_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_important -> {
                presenter.setDataToLoad()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun getData(): ImportantFeed {
        val autor = SessionManager(MonitoringSystem.sInstance.getContext()!!).getUserName()
        val email = SessionManager(MonitoringSystem.sInstance.getContext()!!).getUserEmail()
        val comment = etComment.text.toString()
        var value = ""
        when (type) {
            1 -> {
                value = feed.temperature
            }
            2 -> {
                value = feed.waterLeve
            }
            3 -> {
                value = feed.phLevel
            }
        }
        return ImportantFeed(
            autor,
            feed.entryId,
            comment,
            value,
            type,
            feed.createdAt,
            email,
            true,
            SessionManager(this).getUserProfile()!!.uid
        )
    }

    fun customToolbar() {
        when (type) {
            1 -> {
                toolbar.title = this.getString(R.string.menu_temperature)
                tvValue.text =
                    feed.temperature + Constants.EMPTY_SPACE + this.getString(R.string.temperature_symbol)
            }
            2 -> {
                toolbar.title = this.getString(R.string.menu_level_water)
                tvValue.text = feed.waterLeve + Constants.EMPTY_SPACE + this.getString(R.string.unit_water_level)
            }
            3 -> {
                toolbar.title = this.getString(R.string.menu_ph)
                tvValue.text = feed.phLevel
            }
        }
        tvDate.text = ConvertDate(feed.createdAt).converToDateColombian()
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    fun getIntents() {
        type = intent.getIntExtra(TYPE_KEY, 0)
        feed = intent.getParcelableExtra(FEED_DETAIL)
    }

    override fun showProgressView() {
        progress_view.visibility = View.VISIBLE
    }

    override fun hideProgressView() {
        progress_view.visibility = View.GONE
    }

    override fun finishActivity(){
        finish()
    }

    override fun setEtCommentError(): TextInputLayout = tiComment
}


