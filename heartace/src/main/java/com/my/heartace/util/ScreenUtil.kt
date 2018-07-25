package com.my.heartace.util

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.WindowManager

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/7/21 0021.
 */
object ScreenUtil {
        /**
         * 获取屏幕高度（去除标题栏高度）
         */
        fun getWindowHeight(context: Context): Int {
            var statusBarHeight = 0
            val ac = context as Activity
            val vm = ac.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val size = Point()
            vm.defaultDisplay.getSize(size)
            val height = size.y
            val resId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resId > 0) {
                statusBarHeight = getPixelSize(context, resId)
            }
            return height - statusBarHeight
        }

        /***
         * 获取屏幕中的大小
         *
         * @param context 关联
         * @param sizeId  大小的id
         * @return 大小
         */
        fun getPixelSize(context: Context, sizeId: Int): Int {
            var size = 0
            if (sizeId != -1) {
                size = context.resources.getDimensionPixelSize(sizeId)
            }
            return size
        }
}