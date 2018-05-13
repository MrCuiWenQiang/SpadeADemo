package com.my.spadea.net.callback

import android.os.Handler
import android.os.Looper
import okhttp3.Callback

/**
 * Function : OkHttp3 的callback基类
 * Remarks  :
 * Created by Mr.C on 2018/5/8 0008.
 */
    abstract class BaseCallBack : Callback{
        companion object {
            var mainThread : Handler = Handler(Looper.getMainLooper())
        }

    /**
     * 通用失败回调方法
     * code ：网络请求错误码
     * reason：失败原因
     */
    abstract fun onFail(code:Int , reason:String)

    }