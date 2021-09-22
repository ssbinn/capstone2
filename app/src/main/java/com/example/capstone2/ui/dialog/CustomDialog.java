package com.example.capstone2.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.capstone2.GraphActivity;
import com.example.capstone2.R;
import com.example.capstone2.util.StationData;

public class CustomDialog {

    public static int START = 1000;
    public static int GOAL = 1001;

    private Context mContext;
    private float x;
    private float y;
    private ConstraintLayout lineView;
    private String targetStation;

    private TextView currentView;
    private String currentLine;
    private Dialog dialog;


    public CustomDialog(Context mContext, float x, float y, String targetStation) {
        this.mContext = mContext;
        this.x = x;
        this.y = y;
        this.targetStation = targetStation;
    }

    public Dialog getDialog() {
        return dialog;
    }

    public void create() {

        dialog=new Dialog(mContext,android.R.style.Theme_Black_NoTitleBar_Fullscreen);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_custom);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCancelable(false);


        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();

        dialog.show();

        ConstraintLayout constraintLayout = dialog.findViewById(R.id.custom_dialog);
        constraintLayout.setOnClickListener(dialogClickListener);

        lineView = dialog.findViewById(R.id.cl_line_list);
        ConstraintLayout dialogMainView = dialog.findViewById(R.id.cl_dialog_main);
        dialogMainView.setX((int)x-100);
        dialogMainView.setY((int)y-100);

        setLineView(targetStation);

        final TextView tvTargetStation = (TextView) dialogMainView.findViewById(R.id.tv_target_Station);
        tvTargetStation.setText(targetStation);


