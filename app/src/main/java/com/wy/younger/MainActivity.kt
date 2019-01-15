package com.wy.younger

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wy.younger.Bitmap.BitmapActivity
import com.wy.younger.MD.ToolbarTestActivity
import com.wy.younger.animator.AnimatorActivity
import com.wy.younger.canvas.CanvasActivity
import com.wy.younger.paint.PaintActivity
import com.wy.younger.path.PathActivity
import com.wy.younger.views.ViewsTestActivity
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
        tv_bitmap.setOnClickListener {
            startActivity(Intent(this, BitmapActivity::class.java))
        }
        tv_MD_toolbar.setOnClickListener {
            startActivity(Intent(this, ToolbarTestActivity::class.java))
        }
        tv_views.setOnClickListener {
            startActivity(Intent(this, ViewsTestActivity::class.java))
        }

    }
}
