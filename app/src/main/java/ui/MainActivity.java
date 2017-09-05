package ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilijaangeleski.egym.R;

import java.util.ArrayList;
import java.util.List;

import adapter.RecyclerAdapter;
import interfaces.ApiConstants;
import interfaces.UserAPI;
import model.ResponseDTO;
import model.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import util.AppPreference;
import util.AppUtils;
import util.EndlessRecyclerViewScrollListener;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getName();

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
    private UserAPI api;
    private TextView alertText;
    private int currentPage = 1;
    private List<User> items = new ArrayList<>();
    private EndlessRecyclerViewScrollListener scrollListener;
    private AppPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariables();
        initListeners();

        checkInternetAndLoadData();
    }

    /**
     * check for internet connection and load data
     */
    private void checkInternetAndLoadData() {
        if (AppUtils.checkForInternetConnection(getApplicationContext())) {
            Log.d(TAG, "get users from server!");
            getUsers(currentPage);
        } else {
            Log.d(TAG, "get users from local storage!");
            if (preference.getSavedUsers().size() == 0) {
                recyclerView.setVisibility(View.GONE);
                alertText.setVisibility(View.VISIBLE);
            } else {
                items.addAll(preference.getSavedUsers());
                recyclerAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * init Activity variables
     */
    public void initVariables() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        api = restAdapter.create(UserAPI.class);
        preference = new AppPreference(this);
        alertText = (TextView) findViewById(R.id.alertText);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);


        recyclerAdapter = new RecyclerAdapter(items, getApplicationContext(), R.layout.item);
        recyclerView.setAdapter(recyclerAdapter);


    }

    public void loadNextDataFromAPI(int currentPage) {
        getUsers(currentPage);
    }

    /**
     * init Activity listeners
     */
    private void initListeners() {

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                currentPage++;
                Log.d(TAG, "onLoadMore page=" + currentPage);
                loadNextDataFromAPI(currentPage);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);


        recyclerAdapter.setOnUserItemClick(new RecyclerAdapter.OnUserItemClick() {
            @Override
            public void onUserClick(User user, ImageView imageView) {
                Log.d(TAG, "onUserClick :" + user);
                Intent intent = new Intent(MainActivity.this, UserDetailActivity.class);
                intent.putExtra("clickedUser", user);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this, imageView, "profile");
                startActivity(intent, options.toBundle());
            }
        });
    }


    /**
     * Get users from the server
     *
     * @param page
     */
    public void getUsers(int page) {
        api.getUsers("egym", page, 10, new Callback<ResponseDTO>() {
            @Override
            public void success(ResponseDTO responseDTO, Response response) {
                Log.d(TAG, "successful getting users from server" + responseDTO);
                items.addAll(responseDTO.getResults());
                preference.savedUsers(items);
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "error getting users from server" + error);
            }
        });
    }
}
