package com.my.spadea.net.callback

import com.my.spadea.net.json.JsonUtil
import com.my.spadea.util.LogUtil
import okhttp3.Call
import okhttp3.Response
import java.io.IOException

/**
 * Function :普通网络请求使用的回调
 * Remarks  :
 * Created by Mr.C on 2018/5/11 0011.
 */
abstract  class  HttpConnectCallBack<T : Any>(t:Class<T>): BaseCallBack(){

    val t = t

    override fun onFailure(call: Call?, e: IOException?) {
        mainThread.post(Runnable {
            onFail(400, e.toString())
        })
    }

    override fun onResponse(call: Call?, response: Response?) {
        if(response != null){
            var code = response.code()
            if (code == 200){
                val dataBase  =JsonUtil.convertJsonToObject(response.body().string().toString(),t)
                mainThread.post(Runnable {
                    onSuccess(code,dataBase)
                })
            }else{
                mainThread.post(Runnable {
                    onFail(code,"connection fail!")
                })
            }
        }else{
            mainThread.post(Runnable {
                onFail(-1,"response is null!")
            })
        }
    }

    abstract fun onSuccess(code : Int,data : T?)
}