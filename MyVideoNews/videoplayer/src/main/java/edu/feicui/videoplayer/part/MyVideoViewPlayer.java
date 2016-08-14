package edu.feicui.videoplayer.part;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import edu.feicui.videoplayer.R;
import edu.feicui.videoplayer.full.VideoViewActivity;
import io.vov.vitamio.Vitamio;

/**
 * Created by DELL on 2016/8/12.
 */
public class MyVideoViewPlayer extends FrameLayout {
    public MyVideoViewPlayer(Context context) {
        this(context,null);
    }

    public MyVideoViewPlayer(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyVideoViewPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private String videoPath;

    private void init() {
//        初始化Vitamio
        Vitamio.isInitialized(getContext());
        LayoutInflater.from(getContext()).inflate(R.layout.view_simple_video_player, this);
        initView();
    }

    private ImageView ivPreview;
    private ImageButton btnToggle,btnFullScreen;
    private ProgressBar progressBar;

//    对当前自定义视图初始化
    private void initView() {
        initSurfaceView();
        initControllerViews();
    }

    //    设置播放路径（必须在onResume()方法前调用）
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
//    建立onResume()方法，初始化MediaPlayer，准备MediaPlayer（与Activity的onResume同步执行）
    public void onResume() {
        initMediaPlayer();
        prepareMediaPlayer();
    }

    //    建立onPause()方法，暂停MediaPlayer，释放MediaPlayer（与Activity的onPause同步执行）
    public void onPause() {
        pauseMediaPlayer();
        releaseMediaPlayer();
    }
//    初始化SurfaceView
    private void initSurfaceView() {
    }
//    初始化视图控制
    private void initControllerViews() {
        ivPreview = (ImageView) findViewById(R.id.ivPreview);
//        播放/暂停点击事件
        btnToggle = (ImageButton) findViewById(R.id.btnToggle);
        btnToggle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        进度条
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        全屏点击事件
        btnFullScreen = (ImageButton) findViewById(R.id.btnFullScreen);
        btnFullScreen.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                VideoViewActivity.open(getContext(),getVideoViewPath());
            }
        });
    }
    //    初始化MediaPlayer，设置监听
    private void initMediaPlayer() {
    }
//    开始MediaPlayer，同时更新UI
    private void startMediaPlayer() {

    }
//    准备MediaPlayer，同时更新UI
    private void prepareMediaPlayer() {
    }
//    暂停MediaPlayer，同时更新UI
    private void pauseMediaPlayer() {
    }
//    释放MediaPlayer，同时更新UI
    private void releaseMediaPlayer() {
    }

    private String getVideoViewPath() {
        return "http://o9ve1mre2.bkt.clouddn.com/raw_%E6%B8%A9%E7%BD%91%E7%94%B7%E5%8D%95%E5%86%B3%E8%B5%9B.mp4";
    }
}
