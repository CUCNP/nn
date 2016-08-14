package edu.feicui.myvideonews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.feicui.videoplayer.full.VideoViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
//        黄油刀绑定当前Activity
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLikes)
    public void VideoViewLikes() {
        Intent intent = new Intent(this, PartPlayActivity.class);
        startActivity(intent);
    }
//    点击监听
    @OnClick(R.id.btnLocal)
    public void VideoViewPlay() {
//        开启VideoViewActivity播放视频
        VideoViewActivity.open(this,getVideoViewPath());
    }
//    视频播放路径
    private String getVideoViewPath() {
        return "http://o9ve1mre2.bkt.clouddn.com/raw_%E6%B8%A9%E7%BD%91%E7%94%B7%E5%8D%95%E5%86%B3%E8%B5%9B.mp4";
    }

}
