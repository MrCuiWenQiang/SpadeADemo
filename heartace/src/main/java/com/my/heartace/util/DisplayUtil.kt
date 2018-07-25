package com.my.heartace.util

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/7/23 0023.
 */
object DisplayUtil {

        /**
         * 屏幕密度,系统源码注释不推荐使用
         */
        val DENSITY = Resources.getSystem()
                .displayMetrics.density

        /**
         * 把以 dp 为单位的值，转化为以 px 为单位的值
         *
         * @param dpValue 以 dp 为单位的值
         * @return px value
         */
        fun dpToPx(dpValue: Int): Int {
            return (dpValue * DENSITY + 0.5f).toInt()
        }

        /**
         * 把以 px 为单位的值，转化为以 dp 为单位的值
         *
         * @param pxValue 以 px 为单位的值
         * @return dp值
         */
        fun pxToDp(pxValue: Float): Int {
            return (pxValue / DENSITY + 0.5f).toInt()
        }

        /**
         * 获取 DisplayMetrics
         *
         * @return
         */
        fun getDisplayMetrics(context: Context): DisplayMetrics {
            val displayMetrics = DisplayMetrics()
            (context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager)
                    .defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics
        }
}