package edu.feicui.videoplayer.full;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import edu.feicui.videoplayer.R;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {

    private static final String VIDEO_PATH_KEY = "VIDEO_PATH_KEY";
//    开启当前Activity，传入视频播放地址
    public static void open(Context context,String videoPath) {
        Intent intent = new Intent(context,VideoViewActivity.class);
        intent.putExtra(VIDEO_PATH_KEY, videoPath);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        初始化Vitamio
        Vitamio.isInitialized(this);
//        取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        设置窗口背景颜色
        getWindow().setBackgroundDrawableResource(android.R.color.black);
        setContentView(R.layout.activity_video_view);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
//        初始化视图
        initBufferViews();
//        初始化VideoView
        initVideoView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        恢复时加载视频播放路径
        videoView.setVideoPath(getIntent().getStringExtra(VIDEO_PATH_KEY));
    }

    @Override
    protected void onPause() {
        super.onPause();
//        设置停止播放返回
        videoView.stopPlayback();
    }

    private VideoView videoView;
    private MediaPlayer mediaPlayer;
//    缓冲信息（图片显示）
    private ImageView ivLoading;
//    缓冲信息（文本显示）
    private TextView tvBufferInfo;
//    下载速率
    private int downLoadSpeed;
//    缓冲比例
    private int bufferPercent;

    private void initBufferViews() {
        ivLoading = (ImageView) findViewById(R.id.ivLoading);
        tvBufferInfo = (TextView) findViewById(R.id.tvBufferInfo);
//        设置缓冲信息初始隐藏
        ivLoading.setVisibility(View.INVISIBLE);
        tvBufferInfo.setVisibility(View.INVISIBLE);
    }

    private void initVideoView() {
        videoView = (VideoView) findViewById(R.id.videoView);
//        设置视频播放控制器（Vitamio自带：播放/暂停、进度条）
//        videoView.setMediaController(new MediaController(this));
//        设置视频播放控制器（自定义）
        videoView.setMediaController(new CustomMediaController(this));
//        设置屏幕常亮
        videoView.setKeepScreenOn(true);
//        设置焦点
        videoView.requestFocus();
//        设置视频播放地址
//        videoView.setVideoPath();
//        播放视频
        videoView.start();
//        设置资源准备监听
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer = mp;
//                设置缓冲区大小（缓冲区填充完后，才会开始播放），默认值为1M
                mediaPlayer.setBufferSize(512 * 1024);
            }
        });
//        设置缓冲更新监听
        videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
//                percent：缓冲的比例
                bufferPercent = percent;
                updateBufferViewInfo();
            }
        });
//        设置“状态”信息监听
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what){
//                    设置缓冲开始
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
//                        设置缓冲开始时视频正在播放，则暂停播放
                        if (videoView.isPlaying()) {
                            videoView.pause();
//                            显示缓冲信息
                            showBufferViewInfo();
                        }
                        break;
//                    缓冲时下载速率
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
//                        设置更新缓冲信息
                        updateBufferViewInfo();
                        break;
//                    设置缓冲结束
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
//                        隐藏缓冲信息
                        hideBufferViewInfo();
//                        播放视频
                        videoView.start();
                        break;
                }
                return false;
            }
        });
    }

    private void showBufferViewInfo() {
//        设置显示缓冲信息
        ivLoading.setVisibility(View.VISIBLE);
        tvBufferInfo.setVisibility(View.VISIBLE);
//        初始化下载速率和缓冲比例
        downLoadSpeed = 0;
        bufferPercent = 0;
    }

    private void updateBufferViewInfo() {
//        设置更新缓冲信息
        String info = String.format(Locale.CHINA, "%d%%,%dkb/s", bufferPercent, downLoadSpeed);
        tvBufferInfo.setText(info);
    }

    private void hideBufferViewInfo() {
//        设置隐藏缓冲信息
        ivLoading.setVisibility(View.INVISIBLE);
        tvBufferInfo.setVisibility(View.INVISIBLE);
    }
}
