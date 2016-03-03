package com.softdesign.school.data.storage.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Team")
public class ORMTeam extends Model {

    @Column(name = "name")
    public String name;

    public ORMTeam() {
        super();
    }


    public List<ORMUser> items() {
        return getMany(ORMUser.class, "team");
    }

    public ORMTeam(String name) {
        super();
        this.name = name;
    }

    public static List<String> getAllNames() {
        List<ORMTeam> teams = getAll();
        List<String> names = new ArrayList<>(teams.size());
        for (ORMTeam t : teams) {
            names.add(t.name);
        }
        return names;
    }

    public static List<ORMTeam> getAll() {
        return new Select().from(ORMTeam.class).execute();
    }

    public static ORMTeam getByName(String name) {
        return new Select().from(ORMTeam.class).where("name = ?", name).executeSingle();
    }

    public String getName() {
        return name;
    }

}