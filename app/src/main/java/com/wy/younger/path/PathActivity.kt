package com.wy.younger.path

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.wy.younger.PageFragment
import com.wy.younger.R
import kotlinx.android.synthetic.main.activity_tablayout.*

class PathActivity : AppCompatActivity() {

    var datas = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)

        datas.add(Model(R.layout.paint_layout, "paintLayout"))
        datas.add(Model(R.layout.paint_porterduff_mode, "porterduffMode"))
        datas.add(Model(R.layout.paint_colorfilter, "colorfilter"))
        datas.add(Model(R.layout.paint_xfermode, "xfermode"))
        datas.add(Model(R.layout.paint_stroke, "stroke"))
        datas.add(Model(R.layout.paint_patheffect, "patheffect"))
        datas.add(Model(R.layout.paint_maskfilter, "maskfilter"))
        datas.add(Model(R.layout.paint_text, "painttext"))

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
