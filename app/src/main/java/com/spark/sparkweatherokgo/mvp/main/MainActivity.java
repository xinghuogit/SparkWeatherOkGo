package com.spark.sparkweatherokgo.mvp.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.library.common.mvp.base.view.MvpBaseActivity;
import com.spark.sparkweatherokgo.BuildConfig;
import com.spark.sparkweatherokgo.R;
import com.spark.sparkweatherokgo.mvp.home.HomeFragment;
import com.spark.sparkweatherokgo.mvp.my.MyFrgamnet;
import com.spark.sparkweatherokgo.mvp.test.TestActivity;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends MvpBaseActivity<MainPresenter> implements MainView, View.OnClickListener {
    private static final String TAG = "MainActivity";


    private ViewPager vp;

    private LinearLayout llHome;
    private TextView tvHome;
    private LinearLayout llType;
    private TextView tvType;
    private LinearLayout llMy;
    private TextView tvMy;
    private TextView tvMy1;

    private View currentView;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        llHome = (LinearLayout) findViewById(R.id.llHome);
        tvHome = (TextView) findViewById(R.id.tvHome);
        llType = (LinearLayout) findViewById(R.id.llType);
        tvType = (TextView) findViewById(R.id.tvType);
        llMy = (LinearLayout) findViewById(R.id.llMy);
        tvMy = (TextView) findViewById(R.id.tvMy);
        setCurrentView(tvHome);

        findViewById(R.id.bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File file = new File("/storage/emulated/0/AACqtss/Cache/AACqtss2.1.6.apk");
//                LogUtils.i(TAG, "onClick: " + FileUtils.createOrExistsDir("/storage/emulated/0/AACqtss/Cache/AACqtss2.1.6.apk"));
//                installAPk(file);
//                Intent intent = new Intent(MainActivity.this, Main1A.class);
//                startActivity(intent);
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 1、首先我们对Android N及以上做判断；
     * 2、然后添加flags，表明我们要被授予什么样的临时权限
     * 3、以前我们直接 Uri.fromFile(apkFile)构建出一个Uri,现在我们使用FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", apkFile);
     * 4、BuildConfig.APPLICATION_ID直接是应用的包名
     */
    private void installAPk(File apkFile) {
//        EventBus.getDefault().post(new AppUpdateEvent(upgrade, mHandler));
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }

    @Override
    public void initData() {
        tvHome.setOnClickListener(this);
        tvType.setOnClickListener(this);
        tvMy.setOnClickListener(this);

        mFragments.add(new MyFrgamnet());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());


        vp.setAdapter(new FragPagerAdapter(getSupportFragmentManager(), mFragments));
        vp.setCurrentItem(0, false);
        vp.setOffscreenPageLimit(3);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        setCurrentView(tvHome);
                        break;
                    case 1:
                        setCurrentView(tvType);
                        break;
                    case 2:
                        setCurrentView(tvMy);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvHome:
                vp.setCurrentItem(0);
                setCurrentView(v);
                break;
            case R.id.tvType:
                vp.setCurrentItem(1);
                setCurrentView(v);
                break;
            case R.id.tvMy:
                vp.setCurrentItem(2);
                setCurrentView(v);
                break;
        }
    }

    public void setCurrentView(View currentView) {
        if (this.currentView != null && currentView.equals(this.currentView)) return;
        this.currentView = currentView;
        removeSelected();
        currentView.setSelected(true);
        switch (currentView.getId()) {
            case R.id.tvHome:
                break;
            case R.id.tvType:
                break;
            case R.id.tvMy:
                break;
        }
    }

    private void removeSelected() {
        tvHome.setSelected(false);
        tvType.setSelected(false);
        tvMy.setSelected(false);
    }
}
