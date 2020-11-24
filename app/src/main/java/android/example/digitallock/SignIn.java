package android.example.digitallock;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {
    EditText  mPassword, mEmail;
    Button  mLogin, mForgot;
    FirebaseAuth fAuth;
    ProgressBar mProgressBar;
    TextView mRegisterBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);


        mPassword = findViewById(R.id.mPassword);
        mEmail = findViewById(R.id.mEmail);
        mLogin = findViewById(R.id.Login);
        mForgot = findViewById(R.id.Forgot);
        fAuth = FirebaseAuth.getInstance();
        mProgressBar = findViewById(R.id.mProgressBar);
        mRegisterBtn=findViewById(R.id.RegisterHere);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is required");
                    return;
                }
                mProgressBar.setVisibility(View.VISIBLE);
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignIn.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),HomePage.class));
                            mProgressBar.setVisibility(View.GONE);


                        } else {
                            Toast.makeText(SignIn.this, "Error!" + task.getException(), Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.GONE);

                        }
                    }
                });

            }
        });

      mRegisterBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getApplicationContext(),SignUp.class));
          }
      });
      mForgot.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              final EditText resetMail=new EditText(v.getContext());
              final AlertDialog.Builder passwordResetDialog= new AlertDialog.Builder(v.getContext());
              passwordResetDialog.setTitle("Reset Password");
              passwordResetDialog.setMessage("Enter Your Email to recieve reset link");
              passwordResetDialog.setView(resetMail);

              passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      String mail= resetMail.getText().toString();
                      fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                          @Override
                          public void onSuccess(Void aVoid) {
                              Toast.makeText(SignIn.this,"Reset link sent to your email",Toast.LENGTH_SHORT).show();

                          }

                      }).addOnFailureListener(new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull Exception e) {
                              Toast.makeText(SignIn.this,"Error!!Reset link not sent.",Toast.LENGTH_SHORT).show();

                          }
                      });
                  }
              });
              passwordResetDialog.create().show();

          }
      });

    }
}
