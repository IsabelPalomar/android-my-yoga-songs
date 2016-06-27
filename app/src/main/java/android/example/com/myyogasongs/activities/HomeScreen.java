package android.example.com.myyogasongs.activities;

import android.content.Intent;
import android.example.com.myyogasongs.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        //Get the startBtn view and creates an intent to go to MainActivity activity
        Button startBtn = (Button)findViewById(R.id.btn_start_application);
        if (startBtn != null) {
            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(HomeScreen.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }

    }

}
