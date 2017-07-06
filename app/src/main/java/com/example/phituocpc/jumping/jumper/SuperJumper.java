package com.example.phituocpc.jumping.jumper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.androidgames.framework.gl.Screen;
import com.badlogic.androidgames.framework.gl.GLGame;
import com.example.phituocpc.jumping.jumper.model.Assets;
import com.example.phituocpc.jumping.jumper.view_controller.MainMenuScreen;
import com.example.phituocpc.jumping.jumper.view_controller.Settings;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.Sharer;
import com.facebook.share.widget.ShareDialog;

import static android.content.ContentValues.TAG;

/**
 * Created by dell on 8/5/2016.
 * nguyen van phi tuoc
 */
public class SuperJumper extends GLGame {
    boolean firstTimeCreate = true;
    public LoginButton fbLoginBtn;
    public ShareDialog shareDialog;
    CallbackManager callbackManager;
    public Bitmap imageBitmap;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static int REQUEST_CODE = RESULT_CANCELED;

    @Override
    public Screen getStartScreen() {
        return new MainMenuScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        if (firstTimeCreate) {
            callbackManager = CallbackManager.Factory.create();

            AppEventsLogger.activateApp(getApplication());
            fbLoginBtn = new LoginButton(this);
            shareDialog = new ShareDialog(this);
            fbLoginBtn.setReadPermissions("email");
            // If using in a fragment
//            fbLoginBtn.setFragment(this);
            // Other app specific specialization
            shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                @Override
                public void onSuccess(Sharer.Result result) {
                    Toast.makeText(getApplicationContext(), "Share in successfully.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancel() {
                    Toast.makeText(getApplicationContext(), "Error occurred while Share in. Please try again.", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(FacebookException error) {
                    Toast.makeText(getApplicationContext(), "Share in canceled.", Toast.LENGTH_SHORT).show();
                }
            });
            // Callback registration
            fbLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // App code
                    Toast.makeText(getApplicationContext(), "Logged in successfully!", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Facebook login successful.");
                }

                @Override
                public void onCancel() {
                    // App code
                    Toast.makeText(getApplicationContext(), "Logging in canceled.", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Facebook login canceled.");
                }

                @Override
                public void onError(FacebookException exception) {
                    // App code
                    Toast.makeText(getApplicationContext(), "Error occurred while logging in. Please try again.", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Facebook login error.");
                }
            });
            Settings.load(getFileIO());
            Assets.load(this);
            firstTimeCreate = false;
        } else {
            Assets.reload();
        }
    }

    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Settings.soundEnabled)
            Assets.music.pause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            REQUEST_CODE = RESULT_OK;
//            ShareDialog shareDialog = ((SuperJumper) glGame).shareDialog;

        } else
            callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
