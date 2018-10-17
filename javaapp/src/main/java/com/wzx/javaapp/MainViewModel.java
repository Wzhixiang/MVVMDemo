package com.wzx.javaapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import java.util.Date;

/**
 * 描述：
 * <p>
 * 创建人： Administrator
 * 创建时间： 2018/10/17
 * 更新时间：
 * 更新内容：
 */

public class MainViewModel extends AndroidViewModel {

    //ObservableField实现双向绑定
    public ObservableField<String> name = new ObservableField("");

    private MutableLiveData<User> mUsers = null;

    public LinearLayoutManager layoutManager;

    public UserAdapter adapter;

    public MainViewModel(@NonNull Application application) {
        super(application);
        layoutManager = new LinearLayoutManager(application);
        adapter = new UserAdapter();
    }

    public void subscribe(@NonNull LifecycleOwner owner) {
        if (mUsers == null) {
            log("init mUsers");
            mUsers = new MutableLiveData();

            //观察mUsers变化
            mUsers.observe(owner, new Observer<User>() {

                @Override
                public void onChanged(@Nullable User user) {
                    name.set("");
                    adapter.addData(user);
                }
            });
        }
    }

    public void addUser(View view) {
        if(TextUtils.isEmpty(name.get())){
            showSnackBar(view, "name is null");
            return;
        }

        showSnackBar(view, "add ${name.get()} to list");
        User user = new User();
        user.setName(name.get());
        user.setCreateTime(new Date());
        //通知变化
        mUsers.setValue(user);
    }

    private void showSnackBar(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    private void log(String msg) {
        Log.i(this.getClass().getSimpleName(), msg);
    }
}
