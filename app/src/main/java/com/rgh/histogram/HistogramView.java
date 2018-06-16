package com.rgh.histogram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * @author by RGH
 * @date on 2018/6/14.
 */

public class HistogramView extends BaseView {
    public HistogramView(Context context) {
        this(context, null);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void drawColumn(Canvas canvas, Paint paint) {
        if (columInfo != null) {
            float cellWidth = width / axisDivideSizeX;
            for (int i = 0; i < columInfo.length; i++) {
                paint.setColor(columInfo[i][1]);
                float leftTopY = originalY - height * (columInfo[i][0]) / axisDivideSizeY;
                canvas.drawRect(originalX + cellWidth * (i + 1), leftTopY, originalX + cellWidth * (i + 2), originalY, paint);
                paint.setColor(Color.BLACK);
                canvas.drawText( (columInfo[i][0])+ "", cellWidth * (i + 1) + originalX+(cellWidth/2), leftTopY-10, paint);
                canvas.save();
                canvas.drawText(columInfoName[i], cellWidth * (i + 1) + originalX+10, originalY + 30, paint);
            }
        }

    }

    @Override
    protected void drawYAxisScaleValue(Canvas canvas, Paint paint) {
        float cellHeigh = height / axisDivideSizeY;
        float cellValue = maxAxisValueY / axisDivideSizeY;

        for (int i = 1; i < axisDivideSizeY; i++) {
          //  canvas.drawText((cellValue * i) + "", originalX - 30, originalY - cellHeigh * i + 10, paint);
            canvas.drawText(String.valueOf(i), originalX - 30, originalY - cellHeigh * i + 10, paint);
        }
    }

    @Override
    protected void drawYAxisScale(Canvas canvas, Paint paint) {

        float cellHeigh = height / axisDivideSizeY;

        for (int i = 0; i < axisDivideSizeY - 1; i++) {
            canvas.drawLine(originalX, (originalY - cellHeigh * (i + 1)),
                    originalX + 10, (originalY - cellHeigh * (i + 1)), paint);

        }
    }

    @Override
    protected void drawXAxisScaleValue(Canvas canvas, Paint paint) {

       /* paint.setColor(Color.GRAY);
        paint.setTextSize(20);
        paint.setFakeBoldText(true);

        float cellWidth = width / columInfo.length;
        for (int i = 1; i < columInfo.length; i++) {
            canvas.drawText(i + "", cellWidth * i + originalX - 10, originalY + 30, paint
            );
        }*/
    }

    @Override
    protected void drawXAxisScale(Canvas canvas, Paint paint) {

        float cellWidth = width / axisDivideSizeX;

        for (int i = 0; i < axisDivideSizeX - 1; i++) {
            canvas.drawLine(cellWidth * (i + 1) + originalX, originalY, cellWidth * (i + 1) + originalX, originalY - 10, paint);

        }

    }

    @Override
    protected void drawXAxis(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        canvas.drawLine(originalX, originalY, originalX, originalY - height, paint);
    }

    @Override
    protected void drawYAxis(Canvas canvas, Paint paint) {

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        canvas.drawLine(originalX, originalY, originalX + width, originalY, paint);

    }
}
