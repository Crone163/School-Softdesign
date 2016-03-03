package com.softdesign.school.ui.activities;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.ORMTeam;
import com.softdesign.school.data.storage.models.ORMUser;
import com.softdesign.school.ui.adaptres.ContactsORMAdapter;
import com.softdesign.school.ui.fragments.ContactsORMFragment;

import java.util.ArrayList;
import java.util.List;



public class ORMActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<List<ORMUser>> {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orm);

        mContext = this;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.orm_coordinator_layout, new ContactsORMFragment()).commit();
        }


    }

    @Override
    public void onClick(View v) {
        LayoutInflater inflater = getLayoutInflater();
        switch (v.getId()) {
            case R.id.button_add_team:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final EditText teamName = new EditText(this);
                builder
                        .setMessage("Название команды")
                        .setTitle("Добавить команду?")
                        .setView(teamName).setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        new ORMTeam(teamName.getText().toString()).save();
                        dialog.cancel();
                    }
                }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.button_add_user:

                View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ORMTeam.getAllNames());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                Spinner spinner = (Spinner) dialogView.findViewById(R.id.teamSpinner);
                spinner.setAdapter(adapter);
                spinner.setPrompt("Новый пользователь");

                builder = new AlertDialog.Builder(this);
                builder.setTitle("Добавить пользователя?")
                        .setView(dialogView)
                        .setPositiveButton("Готово", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                EditText firstName = (EditText) ((AlertDialog) dialog).findViewById(R.id.contact_add_first_name);
                                EditText lastName = (EditText) ((AlertDialog) dialog).findViewById(R.id.contact_add_last_name);
                                Spinner spinner = (Spinner) ((AlertDialog) dialog).findViewById(R.id.teamSpinner);
                                ORMTeam team = ORMTeam.getByName(spinner.getSelectedItem().toString());
                                new ORMUser(firstName.getText().toString(), lastName.getText().toString(), team).save();

                                getLoaderManager().initLoader(0, null, ORMActivity.this).forceLoad();

                                dialog.cancel();
                            }
                        }).setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                alert = builder.create();
                alert.show();

                break;
        }
    }

    @Override
    public Loader<List<ORMUser>> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<List<ORMUser>>(mContext) {
                     @Override
                      public List<ORMUser> loadInBackground() {
                            return ORMUser.getAll();
                         }
                 };
    }

    @Override
    public void onLoadFinished(Loader<List<ORMUser>> loader, List<ORMUser> data) {
        ListView listView = (ListView) findViewById(R.id.listView);
        ContactsORMAdapter listViewAdapter = (ContactsORMAdapter) listView.getAdapter();
        listViewAdapter.resetData(ORMUser.getAll());
    }

    @Override
    public void onLoaderReset(Loader<List<ORMUser>> loader) {
        ListView listView = (ListView) findViewById(R.id.listView);
        ContactsORMAdapter listViewAdapter = (ContactsORMAdapter) listView.getAdapter();
        listViewAdapter.resetData(new ArrayList<ORMUser>());
    }
}