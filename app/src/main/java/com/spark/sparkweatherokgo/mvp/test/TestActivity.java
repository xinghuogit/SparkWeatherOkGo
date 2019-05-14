package com.spark.sparkweatherokgo.mvp.test;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.library.common.mvp.base.view.MvpBaseActivity;
import com.spark.sparkweatherokgo.R;

/**
 * 日期：2019/5/14 17:49
 * 作者：李加蒙
 * 描述：测试
 **/
public class TestActivity extends MvpBaseActivity<TestPresenter> implements TestView, View.OnClickListener {
    private TextView tv;
    private Button bottom1;
    private Button bottom2;
    private Button bottom3;


    @Override
    public TestPresenter createPresenter() {
        return new TestPresenter();
    }

    @Override
    public int setContentViewId() {
        return R.layout.test_activity;
    }

    @Override
    public void initView() {
        tv = findViewById(R.id.tv);
        bottom1 = findViewById(R.id.bottom1);
        bottom2 = findViewById(R.id.bottom2);
        bottom3 = findViewById(R.id.bottom3);
    }

    @Override
    public void initData() {
        bottom1.setOnClickListener(this);
        bottom2.setOnClickListener(this);
        bottom3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom1: //json字符串
                if (mPresenter != null) {
                    mPresenter.jsonString();
                }
                break;
            case R.id.bottom2://json对象
                if (mPresenter != null) {
                    mPresenter.jsonObject();
                }
                break;
            case R.id.bottom3://array数组
                if (mPresenter != null) {
                    mPresenter.jsonListObject();
                }
                break;
        }
    }

    @Override
    public void setText(String s) {
        tv.setText(s);
    }
}
