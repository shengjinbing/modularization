package com.ymc.common_base.arouter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.core.LogisticsCenter
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavCallback
import com.alibaba.android.arouter.launcher.ARouter
import java.io.Serializable

/**
 * 路由跳转工具类
 *
 * Author : ymc
 * Date   : 2020/9/10  18:50
 * Class  : ARouterUtils
 */

object ARouterUtils {

    /**
     * 获取fragment
     */
    @JvmStatic
    fun startFragment(path: String?) {
        ARouter.getInstance().build(path).navigation()
    }

    fun startActivity(path: String?) {
        ARouter.getInstance().build(path).navigation()
    }


    fun changeActivityAddAction(
        action: String,
        data: Uri,
        flags: Int? = null,
        key: String? = null,
        value: Any? = null
    ) {
        val router = HxRouter.addAction(action).addData(data)
        if (flags != null)
            router.addFlags(flags)
        if (key != null && value != null)
            router.put(key, value)

        router.start()
    }

    fun changeActivity(path: String, key: String, value: Any?) {
        ARouter.getInstance().build(path).run {
            if (value != null) {
                resolution(key, value, this)
            }
            navigation()
        }
    }

    fun changeActivity(
        ac: AppCompatActivity,
        path: String,
        key: String,
        value: Any? = null,
        callback: NavCallback? = null
    ) {
        ARouter.getInstance().build(path).run {
            if (value != null) {
                resolution(key, value, this)
            }
            if (callback != null) {
                navigation(ac, callback)
            } else {
                navigation(ac)
            }
        }
    }

    fun changeActivity(
        path: String,
        params: HashMap<String, Any?>? = null
    ) {
        ARouter.getInstance().build(path).run {
            params?.forEach {
                resolution(it.key, it.value, this)
            }
            navigation()
        }
    }

    fun changeActivity(ac: Activity, path: String, callback: NavCallback) {
        ARouter.getInstance().build(path).run {
            navigation(ac, callback)
        }
    }

    fun changeActivity(
        ac: Activity,
        path: String,
        params: HashMap<String, Any?>? = null,
        callback: NavCallback
    ) {
        ARouter.getInstance().build(path).run {
            params?.forEach {
                resolution(it.key, it.value, this)
            }
            navigation(ac, callback)
        }
    }

    fun changeActivityTransition(
        path: String,
        params: HashMap<String, Any?>? = null,
        ac: Activity,
        animIn: Int,
        animOut: Int
    ) {
        ARouter.getInstance().build(path).withTransition(animIn, animOut).run {
            params?.forEach {
                resolution(it.key, it.value, this)
            }
            navigation(ac)
        }
    }

    /**
     * requestCode需要大于0，否则不能回调onActivityResult
     */
    fun changeActivityForResult(ac: Activity, path: String, requestCode: Int) {
        require(requestCode > 0) { "requestCode value need more than zero" }
        ARouter.getInstance().build(path).run {
            navigation(ac, requestCode)
        }
    }

    fun changeActivityForResult(
        ac: Activity,
        path: String,
        key: String? = null,
        value: Any? = null,
        requestCode: Int? = null,
        callback: NavCallback? = null
    ) {
        ARouter.getInstance().build(path).run {
            if (key != null && value != null) {
                resolution(key, value, this)
            }
            if (requestCode != null) {
                navigation(ac, requestCode)
            } else {
                navigation(ac)
            }
        }
    }

    fun changeActivityForResult(
        ac: Activity,
        path: String,
        params: HashMap<String, Any?>,
        requestCode: Int? = null,
        callback: NavCallback? = null
    ) {
        ARouter.getInstance().build(path).run {
            params.forEach {
                resolution(it.key, it.value, this)
            }
            if (requestCode != null) {
                navigation(ac, requestCode, callback)
            } else {
                navigation(ac, callback)
            }
        }
    }

    fun changeActivityHashForResult(
        ac: Activity,
        fg: Fragment,
        path: String,
        params: HashMap<String, Any?>,
        requestCode: Int
    ) {
        ARouter.getInstance().build(path).apply {
            params.forEach {
                resolution(it.key, it.value, this)
            }
            LogisticsCenter.completion(this)
            val intent = Intent(ac, this.destination)
            intent.putExtras(this.extras)
            fg.startActivityForResult(intent, requestCode)
        }

    }


    /**
     * requestCode需要大于0，否则不能回调onActivityResult
     */
    fun Activity.changeActivityForResult(
        path: String,
        params: HashMap<String, Any?>,
        requestCode: Int
    ) {
        if (requestCode <= 0) {
            throw IllegalArgumentException("requestCode value need more than zero")
        }

        ARouter.getInstance().build(path).run {
            params.forEach {
                resolution(it.key, it.value, this)
            }
            navigation(this@changeActivityForResult, requestCode)
        }
    }

    /**
     * 带有category和flag的路由跳转
     */
    fun changeActivityAddConfigNew(context: Context, path: String, category: String, flags: Int) {
        ARouter.getInstance().build(path).run {
            LogisticsCenter.completion(this)
            val intent = Intent(context, destination).apply {
                addCategory(category)
                addFlags(flags)
            }
            context.startActivity(intent)
        }
    }

    fun resolution(key: String, value: Any?, postcard: Postcard) {
        postcard.run {
            when (value) {
                is String -> withString(key, value)
                is Int -> withInt(key, value)
                is Float -> withFloat(key, value)
                is Double -> withDouble(key, value)
                is Boolean -> withBoolean(key, value)
                is Long -> withLong(key, value)
                is Serializable -> withSerializable(key, value)
                is Parcelable -> withParcelable(key, value)
                //...如果还有其他类型，请自行补充
                else -> {
                }
            }
        }
    }


}