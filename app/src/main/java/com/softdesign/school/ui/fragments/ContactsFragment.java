package com.softdesign.school.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.activities.MainActivity;
import com.softdesign.school.ui.adaptres.RecycleUsersAdapter;
import com.softdesign.school.utils.BlockToolbar;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CRN_soft on 01.02.2016.
 */
public class ContactsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<User> mUsers = new ArrayList<>();

    @Bind(R.id.fab) FloatingActionButton mFab;
    @Bind(R.id.appbar_layout)AppBarLayout appBar;

    /** Записываем статические данные в массивы, перед созданием вьюх */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        generateData();
        mAdapter = new RecycleUsersAdapter(mUsers);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_contacts, container, false);
        getActivity().setTitle(getResources().getString(R.string.drawer_contacts));
        ButterKnife.bind(this,getActivity());

        MainActivity activity = ((MainActivity) getActivity());
        activity.lockAppBar(true);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycleView);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        // запрещаем скролл колапстулбара для ресайкл вью
        mRecyclerView.setNestedScrollingEnabled(false);

        return mView;
    }

    /** Инициализирауем кнопку и задаем параметры позиции снизу справа и меняем иконку  */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Contacts Fab was pressed", Toast.LENGTH_SHORT).show();
            }
        });

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mFab.getLayoutParams();
        params.setAnchorId(R.id.coordinator_layout);

        params.anchorGravity = Gravity.BOTTOM | Gravity.END;
        mFab.setLayoutParams(params);
        mFab.setImageResource(R.drawable.ic_mail_24dp);

        // блокируем коллапстулбар
        appBar.setExpanded(false, false);
        BlockToolbar.setDrag(false, appBar);
    }



    private void generateData() {
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Евгений", "Осипов"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Дмитрий", "Титов"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Василий", "Пупкин"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Андрей", "Нощенко"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_account_circle_24dp), "Жанна", "Беляева"));
    }
}
