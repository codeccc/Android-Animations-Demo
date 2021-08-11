package com.codeccc.animation.ui.normal

import android.animation.*
import android.os.Bundle
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import com.codeccc.animation.R
import com.codeccc.animation.databinding.ActivityAnimationBinding

/**
 * Author : wangbo
 * Date : 2021/8/6
 * Function :
 * Desc :
 */
class AnimationActivity : AppCompatActivity() {

    private var _binding: ActivityAnimationBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnAnim1.setOnClickListener {
                //缩放动画
                //使用ValueAnimator实现缩放动画
                val valueAnim = ValueAnimator.ofFloat(1f, 1.5f, 0.5f, 1f)
                valueAnim.setInterpolator { input ->

                    return@setInterpolator (Math.cos((input + 1) * Math.PI) / 2.0f).toFloat() + 0.5f
                }
                valueAnim.addUpdateListener {
                    val scale = it.animatedValue as Float
                    animationView.scaleX = scale
                    animationView.scaleY = scale
                }
                valueAnim.setDuration(500)
                valueAnim.start()
            }

            btnAnim2.setOnClickListener {
                //平移动画动画
                val transAnim1 = ValueAnimator.ofFloat(0f, 100f, 200f, 100f, 0f)
                transAnim1.addUpdateListener {
                    val distance = it.animatedValue as Float
                    animationView.translationX = distance
                    animationView.translationY = distance
                }

                val transAnim2 = ValueAnimator.ofFloat(0f, -100f, -200f, -100f, 0f)
                transAnim2.addUpdateListener {
                    val distance = it.animatedValue as Float
                    animationView.translationX = distance
                    animationView.translationY = distance
                }
                //使用AnimatorSet处理动画播放顺序
                val animatorSet = AnimatorSet().apply {
                    play(transAnim1).before(transAnim2)
                }
                animatorSet.interpolator = AccelerateDecelerateInterpolator()
                animatorSet.duration = 400
                animatorSet.start()
            }

            btnAnim3.setOnClickListener {
                //旋转动画
                animationView.animate()
                    .rotation(animationView.rotation + 30f)
                    //越界回弹差值器
                    .setInterpolator(AnticipateOvershootInterpolator())
                    .setDuration(500)
                    .start()
            }

            btnAnim4.setOnClickListener {
                //淡入淡出动画
                val alphaAnimator = ValueAnimator.ofFloat(0f, 1f, 0f, 1f)
                alphaAnimator.interpolator = AccelerateDecelerateInterpolator()
                alphaAnimator.duration = 1000
                alphaAnimator.addUpdateListener { animationView.alpha = it.animatedValue as Float }
                alphaAnimator.start()
            }

            btnAnim5.setOnClickListener {
                //组合动画 缩放+平移+旋转+淡入
                //设置初始状态
                animationView.scaleX = 0.5f
                animationView.scaleY = 0.5f
                animationView.alpha = 0f
                animationView.rotation = 0f
                animationView.animate()
                    .translationY(100f)
                    .scaleX(1.5f).scaleY(1.5f)
                    .alpha(1f)
                    .rotation(360f)
                    .setDuration(500)
                    .setInterpolator(AnticipateOvershootInterpolator())
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            //回到动画初始状态
                            animationView.scaleX = 0.5f
                            animationView.scaleY = 0.5f
                            animationView.alpha = 1f
                            animationView.rotation = 0f

                            //动画结束移除监听
                            animation?.removeListener(this)
                        }
                    })
                    .start()
            }
            btnAnim6.setOnClickListener {
                //关键帧动画
                val kf0 = Keyframe.ofFloat(0f, 0f)
                val kf1 = Keyframe.ofFloat(.5f, 360f)
                val kf2 = Keyframe.ofFloat(1f, 0f)
                val pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2)
                val animator =
                    ObjectAnimator.ofPropertyValuesHolder(animationView, pvhRotation).apply {
                        duration = 2000
                    }
                animator.interpolator = AccelerateInterpolator()
                animator.start()
            }

            btnAnim7.setOnClickListener {
                //使用ObjectAnimator一行代码执行动画
                ObjectAnimator.ofFloat(animationView, "rotation", 0f, 360f)
                    .setDuration(500)
                    .start()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        // 播放默认view动画
        (AnimatorInflater.loadAnimator(this, R.animator.animator_enter_default) as AnimatorSet)
            .apply {
                //设置动画目标view
                setTarget(binding.animationView)
                start()
            }
    }
}