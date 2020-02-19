package com.newland.pospp.titlelayout.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

import com.newland.pospp.titlelayout.R;

import utils.CustomFuncManager;
import utils.StatusBarUtils;

/**
 * @description : 沉浸式标题栏
 * @author : huangjingf
 * @creatTime : 2020/2/18 17:07
 * @organization :NewLand
 */
public class TitleBar extends FrameLayout {

    private Context mContext;

    private ImageView imBarBack = null;
    private TextView tvBarTitle = null;
    private LinearLayout llBarOptions = null;

    private boolean fitStatus;
    private String title;
    private boolean backShow;
    private boolean backFinish;

    public TitleBar(Context context) {
        super(context);
        mContext = context;
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.widget_title, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        title = typedArray.getString(R.styleable.TitleBar_title);
        backShow = typedArray.getBoolean(R.styleable.TitleBar_backShow, false);
        backFinish = typedArray.getBoolean(R.styleable.TitleBar_backFinish, false);
        fitStatus = typedArray.getBoolean(R.styleable.TitleBar_fitStatus, true);

        imBarBack = findViewById(R.id.ivBarBack);
        tvBarTitle = findViewById(R.id.tvBarTitle);
        llBarOptions = findViewById(R.id.llBarOptions);

        initStatusBar();

        initBackShow();
        initBackFinish();
        setTitle(title);
        typedArray.recycle();
    }

    private void initStatusBar() {
        if (fitStatus) {
            StatusBarUtils.statusBarTranslucent((Activity) mContext);
        }
    }

    private void initBackShow() {
        if (backShow) {
            imBarBack.setVisibility(View.VISIBLE);
        } else {
            imBarBack.setVisibility(View.GONE);
        }
    }

    private void initBackFinish() {
        if (backFinish) {
            imBarBack.setVisibility(View.VISIBLE);
            imBarBack.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomFuncManager.getInstance().sendKeyEvent(4);
                }
            });
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!StatusBarUtils.isStatusBarExist((Activity) mContext) || fitStatus) {
            setPadding(0, getStatusBarHeight(), 0, 0);
        }
    }

    /**
     * 设置菜单点击事件
     */
    public void setOptionsVisible(int visible) {
        llBarOptions.setVisibility(visible);
    }

    public void setOptionsClickListener(String text, View.OnClickListener listener) {
        TextView textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(ContextCompat.getColor(mContext, R.color.text_main));
        textView.setText(text);
        setOptionsClickListener(textView, listener);
    }

    public void setOptionsClickListener(View view, OnClickListener listener) {
        FrameLayout.LayoutParams frameLayout = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(frameLayout);
        llBarOptions.setOnClickListener(listener);
        llBarOptions.addView(view);
        showView(llBarOptions);
    }

    /**
     * 设置返回点击事件
     */
    public void setBackClickListener(View.OnClickListener listener) {
        imBarBack.setOnClickListener(listener);
        showView(imBarBack);
    }

    /**
     * 设置标题
     */
    public void setTitle(CharSequence title) {
        tvBarTitle.setText(title);
    }

    public void setTitle(@StringRes int resid) {
        tvBarTitle.setText(resid);
    }

    public String getTitle() {
        return tvBarTitle.getText().toString().trim();
    }


    /**
     * 隐藏返回按钮
     */
    public void hideBackBtn() {
        imBarBack.setVisibility(View.GONE);
    }

    /**
     * 显示某个控件
     */
    private void showView(View v) {
        if (v.getVisibility() != View.VISIBLE) {
            v.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获得状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        //方法1
        Class<?> c = null;
        Object obj = null;
        java.lang.reflect.Field field = null;
        int x = 0;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);

            //方法2
            if (statusBarHeight == 0) {
                int result = 0;
                int resourceId = this.getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    result = this.getResources().getDimensionPixelSize(resourceId);
                }
                if (result > 0) {
                    statusBarHeight = result;
                }
            }
            //方法3
            if (statusBarHeight == 0) {
                statusBarHeight = (int) Math.ceil(20 * this.getResources().getDisplayMetrics().density);
            }
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }
}
