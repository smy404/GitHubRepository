package com.smy.gensuiview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class RabbitView extends View {
    public float bitmapX;
    public float bitmapY;

    public RabbitView(Context context) {
        super(context);
        bitmapX=290;
        bitmapY=290;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //定义画笔对象
        Paint paint = new Paint();
        //定义一个位图对象
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.mipmap.astronaut);
        //参一是resource对象，参二是图像资源id
        canvas.drawBitmap(bitmap,bitmapX,bitmapY,paint);
        if(bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
