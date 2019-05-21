package com.jccd.monitoringsystem.ui.importants.detail

import android.widget.Toast
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.db.repository.IImportantDetailFeedRepository
import com.jccd.monitoringsystem.db.repository.ImportantDetailFeedRepository
import com.jccd.monitoringsystem.ui.importants.detail.ImportantDetailMVP
import com.jccd.monitoringsystem.utils.Constants
import com.jccd.monitoringsystem.utils.ValidateFields


class ImportantDetailPresenter(val view: ImportantDetailMVP.view) : ImportantDetailMVP.presenter {

    private val context = MonitoringSystem.sInstance.getContext()
    private var repository: IImportantDetailFeedRepository = ImportantDetailFeedRepository(this)

    override fun setDataToLoad() {
        view.showProgressView()
        if (ValidateFields().isNetworkAvailable(context!!)){
            val importantFeed = view.getData()
            val validateComment = ValidateFields().validateEmptyField(importantFeed.comment)
            if(validateComment == Constants.CORRECT_DATA){
                repository.saveImportantFeed(importantFeed)
            }else{
                view.setEtCommentError().error = context.getString(R.string.empty_field)
                view.hideProgressView()
            }
        }else{
            view.hideProgressView()
            Toast.makeText(context,context.getString(R.string.check_internet_connection),Toast.LENGTH_SHORT).show()
        }
    }
    override fun hideProgressView() {
       view.hideProgressView()
    }

    override fun finishActivity() {
        view.finishActivity()
    }
}