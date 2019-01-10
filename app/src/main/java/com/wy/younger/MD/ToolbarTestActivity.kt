package com.wy.younger.MD

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.wy.younger.R

/**
 *@package:com.wy.younger.MD
 *@data on:2019/1/10 11:43
 *author:YOUNG
 *desc:TODO
 */
class ToolbarTestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbartest)

    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


}