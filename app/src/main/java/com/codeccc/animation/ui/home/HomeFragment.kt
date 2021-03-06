package com.codeccc.animation.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.codeccc.animation.R
import com.codeccc.animation.databinding.FragmentHomeBinding
import com.codeccc.animation.ui.normal.AnimationActivity
import com.codeccc.animation.ui.fling.FlingActivity
import com.codeccc.animation.ui.layout.AnimateLayoutChangeActivity
import com.codeccc.animation.ui.normal.MoveViewActivity
import com.codeccc.animation.ui.normal.ShowOrHideActivity
import com.codeccc.animation.ui.scene.SceneActivity
import com.codeccc.animation.ui.scene.SceneCustomActivity
import com.codeccc.animation.ui.spring.SpringActivity
import com.codeccc.animation.ui.transition.TransitionAActivity
import com.codeccc.animation.ui.vector.VectorAnimationActivity
import com.codeccc.animation.ui.zoom.ZoomActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rcyList.adapter = MyListAdapter(homeViewModel.menus, mOnClickListener)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val mOnClickListener = object : View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.txtItem -> {
                    val pos = v.tag as Int
                    when (pos) {
                        0 -> {
                            startActivity(Intent(context, AnimationActivity::class.java))
                        }
                        1 -> {
                            startActivity(Intent(context, VectorAnimationActivity::class.java))
                        }
                        2 -> {
                            startActivity(Intent(context, ShowOrHideActivity::class.java))
                        }
                        3 -> {
                            startActivity(Intent(context, MoveViewActivity::class.java))
                        }
                        4 -> {
                            startActivity(Intent(context, FlingActivity::class.java))
                        }
                        5 -> {
                            startActivity(Intent(context, ZoomActivity::class.java))
                        }
                        6 -> {
                            startActivity(Intent(context, SpringActivity::class.java))
                        }
                        7 -> {
                            startActivity(Intent(context, AnimateLayoutChangeActivity::class.java))
                        }
                        8 -> {
                            startActivity(Intent(context, SceneActivity::class.java))
                        }
                        9 -> {
                            startActivity(Intent(context, SceneCustomActivity::class.java))
                        }
                        10 -> {
                            startActivity(Intent(context, TransitionAActivity::class.java))
                        }
                    }
                }
            }
        }
    }

    class MyListAdapter(
        private val mDatas: MutableList<String>,
        private val mOnClickListener: View.OnClickListener
    ) :
        RecyclerView.Adapter<MyListAdapter.ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_menu, null)
            layout.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            return ViewHolder(layout)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.txtItem?.text = mDatas[position]
            holder.txtItem?.tag = position
            holder.txtItem?.setOnClickListener(mOnClickListener)
        }

        override fun getItemCount(): Int = mDatas.size

        inner class ViewHolder(layout: View) : RecyclerView.ViewHolder(layout) {

            var txtItem: TextView? = null

            init {
                txtItem = layout.findViewById(R.id.txtItem)
            }

        }
    }
}