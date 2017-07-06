package com.example.phituocpc.jumping;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity implements Animation.AnimationListener {

    Animation animRotate;
    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 1 * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ImageView imgRotate = (ImageView) findViewById(R.id.imgSplashScreen);
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        animRotate.setAnimationListener(this);
        imgRotate.startAnimation(animRotate);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                 Create an Intent that will start the Menu-Activity.
                try {
                    Class clazz = Class
                            .forName("com.example.phituocpc.jumping.jumper.SuperJumper");
                    Intent intent = new Intent(MainActivity.this, clazz);
                    MainActivity.this.startActivity(intent);
                    MainActivity.this.finish();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}
