package com.codeccc.animation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    val menus = mutableListOf<String>().apply {
        add("属性动画")
        add("可绘制图形动画")
        add("使用动画显示隐藏视图")
        add("使用动画移动视图")
        add("使用Fling动画移动视图")
        add("缩放动画")
        add("弹簧动画")
        add("自动为布局更新添加动画")
        add("过渡效果动画")
        add("创建自定义过渡动画")
        add("使用动画启动Activity")
        add("Viewpager动画")
        add("Viewpager2动画")
        add("从ViewPager迁移到ViewPager2")
    }

}