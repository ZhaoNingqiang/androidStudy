package com.engin.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.engin.R;
import com.engin.service.LoaclService;
import com.engin.service.LocalServiceByMessenger;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/07/28 下午5:58
 */

public class BindServiceActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_bind_service;
    }

    @Override
    public void initView() {
        findViewById(R.id.iv1);
        findViewById(R.id.iv2);

    }

    @Override
    public void setListener() {

    }

    public void bindService(View view){
        Intent s = new Intent(this, LoaclService.class);
        bindService(s, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                LoaclService.LocalBinder binder = (LoaclService.LocalBinder) service;
                LoaclService service1 = binder.getService();
                service1.sayHello();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        },BIND_AUTO_CREATE);
    }

    public void bindServiceByMessenger(View view){
        Intent s = new Intent(this, LocalServiceByMessenger.class);
        bindService(s, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Messenger messenger = new Messenger(service);
                try {
                    messenger.send(Message.obtain(null,0,"发送一些文字"));
                    Log.d(",","TTTT 6  "+Thread.currentThread().getName());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        },BIND_AUTO_CREATE);
    }



}
