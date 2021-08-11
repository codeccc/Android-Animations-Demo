package com.codeccc.animation.ui.spring

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.dynamicanimation.animation.SpringForce.DAMPING_RATIO_HIGH_BOUNCY
import com.codeccc.animation.databinding.ActivityMoveViewBinding
import com.codeccc.animation.databinding.ActivitySpringBinding

/**
 * Author : wangbo
 * Date : 2021/8/10
 * Function : TODO 请在这里输入文件用途
 * Desc :
 *
 * Doc Link: https://developer.android.com/guide/topics/graphics/spring-animation?hl=zh-cn#creating-spring-animation
 */
class SpringActivity : AppCompatActivity() {

    private var _binding: ActivitySpringBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySpringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnStart.setOnClickListener {
                //缩放弹簧动画
                SpringAnimation(
                    thumbView,
                    DynamicAnimation.SCALE_X,
                    thumbView.scaleX + 0.1f
                ).apply {
                    //设置阻尼比
                    //当阻尼比大于 1 时，会出现过阻尼现象。它会使对象快速地返回到静止位置。
                    //当阻尼比等于 1 时，会出现临界阻尼现象。这会使对象在最短时间内返回到静止位置。
                    //当阻尼比小于 1 时，会出现欠阻尼现象。这会使对象多次经过并越过静止位置，然后逐渐到达静止位置。
                    //当阻尼比等于零时，便会出现无阻尼现象。这会使对象永远振动下去。
                    spring.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                    addUpdateListener { animation, value, velocity ->
                        //动画更新监听
                    }
                    addEndListener { animation, canceled, value, velocity ->
                        //结束监听
                    }
                    start()
                }
            }

            btnStart2.setOnClickListener {
                //平移弹簧动画
                SpringAnimation(
                    thumbView,
                    DynamicAnimation.TRANSLATION_Y,
                    thumbView.translationY + 50f
                ).apply {
                    //设置阻尼比
                    //当阻尼比大于 1 时，会出现过阻尼现象。它会使对象快速地返回到静止位置。
                    //当阻尼比等于 1 时，会出现临界阻尼现象。这会使对象在最短时间内返回到静止位置。
                    //当阻尼比小于 1 时，会出现欠阻尼现象。这会使对象多次经过并越过静止位置，然后逐渐到达静止位置。
                    //当阻尼比等于零时，便会出现无阻尼现象。这会使对象永远振动下去。
                    spring.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
                    //设置弹簧刚度,刚度越大,回弹弹性越小
                    spring.setStiffness(SpringForce.STIFFNESS_MEDIUM)
                    addUpdateListener { animation, value, velocity ->
                        //动画更新监听
                    }
                    addEndListener { animation, canceled, value, velocity ->
                        //结束监听
                    }
                    start()
                }
            }
        }
    }
}