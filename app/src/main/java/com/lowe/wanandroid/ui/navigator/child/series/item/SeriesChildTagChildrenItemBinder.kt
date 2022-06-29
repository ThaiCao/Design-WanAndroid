package com.lowe.wanandroid.ui.navigator.child.series.item

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import com.lowe.multitype.ItemViewBinder
import com.lowe.wanandroid.R
import com.lowe.wanandroid.base.binder.ViewBindingHolder
import com.lowe.wanandroid.databinding.ItemNavigatorChildTagChildrenLayoutBinding
import com.lowe.wanandroid.services.model.Classify
import com.lowe.wanandroid.services.model.Series
import com.lowe.wanandroid.utils.dp
import com.lowe.wanandroid.utils.dpF
import com.lowe.wanandroid.utils.fromHtml
import com.lowe.wanandroid.utils.setRippleBackground

class SeriesChildTagChildrenItemBinder(private val onTagChildrenClick: (Classify) -> Unit) :
    ItemViewBinder<Series, ViewBindingHolder<ItemNavigatorChildTagChildrenLayoutBinding>>() {
    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewBindingHolder<ItemNavigatorChildTagChildrenLayoutBinding> {
        return ViewBindingHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_navigator_child_tag_children_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ViewBindingHolder<ItemNavigatorChildTagChildrenLayoutBinding>,
        item: Series
    ) {
        holder.binding.apply {
            name = item.name
            executePendingBindings()
            tagChildrenLayout.removeAllViews()
            item.children.forEach { classify ->
                val tv = generateTagChildrenTextView(
                    this.root.context,
                    ViewGroup.MarginLayoutParams(
                        ViewGroup.MarginLayoutParams.WRAP_CONTENT,
                        ViewGroup.MarginLayoutParams.WRAP_CONTENT
                    )
                )
                tv.text = classify.name.fromHtml()
                tv.setOnClickListener { onTagChildrenClick(classify) }
                tagChildrenLayout.addView(tv)
            }
        }
    }

    private fun generateTagChildrenTextView(
        context: Context,
        layoutParams: ViewGroup.MarginLayoutParams
    ) = with(TextView(context, null, android.R.attr.textViewStyle)) {
        layoutParams.setMargins(6.dp)
        this.layoutParams = layoutParams
        this.gravity = Gravity.CENTER
        this.setPadding(6.dp)
        this.textSize = 13F
        this.setTextColor(context.getColor(R.color.md_theme_dark_surfaceVariant))
        setRippleBackground(
            GradientDrawable().also {
                it.cornerRadius = 8.dpF
                it.setColor(context.getColor(R.color.backgroundContainer))
            },
            cornerRadius = 8.dpF
        )
        this
    }
}