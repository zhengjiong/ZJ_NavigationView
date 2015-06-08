package com.zj.example.zj_navigationview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * create by zhengjiong
 * Date: 2015-06-07
 * Time: 11:21
 */
public class BaseFragment extends Fragment{
    public static final String TITLE_KEY = "title";
    private TextView mTxtTitle;

    public static BaseFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString(TITLE_KEY, title);

        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        mTxtTitle = (TextView) view.findViewById(R.id.txt);

        String title = getArguments().getString(TITLE_KEY, "");
        mTxtTitle.setText(title);

        return view;
    }
}
