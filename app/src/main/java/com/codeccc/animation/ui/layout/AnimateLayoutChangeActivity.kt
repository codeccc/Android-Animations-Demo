package com.codeccc.animation.ui.layout

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.codeccc.animation.databinding.ActivityAnimateLayoutChangeBinding
import com.codeccc.animation.databinding.ActivitySpringBinding
import com.google.android.material.button.MaterialButton

/**
 * Author : wangbo
 * Date : 2021/8/10
 * Function : TODO 请在这里输入文件用途
 * Desc : TODO 请在这里输入文件描述
 */
class AnimateLayoutChangeActivity : AppCompatActivity() {

    private var _binding: ActivityAnimateLayoutChangeBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAnimateLayoutChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var i = 1

        binding.apply {
            btnAdd.setOnClickListener {
                val textView = TextView(it.context)
                textView.text = "我是第${i}个被添加的view"
                binding.root.addView(textView, 0)
                i++
            }

            btnRemove.setOnClickListener {
                val firstView = binding.root.getChildAt(0)
                if (firstView is MaterialButton) {
                    Toast.makeText(it.context, "请先添加View", Toast.LENGTH_SHORT).show()
                } else {
                    binding.root.removeView(firstView)
                }
            }
        }
    }
}