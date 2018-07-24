package com.my.heartace.view.widget.group

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/7/24 0024.
 */
class WrapContentScrollView constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ScrollView(context, attrs, defStyleAttr) {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private var maxHeight: Int? = null

    fun setMaxHeight(maxHeight: Int) {
        if (this.maxHeight != maxHeight) {
            this.maxHeight = maxHeight
            requestLayout()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (maxHeight != null) {
            val heispec = MeasureSpec.makeMeasureSpec(maxHeight!!, MeasureSpec.AT_MOST)
            super.onMeasure(widthMeasureSpec, heispec)
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}