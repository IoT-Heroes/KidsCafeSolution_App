package com.kt.iotheroes.kidscafesolution.Account.Join;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.OkDialog;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener, JoinContract.JoinView, TextWatcher {

    private EditText editId, editPw, editPwCheck, editPhone, editName;
    private ImageView imgCheck;
    private Button btnJoin;

    private JoinContract.JoinPresenter presenter;

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
        editName = (EditText)findViewById(R.id.edit_name);
        imgCheck = (ImageView)findViewById(R.id.image_check);
        btnJoin = (Button)findViewById(R.id.button_join);

        btnJoin.setOnClickListener(this);
        editPwCheck.addTextChangedListener(this);
        editPw.addTextChangedListener(this);
    }

    @Override
    public void imageCheck(boolean check) {
        if (check) imgCheck.setColorFilter(Color.parseColor("#2fc299"));
        else imgCheck.setColorFilter(Color.parseColor("#000000"));
    }

    @Override
    public void close() {
        close();
    }

    @Override
    public void actionSuccess(final User user) {
        final OkDialog okDialog = new OkDialog(this);
        okDialog.setMessage("회원가입에 성공했습니다!\n로그인 해주세요.");
        okDialog.setOkListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JoinActivity.this.close();
                okDialog.dismiss();
            }
        });
        okDialog.show();
    }

    @Override
    public void onClick(View view) {
        String id = editId.getText().toString();
        String pw = editPw.getText().toString();
        String phone = editPhone.getText().toString();
        String name = editName.getText().toString();

        if (id.isEmpty()) presentDialog("id를 입력해주세요.");
        else if (phone.isEmpty()) presentDialog("전화번호를 입력해주세요.");
        else if (name.isEmpty()) presentDialog("이름을 입력해주세요.");
        else if (!presenter.isCheck() || pw.isEmpty()) presentDialog("pw가 일치하지 않습니다.");
        else presenter.onJoinBtnSelected(id, pw, phone, name);
    }

    @Override
    public void presentDialog(String s) {
        OkDialog okDialog = new OkDialog(this);
        okDialog.setMessage(s);
        okDialog.show();
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
        String pw1 = editPw.getText().toString().trim();
        String pw2 = editPwCheck.getText().toString().trim();
        presenter.pwCheck(pw1, pw2);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
