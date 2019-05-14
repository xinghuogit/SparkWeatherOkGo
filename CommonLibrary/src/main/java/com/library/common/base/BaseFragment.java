package com.library.common.base;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.library.common.R;
import com.library.common.utils.StatusBarUtil;

import static com.githang.statusbar.StatusBarTools.getStatusBarHeight;

/**
 * 日期：2019/3/6
 * 创建：李加蒙
 * 描述：Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    private Toolbar toolbar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public abstract void initView(View view);

    public abstract void initData();

    protected void initViews(View view, Bundle savedInstanceState) {
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
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
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
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

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= 19 && toolbar != null) {
            StatusBarUtil.transparencyBar(getActivity());
            ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
            layoutParams.height = layoutParams.height + getStatusBarHeight(getActivity());
            toolbar.setPadding(0, getStatusBarHeight(getActivity()), 0, 0);
            toolbar.setLayoutParams(layoutParams);
        }
    }

    public <T extends View> T findView(View view, int id) {
        return (T) view.findViewById(id);
    }
}
