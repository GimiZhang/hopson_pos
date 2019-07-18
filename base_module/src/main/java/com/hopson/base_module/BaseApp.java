package com.hopson.base_module;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.hopson.base_module.database.TestBean;
import com.hopson.base_moudle.database.hopson_pos.db.DaoMaster;
import com.hopson.base_moudle.database.hopson_pos.db.DaoSession;


public class BaseApp extends Application {

    public static BaseApp baseAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        baseAppContext = this;
        //初始化数据库
        initGreenDao();
        insertData();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "hopson_pos.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void insertData(){
        TestBean testBean = new TestBean();
        testBean.setBaseUrl("https://tapi.hopsontong.com:11013/");
        testBean.setRequestUrl("business/getMarkets/116.467784/39.891225");
        daoSession.insert(testBean);
    }

}
