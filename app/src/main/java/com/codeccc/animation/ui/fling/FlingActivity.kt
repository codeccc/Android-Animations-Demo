package com.codeccc.animation.ui.fling

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import com.codeccc.animation.databinding.ActivityFlingBinding

/**
 * Author : wangbo
 * Date : 2021/8/10
 * Function : TODO 请在这里输入文件用途
 * Desc : TODO 请在这里输入文件描述
 */
class FlingActivity : AppCompatActivity() {


    private var _binding: ActivityFlingBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityFlingBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.apply {
            btnStart.setOnClickListener {
                //创建Fling动画
                val fling = FlingAnimation(animationView, DynamicAnimation.TRANSLATION_Y)
                //设置速度,单位px/s
                fling.setStartVelocity(1000f)
                fling.setStartValue(0f)
                //设置摩擦力,可降低动画速度
                fling.setFriction(0.5f)
                //执行fling动画
                fling.start()
            }
        }
    }

}