package com.kt.iotheroes.kidscafesolution.Account.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.BottomTabActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.LoginView {

    private EditText editId, editPw;
    private Button btnLogin, btnJoin;

    private LoginPresenterImpl presenter = new LoginPresenterImpl(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        editId = (EditText) findViewById(R.id.edit_id);
        editPw = (EditText) findViewById(R.id.edit_pw);
        btnLogin = (Button) findViewById(R.id.button_login);
        btnJoin = (Button) findViewById(R.id.button_join);

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Log.i("test", "id : " + id);
//            }
//        });
        btnLogin.setOnClickListener(this);
        btnJoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login :

                String id = editId.getText().toString();
                String pw = editPw.getText().toString();


                Log.i("test", "id : " + id);
                Log.i("test", "pw : " + pw);


//                presenter.onLoginBtnSelected(editId.getText().toString(), editPw.getText().toString());
            case R.id.button_join :

                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    // TODO : 실패 다이얼로그 만들기
    public void presentDialog(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void goToBottomTabActivity(User user) {
        Intent intent = new Intent(LoginActivity.this, BottomTabActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}
