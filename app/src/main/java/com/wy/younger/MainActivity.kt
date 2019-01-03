package com.wy.younger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.wy.younger.animator.AnimatorActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tv_animator.setOnClickListener {
            startActivity(Intent(this, AnimatorActivity::class.java))
        }
    }
}
