package com.codeccc.animation.ui.transition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.codeccc.animation.databinding.ActivitySpringBinding
import com.codeccc.animation.databinding.ActivityTransitionABinding
import com.codeccc.animation.databinding.ActivityTransitionBBinding

/**
 * Author : wangbo
 * Date : 2021/8/11
 * Function : TODO 请在这里输入文件用途
 * Desc :
 */
class TransitionBActivity : AppCompatActivity() {

    private var _binding: ActivityTransitionBBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTransitionBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //设置共享元素名称
        ViewCompat.setTransitionName(binding.thumb, SHARED_IMAGE)
        ViewCompat.setTransitionName(binding.txtName, SHARED_NAME)


    }

    override fun onBackPressed() {
        finishAfterTransition()
    }

    companion object {
        const val SHARED_IMAGE = "detail:image"
        const val SHARED_NAME = "detail:name"
    }

}