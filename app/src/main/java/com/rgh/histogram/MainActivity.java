package com.rgh.histogram;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    HistogramView mHistogramView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHistogramView= (HistogramView) findViewById(R.id.hv);

         int[][] columInfo=new int[][]{
                 {1, Color.YELLOW},
                 {2, Color.RED},
                 {4, Color.GRAY},
                 {5, Color.BLACK},
                 {7, Color.BLUE},
                 {3, Color.GREEN},
                 {6, Color.CYAN}

         };
         String[] columInfoName=new String[]{"苹果","三星","魅族","华为","小米","vivo","oppo"};

        mHistogramView.setColumInfo(columInfo);
        mHistogramView.setColumInfoName(columInfoName);
        mHistogramView.setXAxisValue(10,9);
        mHistogramView.setYAxisValue(12,10);

    }
}
