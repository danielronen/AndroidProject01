package com.example.danielapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {
    private EditText tv;
    private ImageView flagImage;

    public static float screen_width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screen_width = metrics.widthPixels;
        tv = findViewById(R.id.textView);
        Button search = findViewById(R.id.button);
        flagImage = findViewById(R.id.im_flag);

        search.setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            String countryCode = tv.getText().toString();
            String apiUrl = "https://countryflagsapi.com/png/" + countryCode.toLowerCase();
            System.out.println(apiUrl);
            Picasso.get().load(apiUrl).fit().into(flagImage);
        });


        flagImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float scale = MainActivity.screen_width / view.getWidth();
                if (view.getScaleX() == 1) {
                    view.setScaleY(scale);
                    view.setScaleX(scale);
                } else {
                    view.setScaleY(1);
                    view.setScaleX(1);
                }
            }


        });


    }


}