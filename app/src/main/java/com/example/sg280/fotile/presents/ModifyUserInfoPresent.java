package com.example.sg280.fotile.presents;

import android.app.Activity;

/**
 * Created by Tian on 2016/8/4.
 */
public class ModifyUserInfoPresent implements ModifyUserInfoContacts.Present{

    private ModifyUserInfoContacts.View myView;
    private Activity myActivity;


    public ModifyUserInfoPresent(ModifyUserInfoContacts.View myView, Activity myActivity) {
        this.myView = myView;
        this.myActivity = myActivity;
    }

    @Override
    public void ondestory() {


    }

    @Override
    public void overModify() {

    }
}
