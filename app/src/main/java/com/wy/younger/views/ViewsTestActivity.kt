package com.wy.younger.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.wy.younger.PageFragment
import com.wy.younger.R
import kotlinx.android.synthetic.main.activity_tablayout.*

/**
 *@package:com.wy.younger.MD
 *@data on:2019/1/10 11:43
 *author:YOUNG
 *desc:自定义view
 */
class ViewsTestActivity : AppCompatActivity() {

    var datas = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)

        datas.add(Model(R.layout.views_mutilpoint_scale, "mutilpoint_scale"))



        viewpager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                return PageFragment.newInstance(datas[p0].resorceLayout)
            }

            override fun getCount(): Int {
                return datas.size
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return datas[position].title
            }
        }

        tablayout.setupWithViewPager(viewpager)

    }


    data class Model(var resorceLayout: Int, var title: String)


}