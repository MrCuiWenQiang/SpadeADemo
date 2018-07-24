package com.my.heartace.util

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.TypedValue

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/7/23 0023.
 */
class ResUtil {
    companion object {

        fun getAttrDimen(context: Context, attrRes: Int): Int {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(attrRes, typedValue, true)
            return TypedValue.complexToDimensionPixelSize(typedValue.data, DisplayUtil.getDisplayMetrics(context))
        }

        fun getAttrColor(context: Context, attRes: Int): Int {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(attRes, typedValue, true)
            return typedValue.data
        }

        fun getAttrColorStateList(context: Context, attRes: Int): ColorStateList {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(attRes, typedValue, true)
            return ContextCompat.getColorStateList(context, typedValue.resourceId)!!
        }

        fun getAttrDrawable(context: Context, attRes: Int): Drawable? {
            val attrs = intArrayOf(attRes)
            val typedValue = context.obtainStyledAttributes(attrs)
            val drawable = typedValue.getDrawable(0)
            return drawable
        }
    }
}