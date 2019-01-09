package com.wy.younger.animator

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.wy.younger.PageFragment
import com.wy.younger.R
import kotlinx.android.synthetic.main.activity_tablayout.*

class AnimatorActivity : AppCompatActivity() {

    var datas = ArrayList<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)

        datas.add(Model(R.layout.animator_valueanimator1, "AnimatorSize"))
        datas.add(Model(R.layout.animator_valueanimator2, "AnimatorColor"))
        datas.add(Model(R.layout.animator_ballrun, "ballrun1"))
        datas.add(Model(R.layout.animator_ballrun2, "ballrun2"))
        datas.add(Model(R.layout.animator_ballrun3, "ballrun3"))


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
