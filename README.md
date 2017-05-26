# customsPullToAndMore
自带下拉刷新和上拉加载更多控件
## 使用步骤
  Step 1. Add the JitPack repository to your build file;
  Add it in your root build.gradle at the end of repositories:
  
  ##
  
     allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }
  Step 2. Add the dependency
  
  ##
  
     dependencies {
            compile 'com.github.Simonhy:customsPullToAndMore:v1.0.0'
    }
  
  ## 说明
     该方法只针对listview进行了封装,对系统的listView进行加头和加尾的视图
     使用方式和普通的listview一样,只是在设置监听的时候会有刷新的操作
      设置一个当ListView刷新的监听
      ###
      
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
  ##  欢迎各位的指正; 
     也可以下载下来运行demo
  ##  备注
    如果开发是在eclipse上的话,只能将代码拷贝过去,暂不支持jar包形式.
