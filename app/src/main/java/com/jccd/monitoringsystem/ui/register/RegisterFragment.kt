package com.jccd.monitoringsystem.ui.register

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.jccd.monitoringsystem.R
import com.jccd.monitoringsystem.utils.Constants


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar!!.title = Constants.EMPTY
        (activity as AppCompatActivity).supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor
            (R.color.unibague_blue)))

        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
