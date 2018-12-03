package com.kt.iotheroes.kidscafesolution.Account.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.BottomTabActivity;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.OkDialog;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.LoginView {

    private EditText editId, editPw;
    private Button btnLogin, btnJoin;

    private LoginPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        presenter = new LoginPresenterImpl(this);
        initView();
    }

    private void initView() {
        editId = (EditText) findViewById(R.id.edit_id);
        editPw = (EditText) findViewById(R.id.edit_pw);
        btnLogin = (Button) findViewById(R.id.button_login);
        btnJoin = (Button) findViewById(R.id.button_join);

        btnLogin.setOnClickListener(this);
        btnJoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login :
                String id = editId.getText().toString();
                String pw = editPw.getText().toString();

                if (id.isEmpty()) presentDialog("id를 입력해주세요.");
                else if (pw.isEmpty()) presentDialog("pw를 입력해주세요.");
                else presenter.onLoginBtnSelected(editId.getText().toString(), editPw.getText().toString());

                break;
            case R.id.button_join :

                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    // TODO : 실패 다이얼로그 만들기
    public void presentDialog(String message) {
        OkDialog okDialog = new OkDialog(this, message);
        okDialog.show();


//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setMessage(message);
//        builder.setNeutralButton("닫기", null);
//        builder.create().show();

//        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void goToBottomTabActivity(User user) {
        Intent intent = new Intent(LoginActivity.this, BottomTabActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}
