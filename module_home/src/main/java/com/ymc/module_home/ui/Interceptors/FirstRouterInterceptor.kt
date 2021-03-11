package com.ymc.module_home.ui.Interceptors

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

/**
 *值越小，优先级越高
 * Created by lixiang on 2020/12/21
 * Describe:
 */
@Interceptor(priority = 1)
class FirstRouterInterceptor : IInterceptor {
    override fun init(context: Context?) {
        Log.d("BBBBB","First初始化")
    }
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        Log.d("BBBBB","Firstprocess前")
        callback?.onContinue(postcard)
        Log.d("BBBBB","Firstprocess后")
    }
}

@Interceptor(priority = 2)
class SecondRouterInterceptor : IInterceptor {
    override fun init(context: Context?) {
        Log.d("BBBBB"," Second初始化")
    }
    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        Log.d("BBBBB"," Secondprocess前")
        callback?.onContinue(postcard)
        Log.d("BBBBB"," Secondprocess后")
    }
}