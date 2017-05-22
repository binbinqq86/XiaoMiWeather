package com.example.tb.xiaomiweather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


import java.util.List;

/**
 * Created by tb on 2017/5/13.
 * 未来若干天天气温度图表
 */

public class FutureDaysChart extends View {
    private static final String TAG = "FutureDaysChart";
    private static final float LINE_SMOOTHNESS = 0.16f;
    private float eachChartHeight;
    private float perHeightTop,perHeightBottom;
    private static final int padding=100;
    private float pointRaidus=10;
    private float minHigh,minLow,maxHigh,maxLow;
    private List<Weather> datas;
    private Paint linePaint = new Paint();
    private Paint pointPaint = new Paint();
    private TextPaint labelPaint=new TextPaint();
    public FutureDaysChart(Context context) {
        this(context,null);
    }

    public FutureDaysChart(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FutureDaysChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FutureDaysChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
//        linePaint.setPathEffect(line.getPathEffect());
        linePaint.setShader(null);
        linePaint.setStrokeWidth(2);
        linePaint.setColor(0xff48c4fa);

        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(0xff48c4fa);

        labelPaint.setAntiAlias(true);
        labelPaint.setColor(Color.GRAY);
        labelPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics()));

        eachChartHeight=getResources().getDimension(R.dimen.sevenday_chart_height)/2f-padding;
    }

    public void setDatas(List<Weather> datas){
        this.datas=datas;

        lineSize=datas.size();

        minHigh=datas.get(0).getHighTemperature();
        maxHigh=datas.get(0).getHighTemperature();

        minLow=datas.get(0).getLowTemperature();
        maxLow=datas.get(0).getLowTemperature();
        for (int i = 1; i < datas.size(); i++) {
            float fh=datas.get(i).getHighTemperature();
            if(minHigh>fh){
                minHigh=fh;
            }
            if(maxHigh<fh){
                maxHigh=fh;
            }
            float fl=datas.get(i).getLowTemperature();
            if(minLow>fl){
                minLow=fl;
            }
            if(maxLow<fl){
                maxLow=fl;
            }
        }

        //转换比例，找出最大和最小进行换算每个梯度所占像素
        perHeightTop=eachChartHeight/(maxHigh-minHigh);
        perHeightBottom=eachChartHeight/(maxLow-minLow);

        postInvalidate();
    }


    private int lineSize=0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(datas==null||datas.size()==0){
            return;
        }

        float prePreviousPointX = Float.NaN;
        float prePreviousPointY = Float.NaN;
        float previousPointX = Float.NaN;
        float previousPointY = Float.NaN;
        float currentPointX = Float.NaN;
        float currentPointY = Float.NaN;
        float nextPointX = Float.NaN;
        float nextPointY = Float.NaN;

        float eachWidth=getResources().getDimension(R.dimen.sevenday_each_width);
        float lineWidth=getResources().getDimension(R.dimen.sevenday_line);
        Path path=new Path();

        //画上面的
        for (int i = 0; i < lineSize; i++) {
            float x=eachWidth/2f+i*(eachWidth+lineWidth);
            float y=getResources().getDimension(R.dimen.sevenday_chart_height)/2f-padding/2-(datas.get(i).getHighTemperature()-minHigh)*perHeightTop;
            canvas.drawCircle(x,y,pointRaidus,pointPaint);


            if (Float.isNaN(currentPointX)) {
                currentPointX = eachWidth/2f+i*(eachWidth+lineWidth);
                currentPointY = getResources().getDimension(R.dimen.sevenday_chart_height)/2f-padding/2-(datas.get(i).getHighTemperature()-minHigh)*perHeightTop;
            }
            if (Float.isNaN(previousPointX)) {
                if (i > 0) {
                    previousPointX = eachWidth/2f+(i-1)*(eachWidth+lineWidth);
                    previousPointY = getResources().getDimension(R.dimen.sevenday_chart_height)/2f-padding/2-(datas.get(i-1).getHighTemperature()-minHigh)*perHeightTop;
                } else {
                    previousPointX = currentPointX;
                    previousPointY = currentPointY;
                }
            }

            if (Float.isNaN(prePreviousPointX)) {
                if (i > 1) {
                    prePreviousPointX = eachWidth/2f+(i-2)*(eachWidth+lineWidth);
                    prePreviousPointY = getResources().getDimension(R.dimen.sevenday_chart_height)/2f-padding/2-(datas.get(i-2).getHighTemperature()-minHigh)*perHeightTop;
                } else {
                    prePreviousPointX = previousPointX;
                    prePreviousPointY = previousPointY;
                }
            }

            // nextPoint is always new one or it is equal currentPoint.
            if (i < lineSize - 1) {
                nextPointX = eachWidth/2f+(i+1)*(eachWidth+lineWidth);
                nextPointY = getResources().getDimension(R.dimen.sevenday_chart_height)/2f-padding/2-(datas.get(i+1).getHighTemperature()-minHigh)*perHeightTop;
            } else {
                nextPointX = currentPointX;
                nextPointY = currentPointY;
            }

            if (i == 0) {
                // Move to start point.
                path.moveTo(currentPointX, currentPointY);
            } else {
                // Calculate control points.
                final float firstDiffX = (currentPointX - prePreviousPointX);
                final float firstDiffY = (currentPointY - prePreviousPointY);
                final float secondDiffX = (nextPointX - previousPointX);
                final float secondDiffY = (nextPointY - previousPointY);
                final float firstControlPointX = previousPointX + (LINE_SMOOTHNESS * firstDiffX);
                final float firstControlPointY = previousPointY + (LINE_SMOOTHNESS * firstDiffY);
                final float secondControlPointX = currentPointX - (LINE_SMOOTHNESS * secondDiffX);
                final float secondControlPointY = currentPointY - (LINE_SMOOTHNESS * secondDiffY);
                path.cubicTo(firstControlPointX, firstControlPointY, secondControlPointX, secondControlPointY, currentPointX, currentPointY);
            }

            // Shift values by one back to prevent recalculation of values that have
            // been already calculated.
            prePreviousPointX = previousPointX;
            prePreviousPointY = previousPointY;
            previousPointX = currentPointX;
            previousPointY = currentPointY;
            currentPointX = nextPointX;
            currentPointY = nextPointY;
//            if(i==0){
//                path.moveTo(x,y);
//            }else{
//                path.lineTo(x,y);
//            }
            canvas.drawPath(path,linePaint);

            String label=(int)datas.get(i).getHighTemperature()+"°";
            canvas.drawText(label,x-labelPaint.measureText(label)/2,y-pointRaidus*2,labelPaint);
        }

        prePreviousPointX = Float.NaN;
        prePreviousPointY = Float.NaN;
        previousPointX = Float.NaN;
        previousPointY = Float.NaN;
        currentPointX = Float.NaN;
        currentPointY = Float.NaN;
        nextPointX = Float.NaN;
        nextPointY = Float.NaN;
        //画下面的
        for (int i = 0; i < lineSize; i++) {
            float x=eachWidth/2f+i*(eachWidth+lineWidth);
            float y=getResources().getDimension(R.dimen.sevenday_chart_height)-padding/2-(datas.get(i).getLowTemperature()-minLow)*perHeightBottom;
            canvas.drawCircle(x,y,pointRaidus,pointPaint);


            if (Float.isNaN(currentPointX)) {
                currentPointX = eachWidth/2f+i*(eachWidth+lineWidth);
                currentPointY = getResources().getDimension(R.dimen.sevenday_chart_height)-padding/2-(datas.get(i).getLowTemperature()-minLow)*perHeightBottom;
            }
            if (Float.isNaN(previousPointX)) {
                if (i > 0) {
                    previousPointX = eachWidth/2f+(i-1)*(eachWidth+lineWidth);
                    previousPointY = getResources().getDimension(R.dimen.sevenday_chart_height)-padding/2-(datas.get(i-1).getLowTemperature()-minLow)*perHeightBottom;
                } else {
                    previousPointX = currentPointX;
                    previousPointY = currentPointY;
                }
            }

            if (Float.isNaN(prePreviousPointX)) {
                if (i > 1) {
                    prePreviousPointX = eachWidth/2f+(i-2)*(eachWidth+lineWidth);
                    prePreviousPointY = getResources().getDimension(R.dimen.sevenday_chart_height)-padding/2-(datas.get(i-2).getLowTemperature()-minLow)*perHeightBottom;
                } else {
                    prePreviousPointX = previousPointX;
                    prePreviousPointY = previousPointY;
                }
            }

            // nextPoint is always new one or it is equal currentPoint.
            if (i < lineSize - 1) {
                nextPointX = eachWidth/2f+(i+1)*(eachWidth+lineWidth);
                nextPointY = getResources().getDimension(R.dimen.sevenday_chart_height)-padding/2-(datas.get(i+1).getLowTemperature()-minLow)*perHeightBottom;
            } else {
                nextPointX = currentPointX;
                nextPointY = currentPointY;
            }

            if (i == 0) {
                // Move to start point.
                path.moveTo(currentPointX, currentPointY);
            } else {
                // Calculate control points.
                final float firstDiffX = (currentPointX - prePreviousPointX);
                final float firstDiffY = (currentPointY - prePreviousPointY);
                final float secondDiffX = (nextPointX - previousPointX);
                final float secondDiffY = (nextPointY - previousPointY);
                final float firstControlPointX = previousPointX + (LINE_SMOOTHNESS * firstDiffX);
                final float firstControlPointY = previousPointY + (LINE_SMOOTHNESS * firstDiffY);
                final float secondControlPointX = currentPointX - (LINE_SMOOTHNESS * secondDiffX);
                final float secondControlPointY = currentPointY - (LINE_SMOOTHNESS * secondDiffY);
                path.cubicTo(firstControlPointX, firstControlPointY, secondControlPointX, secondControlPointY, currentPointX, currentPointY);
            }

            // Shift values by one back to prevent recalculation of values that have
            // been already calculated.
            prePreviousPointX = previousPointX;
            prePreviousPointY = previousPointY;
            previousPointX = currentPointX;
            previousPointY = currentPointY;
            currentPointX = nextPointX;
            currentPointY = nextPointY;
//            if(i==0){
//                path.moveTo(x,y);
//            }else{
//                path.lineTo(x,y);
//            }
            canvas.drawPath(path,linePaint);

            String label=(int)datas.get(i).getLowTemperature()+"°";
            float labelHeight=labelPaint.getFontMetrics().bottom-labelPaint.getFontMetrics().top;
            canvas.drawText(label,x-labelPaint.measureText(label)/2,y+labelHeight,labelPaint);
        }
    }
}
