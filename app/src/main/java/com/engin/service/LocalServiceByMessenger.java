package com.engin.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.engin.utils.LogUtil;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/07/28 下午6:10
 */

public class LocalServiceByMessenger extends Service {

    class LocalService extends Handler{
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(LocalServiceByMessenger.this, "LL", Toast.LENGTH_SHORT).show();
            LogUtil.d("TTTT s "+Thread.currentThread().getName());
        }
    }

    Messenger messenger = new Messenger(new LocalService());

    @Override
    public void onCreate() {
        LogUtil.d("LocalServiceByMessenger onCreate");
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
