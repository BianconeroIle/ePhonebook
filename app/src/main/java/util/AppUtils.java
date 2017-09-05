package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.User;

/**
 * Created by Ilija Angeleski on 12/13/2016.
 */

public class AppUtils {

    public static final List<User> users = new ArrayList<>();
    public static List<User> getUsers() {
        return users;
    }

    /**
     * check for internet connection
     *
     * @param context
     * @return
     */
    public static boolean checkForInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        } else
            return false;
    }
}
