package com.bagicode.www.bagipukesmasvideo;

/**
 * Created by john doe on 9/15/2016.
 */

/**
 * Created by john doe on 9/14/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "Login";


        public SessionManager(Context context) {
            this._context = context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        }

    public void setPage(String page){
            editor.putString("page", page);

            // commit changes
            editor.commit();
        }

    public String getPage(){
            return pref.getString("page",null);
        }

    public void setDataPage(String datapage){
        editor.putString("datapage", datapage);

        // commit changes
        editor.commit();
    }

    public String getDataPage(){
        return pref.getString("datapage",null);
    }

}

