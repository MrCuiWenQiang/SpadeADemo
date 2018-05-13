package com.my.spadea.net

import com.my.spadea.net.callback.BaseCallBack
import com.my.spadea.net.callback.HttpConnectCallBack
import com.my.spadea.net.json.JsonUtil
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.net.URLEncoder
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
        fun post(url:String, data :Any?, callBack: BaseCallBack){
            if (okHttpClient == null){
                init()
            }
            val builder =  Request.Builder().url(url)
            if (data!=null){
                val data_json = JsonUtil.convertObjectToJson(data)
                val data_length = data_json?.length
                builder.addHeader(CONTENT_LENGTH,data_length.toString())
                val body = RequestBody.create(MediaType.parse(CONTENT_TYPE),data_json)
                builder.post(body)
            }
            val request =builder.build()
            val call = okHttpClient?.newCall(request)
            call?.enqueue(callBack)
        }

        /**
         * get请求方法
         */
        fun <T : Any> get(url:String, params:Map<String,String>?, callBack: HttpConnectCallBack<T>){
                if (okHttpClient == null){
                    init()
                }
                val urlPath = splitJoint(url,params)
                val request = Request.Builder().url(urlPath).build()
                val call = okHttpClient?.newCall(request)
                call?.equals(callBack)
            }


        /**
         * 文件下载
         */
        fun download(){

            }

        private fun splitJoint(url:String,params:Map<String,String>?) : String{
            if (params == null || params.isEmpty()){
                return url
            }

            var sb = StringBuilder()
            sb.append("$url?")
            val entrySet =params.entries
            for (item in entrySet){
                sb.append(item.key)
                sb.append("=")
                try {
                    sb.append(URLEncoder.encode(item.value,"UTF-8"))
                }catch (e : Exception){

                }
                sb.append("&")
            }
            sb.deleteCharAt(sb.length-1)
            return sb.toString()
        }

}