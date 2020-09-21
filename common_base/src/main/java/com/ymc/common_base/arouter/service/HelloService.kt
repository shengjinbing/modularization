package com.ymc.common_base.arouter.service

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by lixiang on 2020/9/21
 * Describe:
 */
open interface HelloService : IProvider {
    fun say(name:String):String
}