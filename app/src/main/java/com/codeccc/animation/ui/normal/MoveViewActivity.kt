package com.codeccc.animation.ui.normal

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.PathInterpolator
import androidx.annotation.InterpolatorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.animation.PathInterpolatorCompat
import com.codeccc.animation.R
import com.codeccc.animation.databinding.ActivityMoveViewBinding
import com.codeccc.animation.databinding.ActivityShowOrHideBinding

/**
 * Author : wangbo
 * Date : 2021/8/10
 * Function : TODO 请在这里输入文件用途
 * Desc : 使用动画移动视图
 */
class MoveViewActivity : AppCompatActivity() {

    private var _binding: ActivityMoveViewBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMoveViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnStart.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val path = Path().apply {
                        arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
                    }
                    val animator =
                        ObjectAnimator.ofFloat(animationView, View.X, View.Y, path).apply {
                            duration = 2000
                            start()
                        }
                }
            }

            btnStart2.setOnClickListener {
                //使用系统PathInterpolator执行动画
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val path = Path().apply {
                        arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
                    }
                    //fast_out_linear_in 快出平入
                    //fast_out_slow_in  快出慢入
                    //linear_out_slow_in 平出慢入
                    val pathInterpolator = AnimationUtils.loadInterpolator(
                        this@MoveViewActivity,
                        com.google.android.material.R.interpolator.fast_out_slow_in
                    )
                    val animator =
                        ObjectAnimator.ofFloat(animationView, View.X, View.Y, path).apply {
                            interpolator = pathInterpolator
                            duration = 2000
                            start()
                        }
                } else {
                    // Create animator without using curved path
                }
            }
        }


    }

}