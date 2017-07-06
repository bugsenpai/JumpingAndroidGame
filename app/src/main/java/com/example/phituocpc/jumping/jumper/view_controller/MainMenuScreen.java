package com.example.phituocpc.jumping.jumper.view_controller;


import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.badlogic.androidgames.framework.gl.Game;
import com.badlogic.androidgames.framework.input.Input;
import com.badlogic.androidgames.framework.impl.Camera2D;
import com.badlogic.androidgames.framework.impl.SpriteBatcher;
import com.badlogic.androidgames.framework.gl.GLScreen;
import com.badlogic.androidgames.framework.gamedev2d.math.OverlapTester;
import com.badlogic.androidgames.framework.gamedev2d.math.Rectangle;
import com.badlogic.androidgames.framework.gamedev2d.math.Vector2;
import com.example.phituocpc.jumping.jumper.SuperJumper;
import com.example.phituocpc.jumping.jumper.model.Assets;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by dell on 8/5/2016.
 * nguyen van phi tuoc
 */
public class MainMenuScreen extends GLScreen {
    private Camera2D guiCam;
    private SpriteBatcher batcher;
    private Rectangle soundBounds;
    private Rectangle playBounds;
    private Rectangle highscoresBounds;
    private Rectangle helpBounds;
    private Rectangle exitBounds;
    private Rectangle facebookBounds;
    private Vector2 touchPoint;

    public MainMenuScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 320, 480);
        batcher = new SpriteBatcher(glGraphics, 100);
        soundBounds = new Rectangle(0, 0, 64, 64);
        playBounds = new Rectangle(160 - 150, 200 + 18, 300, 36);
        highscoresBounds = new Rectangle(160 - 150, 200 - 18, 300, 36);
        helpBounds = new Rectangle(160 - 150, 200 - 18 - 36, 300, 36);
        exitBounds = new Rectangle(320 - 64, 0, 64, 64);
        facebookBounds = new Rectangle(160 - 16, 200 + 110 - 16, 32, 32);
        touchPoint = new Vector2();
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
                if (OverlapTester.pointInRectangle(playBounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new GameScreen(game));
                    return;
                }
                if (OverlapTester.pointInRectangle(highscoresBounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new HighScoreScreen(game));
                    return;
                }
                if (OverlapTester.pointInRectangle(helpBounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    game.setScreen(new HelpScreen1(game));
                    return;
                }
                if (OverlapTester.pointInRectangle(soundBounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled)
                        Assets.music.play();
                    else
                        Assets.music.pause();
                }
                if (OverlapTester.pointInRectangle(exitBounds, touchPoint)) {

                    Handler mainHandler = new Handler(Looper.getMainLooper());

                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
//                            ((SuperJumper) game).onBackPressed();
                            (glGame).onBackPressed();
                        } // This is your code
                    };
                    mainHandler.post(myRunnable);
                }
                if (OverlapTester.pointInRectangle(facebookBounds, touchPoint)) {
                    Handler mainHandler = new Handler(Looper.getMainLooper());

                    Runnable myRunnable = new Runnable() {
                        @Override
                        public void run() {
//                            ((SuperJumper) game).onBackPressed();
//                            ((SuperJumper) glGame).fbLoginBtn.performClick();
//                            ((SuperJumper) glGame).dispatchTakePictureIntent();
                            ShareDialog shareDialog =  ((SuperJumper) glGame).shareDialog;
                            if (ShareDialog.canShow(ShareLinkContent.class)) {
                                final ShareLinkContent contentLink = new ShareLinkContent.Builder()
                                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
                                        .setShareHashtag(new ShareHashtag.Builder()
                                                .setHashtag("#ConnectTheWorld")
                                                .setHashtag("#Jumper")
                                                .setHashtag("#NguyenVanPhiTuocProject")
                                                .build())
                                        .build();

                                shareDialog.show(contentLink);

                            }
                        } // This is your code
                    };
                    mainHandler.post(myRunnable);
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();
        gl.glEnable(GL10.GL_TEXTURE_2D);
        batcher.beginBatch(Assets.background);
        batcher.drawSprite(160, 240, 320, 480, Assets.backgroundRegion);
        batcher.endBatch();
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        batcher.beginBatch(Assets.items);
        batcher.drawSprite(160, 480 - 10 - 71, 274, 142, Assets.logo);
        batcher.drawSprite(160, 200, 300, 110, Assets.mainMenu);
        batcher.drawSprite(32, 32, 64, 64,
                Settings.soundEnabled ? Assets.soundOn : Assets.soundOff);
        batcher.drawSprite(320 - 32, 32, 64, 64, Assets.arrowVertical);
        batcher.drawSprite(160, 200 + 110, 32, 32, Assets.facebook);
        batcher.endBatch();
        gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void pause() {
        Settings.save(game.getFileIO());
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {

    }
}
