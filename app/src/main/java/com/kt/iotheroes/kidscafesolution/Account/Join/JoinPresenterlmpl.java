package com.kt.iotheroes.kidscafesolution.Account.Join;

import android.util.Log;

import com.kt.iotheroes.kidscafesolution.Model.User;
import com.kt.iotheroes.kidscafesolution.Util.Connections.APIClient;
import com.kt.iotheroes.kidscafesolution.Util.Connections.Response;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mijeong on 2018. 12. 3..
 */

public class JoinPresenterlmpl implements JoinContract.JoinPresenter {
    private JoinContract.JoinView view;
    private User user;
    private boolean checked;
    private CompositeDisposable disposable;

    public JoinPresenterlmpl(JoinContract.JoinView view) {
        this.view = view;
        user = null;
        checked = false;
        disposable = new CompositeDisposable();
    }

    @Override
    public void onJoinBtnSelected(String id, String pw, String phone, String name) {
        user = new User(id, pw, phone, name);

        APIClient.getClient().join(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // disposable 인터페이스를 활용한 명시적 자원해제
                // 구독자가 아래 통신 코드 내부에서 사용하는 자원을 참조하기 때문에 액티비티가 비정상 종료되어도 가비지 컬렉터의 대상이 되지 못해 메모리 누수 발생
                .subscribeWith(new DisposableObserver<Response<User>>() {
                    // 항목이 만들어질 때 마다 받는다.
                    // this can be called multiple times
                    @Override
                    public void onNext(@NonNull Response<User> userResponse) {
                        // TODO : result 결과 확인하는거 나중에 찜찜하면 response.data == null로 확인하기
                        if (userResponse.getResult().equals("success"))
                            user = userResponse.getData();
                        else
                            Log.i("connect", "join에 문제가 발생하였습니다.");

                    }

                    // 에러 발생
                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Log.e("connect", e.getMessage());
                        view.presentDialog("회원가입에 실패하셨어요.");
                    }

                    // 옵저버블이 옵저버에게 전달할 데이터가 없을 때 모든 작업을 마쳤을 때 알리는 기능
                    @Override
                    public void onComplete() {
                        view.actionSuccess(user);
                    }
                });
    }

    @Override
    public boolean isCheck() {
        return checked;
    }

    @Override
    public void pwCheck(String pw1, String pw2) {
        checked = pw1.equals(pw2);
        view.imageCheck(checked);
        Log.i("pw", "result : " + checked);
    }
}
