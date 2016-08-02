package com.engin.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.engin.utils.LogUtil;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/07/28 下午5:48
 */

public class LoaclService extends Service{
    private static final String TAG = "LoaclService ";

    private IBinder mBinder = new LocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public class LocalBinder extends Binder{

        public LoaclService getService(){
            return LoaclService.this;
        }

    }

    public void sayHello(){
        Toast.makeText(this, "Hello world!!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG+"  - onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        LogUtil.d(TAG+"  - onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.d(TAG+"   - onUnbind");
       // return super.onUnbind(intent);
        return true;
    }
}
