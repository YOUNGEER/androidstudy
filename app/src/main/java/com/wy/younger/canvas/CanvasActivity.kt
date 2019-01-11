package com.wy.younger.canvas

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.wy.younger.PageFragment
import com.wy.younger.R
import kotlinx.android.synthetic.main.activity_tablayout.*

class CanvasActivity : AppCompatActivity() {

    var datas = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)

        datas.add(Model(R.layout.canvas_drawview, "drawview"))
        datas.add(Model(R.layout.canvas_clip, "clip"))
        datas.add(Model(R.layout.canvas_translate, "translate"))
        datas.add(Model(R.layout.canvas_matrix, "matrix"))
        datas.add(Model(R.layout.canvas_camera, "camera"))
        datas.add(Model(R.layout.canvas_flipboard, "flipboard"))
        datas.add(Model(R.layout.canvas_region, "region"))
        datas.add(Model(R.layout.canvas_region_demo, "demo"))
        datas.add(Model(R.layout.canvas_region_demo_2, "demo_2"))



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
