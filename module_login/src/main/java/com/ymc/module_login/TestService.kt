package com.ymc.module_login

import android.content.Context
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.ymc.common_base.arouter.service.HelloService

/**
 * Created by lixiang on 2020/9/21
 * Describe:
 */
@Route(path = "/test/testservice")
open class TestService : HelloService{
    override fun say(name: String):String {
        return name
    }

    override fun init(context: Context?) {

    }

}