package com.codeccc.animation.ui.normal

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.codeccc.animation.databinding.ActivityShowOrHideBinding
import com.google.android.material.circularreveal.CircularRevealCompat
import com.google.android.material.circularreveal.CircularRevealHelper
import com.google.android.material.circularreveal.cardview.CircularRevealCardView

/**
 * Author : wangbo
 * Date : 2021/8/9
 * Function : TODO 请在这里输入文件用途
 * Desc : 使用动画显示或隐藏视图
 */
class ShowOrHideActivity : AppCompatActivity() {

    private var _binding: ActivityShowOrHideBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityShowOrHideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //初始化被隐藏的布局为INVISIBLE
        binding.cardView.visibility = View.INVISIBLE
        binding.cardView2.visibility = View.INVISIBLE

        //设置切换事件
        binding.btnStart.setOnClickListener {
//            if (binding.cardView.visibility == View.VISIBLE) {
//                hide(binding.cardView)
//            } else {
//                show(binding.cardView)
//            }

            if (binding.cardView2.visibility == View.VISIBLE) {
                val cx = binding.cardView2.width / 2f
                val cy = binding.cardView2.height / 2f
                val radius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
                val anim =
                    CircularRevealCompat.createCircularReveal(binding.cardView2, cx, cy, radius, 0f)
                anim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        binding.cardView2.visibility = View.INVISIBLE
                    }
                })
                anim.start()
            } else {
                val cx = binding.cardView2.width / 2f
                val cy = binding.cardView2.height / 2f
                val radius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
                val anim =
                    CircularRevealCompat.createCircularReveal(binding.cardView2, cx, cy, 0f, radius)
                binding.cardView2.visibility = View.VISIBLE
                anim.start()
            }
        }

        binding.cardView2.buildCircularRevealCache()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.cardView2.destroyCircularRevealCache()
    }

    private fun show(v: View) {
        v.visibility = View.INVISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val cx = v.width / 2
            val cy = v.height / 2

            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

            //创建圆形揭露动画
            //第一个参数: 作用对象
            //第二个和第三个参数: 裁剪圆形x和y坐标
            //第四个参数: 初始半径为0,所以会隐藏要显示的视图
            //第五个参数: 圆形的最终半径
            val anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, finalRadius)
            anim.duration = 500
            v.visibility = View.VISIBLE
            anim.start()
        } else {
            v.visibility = View.INVISIBLE
        }
    }

    private fun hide(v: View) {
        v.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val cx = v.width / 2
            val cy = v.height / 2

            //获取裁剪圆的初始半径
            val initialRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

            //创建圆形揭露动画
            //第一个参数: 作用对象
            //第二个和第三个参数: 裁剪圆形x和y坐标
            //第四个参数: 初始半径为0,所以会隐藏要显示的视图
            //第五个参数: 圆形的最终半径
            val anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, initialRadius, 0f)

            anim.duration = 500
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    //动画结束,隐藏视图
                    v.visibility = View.INVISIBLE
                }
            })

            anim.start()
        } else {
            v.visibility = View.VISIBLE
        }
    }
}