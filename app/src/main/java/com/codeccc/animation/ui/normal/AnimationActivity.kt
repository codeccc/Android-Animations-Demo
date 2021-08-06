package com.codeccc.animation.ui.normal

import android.animation.Animator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import com.codeccc.animation.databinding.ActivityAnimationBinding

/**
 * Author : wangbo
 * Date : 2021/8/6
 * Function : TODO 请在这里输入文件用途
 * Desc : TODO 请在这里输入文件描述
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
                val valueAnim = ValueAnimator.ofFloat(1f, 1.5f, 0.5f, 1f)
                valueAnim.setInterpolator { input ->

                    return@setInterpolator (Math.cos((input + 1) * Math.PI) / 2.0f).toFloat() + 0.5f
                }
                valueAnim.setEvaluator { fraction, startValue, endValue ->
//                    btnAnim1.scaleX = startValue
//                    btnAnim1.scaleY = startValue
                }
                valueAnim.setDuration(500)
                valueAnim.start()
            }

            btnAnim2.setOnClickListener {
                //平移动画动画
                btnAnim2.animate()
                    .translationY(300f)
                    .setDuration(500)
                    .setInterpolator(OvershootInterpolator(2f))
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
                        }

                        override fun onAnimationStart(animation: Animator?) {
                        }

                        override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            //回到原有位置
//                            btnAnim2.animate()
//                                .translationY(0f)
//                                .setDuration(500)
//                                .setInterpolator(BounceInterpolator())
//                                .setStartDelay(2000)
//                                .start()
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                        }

                        override fun onAnimationRepeat(animation: Animator?) {
                        }
                    })
                    .start()
            }

            btnAnim3.setOnClickListener {
                //旋转动画
            }

            btnAnim4.setOnClickListener {
                //淡入淡出动画
            }

            btnAnim5.setOnClickListener {
                //组合动画
                btnAnim5.scaleX = 0.5f
                btnAnim5.scaleY = 0.5f
                btnAnim5.alpha = 0f
                btnAnim5.rotation = 0f
                btnAnim5.animate()
                    .translationY(100f)
                    .scaleX(1.5f).scaleY(1.5f)
                    .alpha(1f)
                    .rotation(360f)
                    .setDuration(500)
                    .setInterpolator(AccelerateDecelerateInterpolator())
                    .start()
            }
        }

    }
}