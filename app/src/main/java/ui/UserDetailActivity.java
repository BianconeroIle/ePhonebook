package ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilijaangeleski.egym.R;
import com.squareup.picasso.Picasso;

import interfaces.ApiConstants;
import interfaces.UserAPI;
import model.Location;
import model.User;
import retrofit.RestAdapter;
import util.CircleTransform;

/**
 * Created by Ilija Angeleski on 12/13/2016.
 */

public class UserDetailActivity extends AppCompatActivity {
    public static final String TAG = UserDetailActivity.class.getName();
    private ImageView profileImage;
    private TextView title;
    private TextView firstName;
    private TextView lastName;
    private TextView email;
    private TextView username;
    private TextView streetInfo;
    private ImageView callImage;
    private ImageView smsImage;
    private TextView nationality;
    private TextView phoneNumber;
    private TextView postcodeInfo;
    private UserAPI api;
    private User user = null;
    private Context context = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_detail_activity);
        initVariables();
        initListeners();
    }

    /**
     * init Activity variables
     */
    private void initVariables() {
        profileImage = (ImageView) findViewById(R.id.profileImage);
        title = (TextView) findViewById(R.id.title);
        firstName = (TextView) findViewById(R.id.firstName);
        lastName = (TextView) findViewById(R.id.lastName);
        username = (TextView) findViewById(R.id.username);
        streetInfo = (TextView) findViewById(R.id.streetinfo);
        callImage = (ImageView) findViewById(R.id.callImage);
        smsImage = (ImageView) findViewById(R.id.smsImage);
        nationality = (TextView) findViewById(R.id.nationality);
        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        email = (TextView) findViewById(R.id.email);
        postcodeInfo = (TextView) findViewById(R.id.postcodeInfo);


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ApiConstants.URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        api = restAdapter.create(UserAPI.class);

        user = (User) getIntent().getExtras().getSerializable("clickedUser");


        Picasso.with(context).load(user.getPicture().getLarge()).transform(new CircleTransform()).placeholder(R.mipmap.ic_profile).error(R.mipmap.ic_profile).fit().into(profileImage);

        title.setText(user.getName().getTitle());
        firstName.setText(user.getName().getFirst());
        lastName.setText(user.getName().getLast());
        username.setText(user.getLogin().getUsername());
        streetInfo.setText(getStreetInfo(user.getLocation()));

        postcodeInfo.setText(user.getLocation().getPostcode());
        nationality.setText(user.getNat());
        phoneNumber.setText(user.getPhone());
        email.setText(user.getEmail());
    }

    /**
     * init Activity listeners
     */
    public void initListeners() {
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        streetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });
        callImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call();
            }

        });
        smsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSms();
            }
        });
    }

    /**
     * get picture and get Pallete colors
     */
    private void getPicture() {
         /*       Picasso.with(this).load(user.getPicture().getLarge()).transform(new CircleTransform()).into(profileImage,
                PicassoPalette.with(user.getPicture().getLarge(), profileImage)
                        .use(PicassoPalette.Profile.VIBRANT)
                        .intoBackground(imageHolder)

                        *//*.intoCallBack(new PicassoPalette.CallBack(){

                            @Override
                            public void onPaletteLoaded(Palette palette) {
                                Palette.Swatch base = palette.getVibrantSwatch();
                                changeActionbarColor(palette.getVibrantColor(base.getRgb()),palette.getLightVibrantColor(base.getRgb()));
                            }
                        })*//*
        );*/
    }

    /**
     * Intent for sending the email and getting the current user email address
     */
    public void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{user.getEmail()});
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
        startActivity(Intent.createChooser(intent, ""));
    }

    /**
     * Intent for getting the location of the user
     */
    public void openMap() {
        String map = "http://maps.google.co.in/maps?q=" + getStreetInfo(user.getLocation());
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(map));
        startActivity(intent);
    }

    /**
     * Check for permisions and sending Intent for making a call
     */
    public void call() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + user.getPhone()));
            startActivity(intent);
            return;
        }
    }

    /**
     * Intent for sending SMS to the user
     */
    public void sendSms() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", user.getPhone(), null)));
    }

    /**
     * Get location and check if there some of the values are NULL
     *
     * @param l
     * @return formated address
     */
    private String getStreetInfo(Location l) {
        StringBuilder b = new StringBuilder();
        b.append(l.getStreet());

        if (l.getCity() != null) {
            b.append(", " + l.getCity());
        }

        if (l.getState() != null) {
            b.append(", " + l.getState());
        }
        return b.toString();
    }
}
