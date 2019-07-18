package com.hopson.hopson_pos;

import com.hopson.base_module.BaseApp;
import com.xiaojinzi.component.Component;
import com.xiaojinzi.component.impl.application.ModuleManager;

public class MainApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        Component.init(this, BuildConfig.DEBUG);
        // 注册其他业务模块,注册的字符串是上面各个业务模块配置在 build.gradle 中的 HOST
        ModuleManager.getInstance().registerArr("base", "app", "network", "cash",
                "change_flight", "flight", "refund", "trans_detail", "trans_sum");
        if (BuildConfig.DEBUG) {
            // 框架还带有检查重复的路由和重复的拦截器等功能,在 `debug` 的时候开启它
            ModuleManager.getInstance().check();
        }


    }

}
