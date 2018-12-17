package com.kt.iotheroes.kidscafesolution.Util.GCM;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.kt.iotheroes.kidscafesolution.R;

/**
 * Created by lmjin_000 on 2016-03-24.
 */
public class ShowMsgActivity extends Activity {
    private TextView textView, titleView;
    private Button button;
    private String message, title;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");

        //투명한 액티비티 띄우는 용도
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        //다이얼로그 띄우기
//        final PushDialog dialog = new PushDialog(this);
//        dialog.setMessage(title, message);
//        dialog.show();


        final Dialog dialog = new Dialog(ShowMsgActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_push);

        textView = (TextView)dialog.findViewById(R.id.text_message);
        textView.setText(message);

        titleView = (TextView)dialog.findViewById(R.id.text_title);
        titleView.setText(title);

        button = (Button)dialog.findViewById(R.id.button_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }
}
