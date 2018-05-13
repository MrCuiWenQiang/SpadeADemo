package com.my.spadea.util

import android.util.Log

/**
 * Function : 调试工具类  无须制定TAG ，自动输出类名  行号 使用前需将isDebug赋值为true
 * Remarks  :
 * Created by Mr.C on 2018/5/12 0012.
 */
object LogUtil {
    
    var isDebug = false

    fun v(msg:String){
        if (isDebug){
            v(getTAG(),msg)
        }
    }

    fun d(msg:String){
        if (isDebug){
            d(getTAG(),msg)
        }
    }

    fun i(msg:String){
        if (isDebug){
            i(getTAG(),msg)
        }
    }

    fun w(msg:String){
        if (isDebug){
            w(getTAG(),msg)
        }
    }

    fun e(msg:String){
        if (isDebug){
           e(getTAG(),msg)
        }
    }


    fun v(tag:String ,msg:String){
        if (isDebug){
            Log.v(tag,msg)
        }
    }

    fun d(tag:String ,msg:String){
        if (isDebug){
            Log.d(tag,msg)
        }
    }

    fun i(tag:String ,msg:String){
        if (isDebug){
            Log.i(tag,msg)
        }
    }

    fun w(tag:String ,msg:String){
        if (isDebug){
            Log.w(tag,msg)
        }
    }

    fun e(tag:String ,msg:String){
        if (isDebug){
            Log.e(tag,msg)
        }
    }

    private fun getTAG() : String{
        val stasks = Throwable().fillInStackTrace().stackTrace
        val sb = StringBuilder();
        if (stasks == null){
            sb.append("DEBUG MODEL")
        }
        for (i in 2..stasks.size){
            var stask = stasks.get(i)
            var clazz = stask.javaClass
            if (!clazz.equals(this)){
                sb.append("${stask.className} -> ${stask.methodName}")
                sb.append("(${stask.lineNumber})")
                break
            }
        }
        if (sb.isEmpty()){
            sb.append("DEBUG MODEL")
        }
        return sb.toString()
    }
}