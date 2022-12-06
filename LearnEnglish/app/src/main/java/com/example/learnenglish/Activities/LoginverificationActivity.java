package com.example.learnenglish.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.learnenglish.MainActivity;
import com.example.learnenglish.PushNotificationService;
import com.example.learnenglish.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginverificationActivity extends AppCompatActivity {

    ImageView loginver_img;
    ProgressBar loading;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginverification);


        loginver_img=findViewById(R.id.loginver_img);
        loading=findViewById(R.id.loding);


        auth=FirebaseAuth.getInstance();

        final FirebaseUser user=auth.getCurrentUser();

        final Animation zoomin = AnimationUtils.loadAnimation(this,R.anim.zoomin);
        final Animation zoomout= AnimationUtils.loadAnimation(this,R.anim.zoomout);

        loginver_img.setAnimation(zoomin);
        loginver_img.setAnimation(zoomout);

        zoomout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                loginver_img.startAnimation(zoomin);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        zoomin.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                loginver_img.startAnimation(zoomout);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        loginver_img.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);





        if(user.isEmailVerified())
        {
            loginver_img.setVisibility(View.VISIBLE);
        }

        else
        {
            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(LoginverificationActivity.this, "Verification Email is send to Your mail Please Check", Toast.LENGTH_SHORT).show();
                    FirebaseAuth.getInstance().signOut();
                }
            });
        }

        loginver_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.setVisibility(View.VISIBLE);
                Intent intent = new Intent(LoginverificationActivity.this, PushNotificationService.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(LoginverificationActivity.this,0,intent,0);
                AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);
                long timeAtButtonClick = System.currentTimeMillis();

                long tenseconds = 2000*10;

                alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtButtonClick+tenseconds,pendingIntent);
                startActivity(new Intent(LoginverificationActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}