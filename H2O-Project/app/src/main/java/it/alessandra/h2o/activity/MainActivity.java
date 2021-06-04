package it.alessandra.h2o.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.RenderMode;
import com.google.android.material.snackbar.Snackbar;

import it.alessandra.h2o.R;
import it.alessandra.h2o.utilities.Constants;
import it.alessandra.h2o.utilities.Utilitys;

public class MainActivity extends AppCompatActivity {  //AppCompactActivity aggiunge app bar

    private SharedPreferences sharedPreferences;
    private LottieAnimationView lottieAnimationView;
    private TextView waterRemaining;
    private Button onboarding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lottieAnimationView = (LottieAnimationView) findViewById(R.id.drop);
        waterRemaining = (TextView) findViewById(R.id.waterRemaining);
        sharedPreferences = getApplicationContext().getSharedPreferences("h2o", MODE_PRIVATE);
        onboarding = findViewById(R.id.button);

        onboarding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OnBoardingActivity.getIstanceIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        //Salvo i dati nello sharedPreference (imposto un water target hardcoded)
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.KEY_WATER_TARGET,1000);
        editor.apply();

        int total;
        //Prendo i dati
        if(sharedPreferences.getInt(Constants.KEY_TOTAL_WATER,0)==0){
            total = sharedPreferences.getInt(Constants.KEY_WATER_TARGET,0);
        }else{
            total = sharedPreferences.getInt(Constants.KEY_TOTAL_WATER,0);
        }

        //Setto i dati nella view
        waterRemaining.setText(String.valueOf(total));

        Float perc = Utilitys.getPercOfTotal(Float.parseFloat(String.valueOf(sharedPreferences.getInt(Constants.KEY_WATER_TARGET,0))),Float.parseFloat(String.valueOf(total)));

        //Imposto animazione
        //TODO Impostre percentuale anche in base ai frame dell'animazione
        final Handler handler = new Handler();
        lottieAnimationView.setMinAndMaxFrame(0, Math.round(perc));
        lottieAnimationView.playAnimation();
        lottieAnimationView.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                lottieAnimationView.setAnimation(R.raw.loop_water);
                lottieAnimationView.setMinAndMaxFrame(Math.round(perc)-15, Math.round(perc));
                lottieAnimationView.setSpeed((float) 0.5);
                handler.postDelayed(() -> {
                    // do something after 1
                    lottieAnimationView.playAnimation();
                }, 1500);
            }
        });
    }

    public void viewSetting(View view){
        Intent settingsView = new Intent(this,SettingsActivity.class);
        startActivity(settingsView);
    }

    public void addWater(View view){
        Intent addWaterView = new Intent(MainActivity.this,AddWaterActivity.class);
        startActivity(addWaterView);
    }
}
