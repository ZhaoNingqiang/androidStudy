package com.engin.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.engin.model.Book;
import com.engin.model.BookManager;
import com.engin.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/08/02 上午11:23
 */

public class AIDLService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }


    public final String TAG = this.getClass().getSimpleName();

    //包含Book对象的list
    private List<Book> mBooks = new ArrayList<>();
    BookManager.Stub stub = new BookManager.Stub() {
        @Override
        public List<Book> getBook() throws RemoteException {
            LogUtil.d("TVTV service getBook ");
            return mBooks;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            LogUtil.d("TVTV service mBooks.add(book) ");
            mBooks.add(book);
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };

}
