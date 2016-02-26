package com.softdesign.school.ui.adaptres;

/**
 * Created by CRN_soft on 09.02.2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;

import java.util.ArrayList;


public class RecycleUsersAdapter extends RecyclerView.Adapter<UserViewHolder> {

    ArrayList<User> users;

    public RecycleUsersAdapter(ArrayList<User> users){
        this.users = users;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_card, parent, false);
        return new UserViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        User user = users.get(position);
        holder.fullName.setText(user.getmFirstName() + " " + user.getmLastName());
        holder.avatar.setImageDrawable(user.getmImage());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}