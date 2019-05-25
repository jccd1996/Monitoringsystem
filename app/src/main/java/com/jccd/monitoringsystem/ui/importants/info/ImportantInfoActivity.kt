package com.jccd.monitoringsystem.ui.importants.info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.model.Feed
import com.jccd.monitoringsystem.db.model.ImportantFeed
import com.jccd.monitoringsystem.utils.Constants
import kotlinx.android.synthetic.main.activity_important_info.*

class ImportantInfoActivity : AppCompatActivity(), IImportantInfoMVP.view {

    private lateinit var presenter: IImportantInfoMVP.presenter
    private val IMPORTANT_FEED_DETAIL = "importantFeed"
    private lateinit var importantFeed: ImportantFeed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_important_info)
        presenter = ImportantInfoPresenter(this)
        setToolbar(toolbar)
        importantFeed = intent.getParcelableExtra(IMPORTANT_FEED_DETAIL)
        setData(importantFeed)


    }

    fun setToolbar(toolbar: Toolbar){
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    fun setData(importantFeed: ImportantFeed){
        tvComment.text = importantFeed.comment
        tvDate.text = importantFeed.createdAt
        tvAutor.text = importantFeed.autor
        tvEmail.text = importantFeed.email

        when(importantFeed.type){
            1-> tvValue.text = importantFeed.value + Constants.EMPTY_SPACE + this.getString(R.string.temperature_symbol)
            2-> tvValue.text = importantFeed.value + Constants.EMPTY_SPACE + this.getString(R.string.unit_water_level)
            3-> tvValue.text = importantFeed.value
        }
    }
}
