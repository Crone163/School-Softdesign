package com.softdesign.school.ui.adaptres;


import android.app.ActionBar;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.ORMUser;
import com.softdesign.school.data.storage.models.User;

import java.util.ArrayList;
import java.util.List;


public class ContactsORMAdapter extends BaseAdapter {

    private final Context mContext;
    private List<ORMUser> mUsers;

    public ContactsORMAdapter(Context context, List<ORMUser> users) {
        this.mContext = context;
        this.mUsers = users;
    }

    public void resetData(List<ORMUser> users) {
        this.mUsers = users;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return mUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ORMUser user = mUsers.get(position);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_user_card, null, false);


        TextView name = (TextView) view.findViewById(R.id.user_name);
        TextView command = (TextView) view.findViewById(R.id.user_team);
        name.setText(user.getFirstName() + " " + user.getLastName());
        command.setText(user.getTeam().getName());
        return view;
    }
}