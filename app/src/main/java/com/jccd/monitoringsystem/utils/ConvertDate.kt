package com.jccd.monitoringsystem.utils

import android.annotation.SuppressLint
import com.jccd.monitoringsystem.MonitoringSystem
import com.jccd.monitoringsystem.R
import java.text.SimpleDateFormat
import java.util.*

class ConvertDate(private val date: String) {
    var month = ""
    var day = ""
    val context = MonitoringSystem.sInstance.getContext()!!

    fun converToDateColombian(): String {
        val strDate = date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val date = dateFormat.parse(strDate)
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR).toString()
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH).toString()
        when (date.month) {
            0 -> month = context.getString(R.string.january)
            1 -> month = context.getString(R.string.february)
            2 -> month = context.getString(R.string.march)
            3 -> month = context.getString(R.string.april)
            4 -> month = context.getString(R.string.may)
            5 -> month = context.getString(R.string.june)
            6 -> month = context.getString(R.string.july)
            7 -> month = context.getString(R.string.august)
            8 -> month = context.getString(R.string.september)
            9 -> month = context.getString(R.string.october)
            10 -> month = context.getString(R.string.november)
            11 -> month = context.getString(R.string.december)
        }

        when (date.day) {
            0 -> day = context.getString(R.string.sunday)
            1 -> day = context.getString(R.string.monday)
            2 -> day = context.getString(R.string.thuesday)
            3 -> day = context.getString(R.string.wednesday)
            4 -> day = context.getString(R.string.thursday)
            5 -> day = context.getString(R.string.friday)
            6 -> day = context.getString(R.string.saturday)
        }

        return String.format(
            context.getString(R.string.main_date_format), day, dayOfMonth,
            month, year, date.hours.toString(), date.minutes.toString(), date.seconds
        )
    }

    companion object {

        @SuppressLint("SimpleDateFormat")
        fun converToDateColombian(date: String): String{
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date)
            return SimpleDateFormat("EEEE, d MMMM yyyy HH:mm:ss ").format(dateFormat).capitalize()
        }

    }

    fun convertDateToList(): String {
        val strDate = date
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val date = dateFormat.parse(strDate)
        val calendar: Calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR).toString()
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH).toString()
        val monthOfYear = date.month + 1

        return String.format(
            context.getString(R.string.list_date_format), year, monthOfYear,
            dayOfMonth, date.hours, date.minutes, date.seconds
        )
    }
    fun String.capitalizeWords(): String = split(" ").map { it.capitalize() }.joinToString(" ")
}