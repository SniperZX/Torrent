package com.rock.software.torrent.MyView;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rock.software.torrent.R;


public class TabIndicatorView extends RelativeLayout {
    private ImageView ivTabIcon;
    private TextView tvTabHint;

    private int normalIconId;
    private int focusIconId;

    public TabIndicatorView(Context context) {
        this(context, null);
    }

    public TabIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 将布局文件和 代码进行绑定
        View.inflate(context, R.layout.tab_indicator, this);

        ivTabIcon = (ImageView) findViewById(R.id.tab_indicator_icon);
        tvTabHint = (TextView) findViewById(R.id.tab_indicator_hint);

    }

    // 设置tab的title
    public void setTabTitle(String title) {
        tvTabHint.setText(title);
    }

    public void setTabTitle(int titleId) {
        tvTabHint.setText(titleId);
    }

    // 初始化图标
    public void setTabIcon(int normalIconId, int focusIconId) {
        this.normalIconId = normalIconId;
        this.focusIconId = focusIconId;

        ivTabIcon.setImageResource(normalIconId);
        tvTabHint.setTextColor(getResources().getColor(android.R.color.black));
    }


    // 设置选中
    public void setTabSelected(boolean selected) {
        if (selected) {
            ivTabIcon.setImageResource(focusIconId);
            tvTabHint.setTextColor(getResources().getColor(R.color.colorAccent));
        } else {
            ivTabIcon.setImageResource(normalIconId);
            tvTabHint.setTextColor(getResources().getColor(android.R.color.black));
        }
    }
}
