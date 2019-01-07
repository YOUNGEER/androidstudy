package com.wy.younger

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wy.younger.animator.AnimatorActivity
import com.wy.younger.canvas.CanvasActivity
import com.wy.younger.paint.PaintActivity
import com.wy.younger.path.PathActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_paint.setOnClickListener {
            startActivity(Intent(this, PaintActivity::class.java))
        }

        tv_animator.setOnClickListener {
            startActivity(Intent(this, AnimatorActivity::class.java))
        }
        tv_path.setOnClickListener {
            startActivity(Intent(this, PathActivity::class.java))
        }

        tv_canvas.setOnClickListener {
            startActivity(Intent(this, CanvasActivity::class.java))
        }
    }
}
