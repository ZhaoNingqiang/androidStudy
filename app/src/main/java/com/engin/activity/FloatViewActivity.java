package com.engin.activity;

import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.engin.R;
import com.engin.cache.ImageCacheLoader;
import com.engin.widget.FloatCircleImageView;
import com.engin.widget.RecycleViewPager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @description: author:zhaoningqiang
 * @time 16/6/28/上午11:31
 */
public class FloatViewActivity extends BaseActivity {
    private FloatCircleImageView heard;
    private TextView name;
    private RecycleViewPager rvp;
    private LinearLayoutManager lm;
    private HashMap<String,Bitmap> mHeaders = new HashMap<String,Bitmap>();
    private ArrayList<Person> persons = new ArrayList<Person>();

    @Override
    public int getLayout() {
        return R.layout.activity_float_view;
    }

    @Override
    public void initView() {
        initData();
        heard = findview(R.id.heard);
        name = findview(R.id.name);
        rvp = findview(R.id.rvp);

        lm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvp.setLayoutManager(lm);

        rvp.setAdapter(new FloatAdapter(persons));
    }

    @Override
    public void setListener() {

    }

    private void initData(){
        persons.add(new Person("00000", "http://pic38.nipic.com/20140224/8472040_101914378000_2.jpg"));
        persons.add(new Person("11111", "http://img4.imgtn.bdimg.com/it/u=172112303,3232882607&fm=21&gp=0.jpg"));
        persons.add(new Person("22222", "http://img3.imgtn.bdimg.com/it/u=1876649012,2419765871&fm=21&gp=0.jpg"));
        persons.add(new Person("33333", "http://img4.imgtn.bdimg.com/it/u=3053650863,2902423685&fm=21&gp=0.jpg"));
        persons.add(new Person("44444", "http://www.guimobile.net/blog/uploads/2012/07/107141057.png"));
    }


    /****Adapter***/

    class FloatAdapter extends RecyclerView.Adapter<FloatAdapter.Holder>{
        ArrayList<Person> persons;

        public FloatAdapter(ArrayList<Person> persons) {
            this.persons = persons;
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(View.inflate(FloatViewActivity.this, R.layout.activity_float_view, null));
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            Person person = persons.get(position);
            ImageCacheLoader.loadUpperLayerBitmap(person.hearder,false,holder.fiv_heard,mHeaders);
            holder.tv_name.setText(person.name);
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }

        class Holder extends RecyclerView.ViewHolder{
            FloatCircleImageView fiv_heard;
            TextView tv_name;
            public Holder(View itemView) {
                super(itemView);
                fiv_heard = (FloatCircleImageView) itemView.findViewById(R.id.fiv_heard);
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            }
        }
    }

    static class Person{
        public Person(String name, String hearder) {
            this.name = name;
            this.hearder = hearder;
        }

        public String name;
        public String hearder;
    }

}
