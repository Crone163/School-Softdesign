package com.softdesign.school.data.storage.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "User")
public class ORMUser extends Model {

    @Column(name = "firstName")
    private String mFirstName;

    @Column(name = "lastName")
    private String mLastName;

    @Column(name = "team")
    private ORMTeam mTeam;

    public ORMUser(){
        super();
    }


    public ORMUser(String lastName, String firstName, ORMTeam team) {
        super();
        this.mFirstName = firstName;
        this.mLastName = lastName;
        this.mTeam = team;
    }



    public static List<ORMUser> getAll() {
        return new Select().from(ORMUser.class).execute();
    }


    public ORMTeam getTeam() {
        return mTeam;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getFirstName() {
        return mFirstName;
    }
}