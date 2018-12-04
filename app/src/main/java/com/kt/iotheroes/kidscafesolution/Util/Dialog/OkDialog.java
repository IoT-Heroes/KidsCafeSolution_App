package com.kt.iotheroes.kidscafesolution.Util.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kt.iotheroes.kidscafesolution.R;

/**
 * Created by mijeong on 2018. 12. 3..
 * cycle : constructor -> onCreate(show)
 */

public class OkDialog extends Dialog {
    private TextView textView;
    private Button button;
    private String message;
    View.OnClickListener okListener;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setOkListener(View.OnClickListener listener) {
        this.okListener = listener;
    }

    public OkDialog(@NonNull Context context) {
        super(context);
        okListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_ok);

        initView();
    }

    private void initView() {
        textView = (TextView)findViewById(R.id.text_message);
        textView.setText(message);

        button = (Button)findViewById(R.id.button_ok);
        button.setOnClickListener(okListener);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });
    }
}
