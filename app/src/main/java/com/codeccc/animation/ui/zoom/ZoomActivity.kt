package com.codeccc.animation.ui.zoom

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.codeccc.animation.R
import com.codeccc.animation.databinding.ActivityVectorAnimationBinding
import com.codeccc.animation.databinding.ActivityZoomBinding

/**
 * Author : wangbo
 * Date : 2021/8/10
 * Function : TODO 请在这里输入文件用途
 * Desc : TODO 请在这里输入文件描述
 */
class ZoomActivity : AppCompatActivity() {

    private var _binding: ActivityZoomBinding? = null

    private val binding get() = _binding!!

    private var currentAnimator: Animator? = null
    private var shortAnimationDuration: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityZoomBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.apply {
            thumbButton1.setOnClickListener {
                zoomImageFromThumb(thumbButton1, R.mipmap.image1)
            }
        }
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
    }

    private fun zoomImageFromThumb(thumbView: View, imageResId: Int) {
        // 如果有正在进行的动画，请立即取消它并继续执行此动画。
        currentAnimator?.cancel()

        // 加载高分辨率的“放大”图像。
        val expandedImageView: ImageView = binding.expandedImage
        expandedImageView.setImageResource(imageResId)

        // 计算放大图像的开始和结束边界。
        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        // 起始边界是缩略图的全局可见矩形，最终边界是容器视图的全局可见矩形。
        // 还将容器视图的偏移量设置为边界的原点，因为这是定位动画属性（X，Y）的原点。
        thumbView.getGlobalVisibleRect(startBoundsInt)
        binding.container.getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        // 使用“中心裁剪”技术将起始边界调整为与最终边界相同的纵横比。
        // 这可以防止在动画过程中出现不希望的拉伸。还要计算开始比例因子（结束比例因子始终为 1.0）。
        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // 水平扩展起始边界
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // 垂直扩展起始边界
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        // 隐藏缩略图并显示放大视图。当动画开始时，它会将放大的视图定位在缩略图的位置。
        thumbView.alpha = 0f
        expandedImageView.visibility = View.VISIBLE

        // 将 SCALE_X 和 SCALE_Y 转换的轴心点设置为放大视图的左上角（默认为视图的中心）。
        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        // 构建并运行四个平移和缩放属性（X、Y、SCALE_X 和 SCALE_Y）的并行动画。
        currentAnimator = AnimatorSet().apply {
            play(
                ObjectAnimator.ofFloat(
                    expandedImageView,
                    View.X,
                    startBounds.left,
                    finalBounds.left
                )
            ).apply {
                with(
                    ObjectAnimator.ofFloat(
                        expandedImageView,
                        View.Y,
                        startBounds.top,
                        finalBounds.top
                    )
                )
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }
            })
            start()
        }

        // 单击放大的图像后，它应该缩小到原始边界并显示缩略图而不是放大的图像。
        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()

            // 将四个positioning/sizing 属性并行设置为动画，返回到它们的原始值。
            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = View.GONE
                        currentAnimator = null
                    }
                })
                start()
            }
        }
    }

}