package com.example.sscsis.mappybird;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by sscsis on 1/7/15.
 */
public class GameCanvas extends SurfaceView implements SurfaceHolder.Callback {

    public GameCanvas(Context context) {
        super(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void render(Canvas canvas) {

        canvas.drawColor(Color.GREEN);

    }

    public void update() {

    }


}
