package com.softdesign.school.ui.fragments;

import android.app.Activity;
import android.os.Bundle;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.utils.BlockToolbar;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by CRN_soft on 19.01.2016.
 */
public class ProfileFragment extends Fragment {

    @Bind(R.id.fab) FloatingActionButton mFab;
    @Bind(R.id.appbar_layout)AppBarLayout appBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this,getActivity());

        // подключаемся к MainActivity и задаем параметры
        MainActivity activity = ((MainActivity) getActivity());
        activity.lockAppBar(false);
        activity.setRatingText("44");
        activity.setCollapsTitle(getResources().getString(R.string.my_fio));


        return mView;
    }

    /** Привязываем кнопку к якорю appbar_layout  */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFab.getLayoutParams();
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Profile Fab was pressed", Toast.LENGTH_SHORT).show();
            }
        });

        params.setAnchorId(R.id.appbar_layout);
        params.anchorGravity = Gravity.BOTTOM | Gravity.END;
        mFab.setLayoutParams(params);
        mFab.setImageResource(R.drawable.ic_mode_edit_24dp);

        // разблокируем коллапстулбар
        appBar.setExpanded(true);
        BlockToolbar.setDrag(true, appBar);
    }
}
