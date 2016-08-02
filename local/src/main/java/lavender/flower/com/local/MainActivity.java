package lavender.flower.com.local;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.engin.model.Book;
import com.engin.model.BookManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Messenger messenger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     * 通过Messenger实现进程间通信
     * @param view
     */
    public void messenger(View view){
        try {
            Intent s = new Intent();
            s.setAction("zhangsan.com.lisi");
            s.setPackage("com.example.ningqiangzhao.study");
            bindService(s, new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    messenger = new Messenger(iBinder);

                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {

                }
            },BIND_AUTO_CREATE);
            Message msg = Message.obtain(null,1);
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    BookManager bookManager;
    public void aidlAddBook(View view){
        bindService();
        if (bookManager != null){
            try {
                Book book = new Book();
                book.name = "人生百态";
                book.price = 19;
                bookManager.addBook(book);
                Log.d("","TVTV add one book");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    public void aidlGetBook(View view){
        bindService();
        if (bookManager != null){
            try {
                List<Book> book = bookManager.getBook();
                Log.d("","TVTV getBook books = "+book);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private void bindService(){
        Intent i = new Intent();
        i.setAction("com.lypeer.aidl.jack");
        i.setPackage("com.example.ningqiangzhao.study");
        bindService(i, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                bookManager = BookManager.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        },BIND_AUTO_CREATE);
    }
}
