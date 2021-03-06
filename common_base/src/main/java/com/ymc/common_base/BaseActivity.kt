package com.ymc.common_base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.ymc.common.interfaces.IView

/**
 * Author : ymc
 * Date   : 2020/8/31  16:09
 * Class  : BaseActivity
 */
abstract class BaseActivity : AppCompatActivity(), IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView(savedInstanceState)
        initViewData()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initViewData()


    /**
     * 禁止交互
     */
    protected fun disableInteraction() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

    /**
     * 允许交互
     */
    protected fun enableInteraction() {
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}