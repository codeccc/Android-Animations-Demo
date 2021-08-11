package com.codeccc.animation.ui.scene

import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.*
import com.codeccc.animation.R
import com.codeccc.animation.databinding.ActivitySceneBinding

/**
 * Author : wangbo
 * Date : 2021/8/10
 * Function : TODO 请在这里输入文件用途
 * Desc : 使用过渡场景动画
 */
class SceneActivity : AppCompatActivity() {

    private var _binding: ActivitySceneBinding? = null

    private val binding get() = _binding!!

    private var curScene: Scene? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySceneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val aScene = Scene.getSceneForLayout(binding.sceneRoot, R.layout.a_scene, this)
        val anotherScene = Scene.getSceneForLayout(binding.sceneRoot, R.layout.another_scene, this)

        curScene = aScene
        //加载资源文件Transition
        var fadeTransition: Transition =
            TransitionInflater.from(this)
                .inflateTransition(R.transition.fade_transition)

        //代码创建Transition
        val autoTransition: Transition = AutoTransition()

        binding.btnStart.setOnClickListener {
            //执行场景过渡
            if (curScene == aScene) {
                TransitionManager.go(anotherScene, autoTransition)
                curScene = anotherScene
            } else {
                TransitionManager.go(aScene, autoTransition)
                curScene = aScene
            }


        }

        binding.btnStart2.setOnClickListener {
            //加载xml中定义的TransitionSet
            val transitionSet =
                TransitionInflater.from(this).inflateTransition(R.transition.fade_transition_set)
            if (curScene == aScene) {
                TransitionManager.go(anotherScene, transitionSet)
                curScene = anotherScene
            } else {
                TransitionManager.go(aScene, transitionSet)
                curScene = aScene
            }
        }

        binding.btnStart3.setOnClickListener {
            //应用没有场景的过渡
            val newText = TextView(this).apply {
                text = "没有场景的过渡示例"
                id = R.id.text
            }
            //创建过渡效果Transition
            val fade = Fade(Fade.IN)
            //设置场景父容器及transition
            TransitionManager.beginDelayedTransition(binding.container, fade)
            //添加view
            binding.container.addView(newText)
        }

    }

}