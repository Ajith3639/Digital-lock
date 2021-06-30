package android.example.digitallock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        Button SignUp = (Button) findViewById(R.id.SignUp);
        SignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent UpIntent = new Intent(MainActivity.this, SignUp.class);

                // Start the new activity
                startActivity(UpIntent);
            }
        });
        Button SignIn= (Button) findViewById(R.id.SignIn);
        SignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                Intent InIntent = new Intent(MainActivity.this, SignIn.class);

                // Start the new activity
                startActivity(InIntent);
            }
        });



        }

}
