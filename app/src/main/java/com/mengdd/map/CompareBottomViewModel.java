package com.mengdd.map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mengdd.arapp.R;
import com.mengdd.components.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CompareBottomViewModel extends ViewModel implements
        OnClickListener {
    private View mRootView;

    private List<Button> mButtons = null;

    public CompareBottomViewModel(Activity activity) {
        super(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRootView = View.inflate(mActivity, R.layout.bottom_menu_map_compare, null);

        Button btn1 = (Button) mRootView.findViewById(R.id.tab_google);
        Button btn2 = (Button) mRootView.findViewById(R.id.tab_baidu);
        Button btn3 = (Button) mRootView.findViewById(R.id.tab_autonavi);

        mButtons = new ArrayList<Button>();
        mButtons.add(btn1);
        mButtons.add(btn2);
        mButtons.add(btn3);

        for (Button btn : mButtons) {
            btn.setOnClickListener(this);
        }

    }

    public Button getButton(int index) {
        Button resultButton = mButtons.get(index);

        return resultButton;
    }

    private OnClickListener mOnClickListener = null;

    public void setOnClickListener(OnClickListener listener) {
        mOnClickListener = listener;
    }

    @Override
    public void onClick(View v) {

        for (Button btn : mButtons) {
            if (btn == v) {
                btn.setSelected(true);
            } else {
                btn.setSelected(false);
            }
        }
        if (null != mOnClickListener) {
            mOnClickListener.onClick(v);
        }

    }

    @Override
    public View getView() {
        return mRootView;
    }
}
