package com.softdesign.school.ui.adaptres;

/**
 * Created by CRN_soft on 19.01.2016.
 */


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.school.R;

import butterknife.Bind;

public class UserViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.user_name) TextView fullName;
    @Bind(R.id.user_avatar) ImageView avatar;

    public UserViewHolder(View itemView) {
        super(itemView);
        fullName = (TextView) itemView.findViewById(R.id.user_name);
        avatar = (ImageView) itemView.findViewById(R.id.user_avatar);
    }
}