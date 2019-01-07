package com.wy.younger

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PageFragment : Fragment() {

    var layout: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(layout, container, false)

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            layout = args.getInt("layout")
        }
    }

    companion object {

        fun newInstance(@LayoutRes sampleLayoutRes: Int): PageFragment {
            val fragment = PageFragment()
            val args = Bundle()
            args.putInt("layout", sampleLayoutRes)
            fragment.arguments = args
            return fragment
        }
    }
}
