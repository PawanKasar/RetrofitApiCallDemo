package com.demo.retrofitapicallingdemo.Session;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.demo.retrofitapicallingdemo.Activities.LoginActivity;

import java.util.HashMap;

/**
 * Created by Pawan on 21-09-2018.
 */

public class SessionManager {

    //Shared Preferences
    SharedPreferences prefs;

    //Editor for Shared Preference
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "Pref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // Token which is getting from API Response (make variable public to access from outside)
    public static final String KEY_TOKEN = "token";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        prefs = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = prefs.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSession(String token){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing email in pref
        editor.putString(KEY_TOKEN, token);

        // commit changes
        editor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }
    }


    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        // user email id
        user.put(KEY_TOKEN, prefs.getString(KEY_TOKEN, null));

        // return user
        return user;
    }


    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login_Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }


    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return prefs.getBoolean(IS_LOGIN, false);
    }

}
