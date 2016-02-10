package com.softdesign.school.ui.adaptres;

/**
 * Created by CRN_soft on 19.01.2016.
 */


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.softdesign.school.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    protected TextView fullName;
    protected ImageView avatar;

    public UserViewHolder(View itemView) {
        super(itemView);
        fullName = (TextView) itemView.findViewById(R.id.user_name);
        avatar = (ImageView) itemView.findViewById(R.id.user_avatar);
    }
}