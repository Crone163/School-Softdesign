package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.ORMUser;
import com.softdesign.school.ui.activities.ORMActivity;
import com.softdesign.school.ui.adaptres.ContactsORMAdapter;
import com.softdesign.school.ui.adaptres.UserViewHolder;

import java.util.List;

public class ContactsORMFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View convertView = inflater.inflate(R.layout.fragment_contacts_orm, null, false);

        ORMActivity activity = (ORMActivity) getActivity();
        ListView listView = (ListView) convertView.findViewById(R.id.listView);

        Button addTeam = (Button) convertView.findViewById(R.id.button_add_team);
        Button addUser = (Button) convertView.findViewById(R.id.button_add_user);

        addTeam.setOnClickListener(activity);
        addUser.setOnClickListener(activity);

        List<ORMUser> users = ORMUser.getAll();
        ContactsORMAdapter adapter = new ContactsORMAdapter(activity, users);
        listView.setAdapter(adapter);





        return  convertView ;
    }
}