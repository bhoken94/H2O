package it.alessandra.h2o.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import it.alessandra.h2o.R;
import it.alessandra.h2o.utilities.Constants;

public class AddWaterActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private SeekBar seekBar;
    private TextView percentuale;
    private TextView unitText;
    private Button confirm;
    private SharedPreferences sharedPreferences, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water);

        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        seekBar = (SeekBar)findViewById(R.id.seekBarWater);
        percentuale = (TextView)findViewById(R.id.percentual);
        unitText = (TextView)findViewById(R.id.unit_text);
        confirm = (Button)findViewById(R.id.addCupButton);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences = getApplicationContext().getSharedPreferences("h2o", MODE_PRIVATE);
        final String WATER_UNIT = settings.getString(Constants.KEY_UNIT,"");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(WATER_UNIT.equals("mL")){
                    seekBar.setMax(250);
                    progressBar.setMax(250);
                }else {
                    seekBar.setMax(3);
                    progressBar.setMax(3);
                }
                unitText.setText(WATER_UNIT);
                progressBar.setProgress(progress);
                percentuale.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                //Controllo se è presente già un total water
                if(sharedPreferences.getInt(Constants.KEY_TOTAL_WATER,0)==0){
                    int total = sharedPreferences.getInt(Constants.KEY_WATER_TARGET,0);
                    editor.putInt(Constants.KEY_TOTAL_WATER,total - seekBar.getProgress());
                    editor.apply();
                }else{
                    int total = sharedPreferences.getInt(Constants.KEY_TOTAL_WATER,0);
                    editor.remove(Constants.KEY_TOTAL_WATER);
                    editor.putInt(Constants.KEY_TOTAL_WATER,total - seekBar.getProgress());
                    editor.apply();
                }

                Toast.makeText(getApplicationContext(),"Grande!",Toast.LENGTH_SHORT).show();
                returnHome(v);
            }
        });
    }

    public void returnHome(View view){
        Intent home = new Intent(AddWaterActivity.this, MainActivity.class);
        startActivity(home);
    }
}
