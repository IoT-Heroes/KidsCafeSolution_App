package com.kt.iotheroes.kidscafesolution.Account.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kt.iotheroes.kidscafesolution.Account.Join.JoinActivity;
import com.kt.iotheroes.kidscafesolution.R;
import com.kt.iotheroes.kidscafesolution.TabActivity.BottomTabActivity;
import com.kt.iotheroes.kidscafesolution.Util.Dialog.OkDialog;
import com.kt.iotheroes.kidscafesolution.Util.SharedManager.PrefManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.LoginView {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText editId, editPw;
    private Button btnLogin, btnJoin;

    private LoginPresenterImpl presenter;
    private PrefManager preferenceManager;

    public PrefManager getPreferenceManager() {
        return preferenceManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.i(getString(R.string.activity), TAG + "on Create");

        presenter = new LoginPresenterImpl(this);
        preferenceManager = PrefManager.getInstance();

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
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void presentDialog(String message) {
        final OkDialog okDialog = new OkDialog(this);
        okDialog.setMessage(message);
        okDialog.show();
    }

    @Override
    public void goToBottomTabActivity() {
        Intent intent = new Intent(LoginActivity.this, BottomTabActivity.class);
        startActivity(intent);
        finish();
    }
}
