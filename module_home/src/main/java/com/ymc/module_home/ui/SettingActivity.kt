package com.ymc.module_home.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ymc.common_base.arouter.ARouterConstant.SETTING_ACTIVITY
import com.ymc.module_home.R

@Route(path = SETTING_ACTIVITY)
class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_setting)
    }
}