        final TextView tvStart = (TextView) dialogMainView.findViewById(R.id.tv_start);
        final TextView tvGoal = (TextView) dialogMainView.findViewById(R.id.tv_goal);
        tvStart.setOnClickListener(startGoalClickListener);
        tvGoal.setOnClickListener(startGoalClickListener);


        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                lineView.setVisibility(View.GONE);
            }
        });
    }


    private void setLineView(String targetStation) {
        lineView.setVisibility(View.VISIBLE);

        lineView.setOnClickListener(lineClickListener);
        TextView tvLine1 = lineView.findViewById(R.id.tv_line1);
        TextView tvLine2 = lineView.findViewById(R.id.tv_line2);
        TextView tvLine3 = lineView.findViewById(R.id.tv_line3);
        TextView tvLine5 = lineView.findViewById(R.id.tv_line5);
        TextView tvLine7 = lineView.findViewById(R.id.tv_line7);
        TextView tvLine9 = lineView.findViewById(R.id.tv_line9);

        tvLine1.setTag(R.string.none_click, R.drawable.custom_line1);
        tvLine1.setTag(R.string.click, R.drawable.custom_click_line1);

        tvLine2.setTag(R.string.none_click, R.drawable.custom_line2);
        tvLine2.setTag(R.string.click, R.drawable.custom_click_line2);

        tvLine3.setTag(R.string.none_click, R.drawable.custom_line3);
        tvLine3.setTag(R.string.click, R.drawable.custom_click_line3);

        tvLine5.setTag(R.string.none_click, R.drawable.custom_line5);
        tvLine5.setTag(R.string.click, R.drawable.custom_click_line5);

        tvLine7.setTag(R.string.none_click, R.drawable.custom_line7);
        tvLine7.setTag(R.string.click, R.drawable.custom_click_line7);

        tvLine9.setTag(R.string.none_click, R.drawable.custom_line9);
        tvLine9.setTag(R.string.click, R.drawable.custom_click_line9);

        tvLine1.setOnClickListener(lineClickListener);
        tvLine2.setOnClickListener(lineClickListener);
        tvLine3.setOnClickListener(lineClickListener);
        tvLine5.setOnClickListener(lineClickListener);
        tvLine7.setOnClickListener(lineClickListener);
        tvLine9.setOnClickListener(lineClickListener);


        switch (targetStation) {

            case "건대입구":
            case "대림": {

                tvLine1.setVisibility(View.GONE);
                tvLine2.setVisibility(View.VISIBLE);
                tvLine3.setVisibility(View.GONE);
                tvLine5.setVisibility(View.GONE);
                tvLine7.setVisibility(View.VISIBLE);
                tvLine9.setVisibility(View.GONE);

                tvLine2.setBackgroundResource((int)tvLine2.getTag(R.string.click));
                tvLine2.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                currentLine = "2";
                currentView = tvLine2;
                break;
            }

            case "고속터미널": {
                tvLine1.setVisibility(View.GONE);
                tvLine2.setVisibility(View.GONE);
                tvLine3.setVisibility(View.VISIBLE);
                tvLine5.setVisibility(View.GONE);
                tvLine7.setVisibility(View.VISIBLE);
                tvLine9.setVisibility(View.VISIBLE);

                tvLine3.setBackgroundResource((int)tvLine3.getTag(R.string.click));
                tvLine3.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                currentLine = "3";
                currentView = tvLine3;
                break;
            }

            case "교대": {

                tvLine1.setVisibility(View.GONE);
                tvLine2.setVisibility(View.VISIBLE);
                tvLine3.setVisibility(View.VISIBLE);
                tvLine5.setVisibility(View.GONE);
                tvLine7.setVisibility(View.GONE);
                tvLine9.setVisibility(View.GONE);

                tvLine2.setBackgroundResource((int)tvLine2.getTag(R.string.click));
                tvLine2.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                currentLine = "2";
                currentView = tvLine2;
                break;
            }

            case "영등포구청": {

                tvLine1.setVisibility(View.GONE);
                tvLine2.setVisibility(View.VISIBLE);
                tvLine3.setVisibility(View.GONE);
                tvLine5.setVisibility(View.VISIBLE);
                tvLine7.setVisibility(View.GONE);
                tvLine9.setVisibility(View.GONE);

                tvLine2.setBackgroundResource((int)tvLine2.getTag(R.string.click));
                tvLine2.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                currentLine = "2";
                currentView = tvLine2;
                break;
            }

            case "이대": {

                tvLine1.setVisibility(View.GONE);
                tvLine2.setVisibility(View.VISIBLE);
                tvLine3.setVisibility(View.GONE);
                tvLine5.setVisibility(View.GONE);
                tvLine7.setVisibility(View.GONE);
                tvLine9.setVisibility(View.GONE);

                tvLine2.setBackgroundResource((int)tvLine2.getTag(R.string.click));
                tvLine2.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                currentLine = "2";
                currentView = tvLine2;
                break;
            }

            case "종로3가": {

                tvLine1.setVisibility(View.VISIBLE);
                tvLine2.setVisibility(View.GONE);
                tvLine3.setVisibility(View.VISIBLE);
                tvLine5.setVisibility(View.VISIBLE);
                tvLine7.setVisibility(View.GONE);
                tvLine9.setVisibility(View.GONE);

                tvLine1.setBackgroundResource((int)tvLine1.getTag(R.string.click));
                tvLine1.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                currentLine = "1";
                currentView = tvLine1;

                break;
            }

        }

    }

    View.OnClickListener lineClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            try{
                currentView.setBackgroundResource((int)currentView.getTag(R.string.none_click));
                currentView.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                currentView = (TextView)v;
                currentLine = ((TextView) v).getText().toString();
                currentView.setBackgroundResource((int)v.getTag(R.string.click));
                currentView.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            }catch (Exception e)
            {

            }


        }
    };


    View.OnClickListener dialogClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getId()!=R.id.cl_dialog_main && v.getId()!=R.id.cl_line_list) dialog.dismiss();

        }
    };

    View.OnClickListener startGoalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(v.getId()== R.id.tv_start)
            {
                StationData.startLine = currentLine;
                StationData.startStation = targetStation;

            }else if(v.getId()==R.id.tv_goal){

                StationData.goalLine = currentLine;
                StationData.goalStation = targetStation;
            }
            currentView = null;
            dialog.dismiss();
        }
    };
}
