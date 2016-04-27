package com.example.data.entity;

import io.realm.RealmObject;

/**
 * Created by a630703 on 26/04/2016.
 */
public class RealmString extends RealmObject {

    private String string;

    public RealmString (){

    }

    public RealmString(String string){
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
