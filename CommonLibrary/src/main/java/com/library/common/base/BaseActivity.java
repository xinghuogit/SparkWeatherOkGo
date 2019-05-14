package com.library.common.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.githang.statusbar.StatusBarCompat;
import com.library.common.R;
import com.library.common.utils.ScreenUtils;
import com.library.common.utils.StatusBarUtil;
import com.library.common.utils.WindowSoftModeAdjustResizeExecutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

import static com.githang.statusbar.StatusBarTools.getStatusBarHeight;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewId());

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
//            textRight = (TextView) toolbar.findViewById(R.id.btn_right);
//            textCancel = (TextView) toolbar.findViewById(R.id.btn_left);
//            if (textCancel != null) {
//                textCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        onBackListener();
//                    }
//                });
//            }
//            if (textRight != null) {
//                setTextRight(textRight);
//            }
            setSupportActionBar(toolbar);
//            if (showBack) {
//                final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
//                upArrow.setColorFilter(getResources().getColor(R.color.color_white), PorterDuff.Mode.SRC_ATOP);
//                getSupportActionBar().setHomeAsUpIndicator(upArrow);
//                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            }
        }
        //初始化titlebar
        initStatusBar();
    }

    public abstract int setContentViewId();

    public abstract void initView();

    public abstract void initData();

    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
//        if (translucentStatusBar()) {
//            setStatusBarColor(getColor(), true);
//        }
//        StatusBarCompat.setStatusBarColor(this, R.color.transparent);
//        if (Build.VERSION.SDK_INT >= 19 && toolbar != null) {
//            StatusBarUtil.transparencyBar(this);
//            ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
//            layoutParams.height = layoutParams.height + getStatusBarHeight(this);
//            toolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
//            toolbar.setLayoutParams(layoutParams);
//        }
//        WindowSoftModeAdjustResizeExecutor.assistActivity(this);
        StatusBarUtil.setStatusBarColor(this, android.R.color.transparent);
        if (Build.VERSION.SDK_INT >= 19 && toolbar != null) {
            StatusBarUtil.transparencyBar(this);
            ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
            layoutParams.height = layoutParams.height + getStatusBarHeight(this);
            toolbar.setPadding(0, getStatusBarHeight(this), 0, 0);
            toolbar.setLayoutParams(layoutParams);
        }
        WindowSoftModeAdjustResizeExecutor.assistActivity(this);
    }

    /**
     * 子类可以重写改变状态栏、字体颜色
     */
    protected void setStatusBarColor(int color, boolean fontBackColor) {
        if (color != 0)
            StatusBarCompat.setStatusBarColor(this, color, fontBackColor);
    }


    protected int getColor() {
        return getResources().getColor(android.R.color.transparent);
//        return getResources().getColor(android.R.color.white);
    }

    /**
     * 子类可以重写决定是否使用透明状态栏
     */
    protected boolean translucentStatusBar() {
        return true;
    }

    /**
     * 设置状态栏文字色值为深色调
     *
     * @param useDart  是否使用深色调
     * @param activity
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setStatusTextColor(boolean useDart, Activity activity) {
        if (isFlyme()) {
            //魅族
            processFlyMe(useDart, activity);
        } else if (isMIUI()) {
            //小米
            setMIUIStatusTextColor(activity, useDart);
        } else if (Build.MANUFACTURER.equalsIgnoreCase("OPPO")) {
            //OPPO
            setOPPOStatusTextColor(useDart, activity);
        }
//        else {
//            //其他
//            setOtherStatusTextColor(activity, useDart);
//        }
    }

    /**
     * 判断手机是否是魅族
     *
     * @return
     */
    private static boolean isFlyme() {
        try {
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }

    /**
     * 改变魅族的状态栏字体为黑色，要求FlyMe4以上
     */
    private static void processFlyMe(boolean isLightStatusBar, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        try {
            Class<?> instance = Class.forName("android.view.WindowManager$LayoutParams");
            int value = instance.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON").getInt(lp);
            Field field = instance.getDeclaredField("meizuFlags");
            field.setAccessible(true);
            int origin = field.getInt(lp);
            if (isLightStatusBar) {
                field.set(lp, origin | value);
            } else {
                field.set(lp, (~value) & origin);
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    /**
     * 判断手机是否是小米
     *
     * @return
     */
    private static boolean isMIUI() {
        try {
            final Properties prop = new Properties();
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } catch (final IOException e) {
            return false;
        }
    }

    /**
     * 小米手机更改状态栏颜色
     *
     * @param activity
     * @param useDart
     */
    private void setMIUIStatusTextColor(Activity activity, boolean useDart) {
        //6.0后小米状态栏用的原生的
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (useDart) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
            } else {
                activity.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
            activity.getWindow().getDecorView().findViewById(android.R.id.content).setPadding(0, 0, 0, ScreenUtils.getNavigationBarHeight(this));
        } else {
            processMIUI(useDart, activity);
        }
    }

    /**
     * 改变小米的状态栏字体颜色为黑色, 要求MIUI6以上  lightStatusBar为真时表示黑色字体
     */
    private static void processMIUI(boolean lightStatusBar, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), lightStatusBar ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    /**
     * 设置OPPO手机状态栏字体为黑色(colorOS3.0,6.0以下部分手机)
     *
     * @param lightStatusBar
     * @param activity
     */
    private static final int SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT = 0x00000010;

    private static void setOPPOStatusTextColor(boolean lightStatusBar, Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int vis = window.getDecorView().getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lightStatusBar) {
                vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (lightStatusBar) {
                vis |= SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            } else {
                vis &= ~SYSTEM_UI_FLAG_OP_STATUS_BAR_TINT;
            }
        }
        window.getDecorView().setSystemUiVisibility(vis);
    }

    /**
     * 其他手机更改状态栏字体颜色
     */
    private void setOtherStatusTextColor(Activity activity, boolean useDart) {
        if (useDart) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                activity.getWindow().getDecorView().setSystemUiVisibility
                        (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        activity.getWindow().getDecorView().findViewById(android.R.id.content).setPadding
                (0, 0, 0, ScreenUtils.getNavigationBarHeight(this));
    }
}
