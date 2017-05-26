package com.example.hy.customspulltoandmore;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hy.refreshlistview.RefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RefreshListView mListView = (RefreshListView) findViewById(R.id.refresh_listview);
        dataList = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            dataList.add("这是ListView的数据: " + i);
        }

        // 给ListView设置Adapter数据适配器
        final MyAdapter mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);

        // 设置一个当ListView刷新的监听
        mListView.setOnRefreshListener(new RefreshListView.OnRefreshListener() {

            @Override
            public void onPullDownRefresh() {
                Toast.makeText(MainActivity.this, "开始下拉刷新了", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 会在3秒钟后执行.
                        dataList.add(0, "我是下拉刷新出来的数据..");
                        mAdapter.notifyDataSetChanged();

                        // 把头布局隐藏掉
                        mListView.onRefreshFinish();
                    }
                }, 3000);
            }

            @Override
            public void onLoadingMore() {
                Toast.makeText(MainActivity.this, "开始加载更多了", 0).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataList.add("我是加载更多的数据1");
                        dataList.add("我是加载更多的数据2");
                        dataList.add("我是加载更多的数据3");
                        mListView.onRefreshFinish();
                    }
                }, 5000);
            }
        });
    }

    /**
     * @author andong
     * 数据适配器
     */
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MainActivity.this);
            tv.setText(dataList.get(position));
            tv.setTextSize(18);
            tv.setTextColor(Color.BLACK);
            tv.setPadding(0, 5, 0, 5);
            return tv;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

    }
}
