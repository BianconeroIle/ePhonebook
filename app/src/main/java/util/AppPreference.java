package util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import model.User;

/**
 * Created by Ilija Angeleski on 12/14/2016.
 */

public class AppPreference {
    public static final String TAG = AppPreference.class.getName();

    Context context;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    public AppPreference(Context context) {
        this.context = context;
        this.sp = context.getSharedPreferences("eGym.preference", Context.MODE_PRIVATE);
        this.editor = sp.edit();
    }

    /**
     * Saving list users as json string in SharedPreferences
     * @param users
     */
    public void savedUsers(List<User> users){
        Log.d("AppPreferences", "savedUsers()");
        Gson gson = new Gson();
        editor.putString("app.savedUsers", gson.toJson(users));
        editor.commit();
    }

    /**
     * Getting saved json users and transforming them in Java collection
     * @return
     */
    public List<User> getSavedUsers() {
        Log.d("AppPreferences", "getSavedUsers()");
        Gson gson = new Gson();
        String jsonString = sp.getString("app.savedUsers", "");
        if (!jsonString.equals("")) {
            Type collectionType = new TypeToken<List<User>>() {
            }.getType();

            List<User> listObj = gson.fromJson(jsonString, collectionType);
            return listObj;
        }
        return Collections.emptyList();
    }
}
