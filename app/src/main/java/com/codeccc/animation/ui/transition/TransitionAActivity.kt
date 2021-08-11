package com.codeccc.animation.ui.transition

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.codeccc.animation.databinding.ActivityTransitionABinding

/**
 * Author : wangbo
 * Date : 2021/8/11
 * Function : TODO 请在这里输入文件用途
 * Desc :
 */
class TransitionAActivity : AppCompatActivity() {

    private var _binding: ActivityTransitionABinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTransitionABinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnJump.setOnClickListener {
            val intent = Intent(it.context, TransitionBActivity::class.java)
            //设置共享元素及名称
            val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this@TransitionAActivity,
                Pair(binding.thumb, TransitionBActivity.SHARED_IMAGE),
                Pair(binding.txtName, TransitionBActivity.SHARED_NAME)
            )
            //使用过渡效果启动activity
            ActivityCompat.startActivity(
                this@TransitionAActivity,
                intent,
                activityOptions.toBundle()
            )

        }
    }

}