package com.my.spadea.net

import com.my.spadea.net.callback.BaseCallback
import com.my.spadea.net.json.JsonUtil
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.util.concurrent.TimeUnit


/**
 * Function : 网络请求工具类
 * Remarks  :
 * Created by Mr.C on 2018/5/8 0008.
 */
    object NetHelper {

        //请求时间
        val ConnectTimeOut  = 120L
        //读取时间
        val readTimeout  = 320L
        val CONTENT_TYPE = "application/x-www-form-urlencoded; charset=utf-8"
        val CONTENT_LENGTH = "Content-Length"

        var okHttpClient :OkHttpClient? = null

        fun init(){
           val builder = OkHttpClient().newBuilder()
            builder.cookieJar(Cookie())
            builder.connectTimeout(ConnectTimeOut, TimeUnit.SECONDS)
            builder.readTimeout(readTimeout,TimeUnit.SECONDS)
            okHttpClient = builder.build()
        }

        /**
         * POST请求方法
         */
        fun post( path:String, data :Any, callback:BaseCallback ){
                if (okHttpClient == null){
                    init()
                }
                val data_json = JsonUtil.convertObjectToJson(data)
                val data_length = data_json?.length
                var body  = RequestBody.create(MediaType.parse(CONTENT_TYPE),data_json)
                val request = Request.Builder().url(path).addHeader(CONTENT_LENGTH,data_length.toString()).post(body).build()
                val call = okHttpClient?.newCall(request)
                call?.enqueue(callback)
            }

    }