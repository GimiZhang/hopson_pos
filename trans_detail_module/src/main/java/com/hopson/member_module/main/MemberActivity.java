package com.hopson.member_module.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hopson.member_module.R;
import com.xiaojinzi.component.anno.RouterAnno;

@RouterAnno(host="member",path="memberActivity")
public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
    }
}
