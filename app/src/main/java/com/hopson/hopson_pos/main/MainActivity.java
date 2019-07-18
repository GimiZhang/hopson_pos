package com.hopson.hopson_pos.main;

import android.view.View;
import android.widget.Button;

import com.hopson.base_module.BaseApp;
import com.hopson.base_module.database.TestBean;
import com.hopson.base_moudle.database.hopson_pos.db.DaoSession;
import com.hopson.hopson_pos.R;
import com.hopson.hopson_pos.base.BaseActivity;
import com.hopson.network_module.http.BaseUrlConfig;
import com.xiaojinzi.component.impl.Router;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


public class MainActivity extends BaseActivity<MainContract.View, MainPresenter> implements MainContract.View {

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public View getInflateView() {
        return View.inflate(this, R.layout.activity_main, null);
    }

    @Override
    public void initViews() {
        Button btnJump = findViewById(R.id.btn_jump);

        if(queryAllList().size() !=0){
            TestBean testBean = (TestBean) queryAllList().get(0);
            BaseUrlConfig.BASE_URL = testBean.getBaseUrl();
        }

        btnJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.with(MainActivity.this)
                        .host("cash")
                        .path("cashActivity")
                        .navigate();
            }
        });
    }


    public List queryAllList(){
        DaoSession daoSession = ( (BaseApp)getApplication()).getDaoSession();
        QueryBuilder<TestBean> qb = daoSession.queryBuilder(TestBean.class);
        List<TestBean> list = qb.list(); // 查出所有的数据
        return list;
    }
}
