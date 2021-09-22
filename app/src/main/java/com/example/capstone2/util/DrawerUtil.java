package com.example.capstone2.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.capstone2.GraphActivity;
import com.example.capstone2.GraphActivity2;
import com.example.capstone2.MainActivity;
import com.example.capstone2.R;
import com.example.capstone2.UsefulActivity;

public class DrawerUtil {

    public static DrawerUtil drawerUtil = new DrawerUtil();

    private Context mContext;

    public void setDrawerListener(View view, Context context){
        mContext = context;

        Button btn_home = view.findViewById(R.id.drawer_btn_home);
        Button btn_top3 = view.findViewById(R.id.drawer_btn_top3);
        Button btn_graph = view.findViewById(R.id.drawer_btn_graph);
        Button btn_graph2 = view.findViewById(R.id.drawer_btn_graph2);

        btn_home.setOnClickListener(onClickListener);
        btn_top3.setOnClickListener(onClickListener);
        btn_graph.setOnClickListener(onClickListener);
        btn_graph2.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.drawer_btn_home){

                if(!(((Activity)mContext) instanceof MainActivity))
                {
                   Intent intent = new Intent(mContext,MainActivity.class);
                    ((Activity)mContext).startActivity(intent);

                }

            }
            else if(v.getId() == R.id.drawer_btn_top3){
                if(!(((Activity)mContext) instanceof UsefulActivity))
                {
                    Intent intent = new Intent(mContext, UsefulActivity.class);
                    ((Activity)mContext).startActivity(intent);
                }

            }
            else if(v.getId() == R.id.drawer_btn_graph){
                if(!(((Activity)mContext) instanceof GraphActivity))
                {
                    Intent intent = new Intent(mContext, GraphActivity.class);
                    ((Activity)mContext).startActivity(intent);
                }

            }
            else if(v.getId() == R.id.drawer_btn_graph2){
                if(!(((Activity)mContext) instanceof GraphActivity2))
                {
                    Intent intent = new Intent(mContext, GraphActivity2.class);
                    ((Activity)mContext).startActivity(intent);
                }
            }
        }
    };
}
