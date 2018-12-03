package com.kt.iotheroes.kidscafesolution.Account.Join;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.BottomTabActivity;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.OkDialog;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener, JoinContract.JoinView, TextWatcher {

    EditText editId, editPw, editPwCheck, editPhone;
    ImageView imgCheck;
    Button btnJoin;

    JoinContract.JoinPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        presenter = new JoinPresenterlmpl(this);
        initView();
    }

    private void initView() {
        editId = (EditText)findViewById(R.id.edit_id);
        editPw = (EditText)findViewById(R.id.edit_pw);
        editPwCheck = (EditText)findViewById(R.id.edit_check_pw);
        editPhone = (EditText)findViewById(R.id.edit_phone);
        imgCheck = (ImageView)findViewById(R.id.image_check);
        btnJoin = (Button)findViewById(R.id.button_join);

        btnJoin.setOnClickListener(this);
        editPwCheck.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }

    private void checkImage(boolean check) {
        if (check) imgCheck.setColorFilter(R.color.colorPrimary);
        else imgCheck.setColorFilter(Color.BLACK);
    }

    @Override
    public void presentDialog(String message) {
        OkDialog okDialog = new OkDialog(this, message);
        okDialog.show();
    }

    @Override
    public void goToBottomTabActivity(User user) {
        Intent intent = new Intent(JoinActivity.this, BottomTabActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        close();
    }

    @Override
    public void close() {
        close();
    }

    @Override
    public void onClick(View view) {
        String id = editId.getText().toString();
        String pw = editPw.getText().toString();
        String phone = editPhone.getText().toString();

        if (id.isEmpty()) presentDialog("id를 입력해주세요.");
        else if (phone.isEmpty()) presentDialog("전화번호를 입력해주세요.");
        else if (!presenter.pwCheck() || pw.isEmpty()) presentDialog("pw가 일치하지 않습니다.");
        else presenter.onJoinBtnSelected(id, pw, phone);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
        boolean same = charSequence.equals(editPw.getText());

        if (same) checkImage(true);
        else checkImage(false);

        presenter.setCheck(same);
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
