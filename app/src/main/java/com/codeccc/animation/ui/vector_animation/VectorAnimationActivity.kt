package com.codeccc.animation.ui.vector_animation

import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.codeccc.animation.R
import com.codeccc.animation.databinding.ActivityAnimationBinding
import com.codeccc.animation.databinding.ActivityVectorAnimationBinding

/**
 * Author : wangbo
 * Date : 2021/8/9
 * 创建使用VectorDrawable动画步骤:
 * 1. 在res/drawable目录下创建根节点为vector类型的drawable，指定<group>及<path>节点名称
 * 2. 在res/drawable目录下创建根节点为animated-vector类型的drawable，指定android:drawable属性值
 * 为第一步所创建的drawable，然后在自己点中创建<target>标签，并设置tatget标签属性名称为第一步所创建的drawable
 * 的节点的名称，并设置android:animation属性为所引用的动画
 * 3. 在res/animator目录下创建上一步骤所使用的动画，可以使objectAnimator或者valueAnimator或者是animatorSet组合
 * 4. 在代码中使用AnimatedVectorDrawableCompat类调用create方法使用上文创建的animated-vector，将其作为背景设置到view上
 */
class VectorAnimationActivity : AppCompatActivity() {

    private var _binding: ActivityVectorAnimationBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityVectorAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //设置vector动画并执行
        binding.animationView.background =
            AnimatedVectorDrawableCompat.create(this, R.drawable.animatorvectordrawable)?.apply {
                start()
            }

        binding.animationView.setOnClickListener {
            //执行动画
            (binding.animationView.background as AnimatedVectorDrawableCompat).start()
        }
    }

}