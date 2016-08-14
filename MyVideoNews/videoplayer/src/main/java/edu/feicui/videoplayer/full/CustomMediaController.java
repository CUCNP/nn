package edu.feicui.videoplayer.full;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import edu.feicui.videoplayer.R;
import io.vov.vitamio.widget.MediaController;

/**
 * Created by DELL on 2016/8/11.
 */
public class CustomMediaController extends MediaController{

    private MediaPlayerControl mediaPlayerControl;
    private final AudioManager audioManager;
    private Window window;
//    最大音量
    private final int maxVolume;
//    当前音量(在开始滑动手势时的音量)
    private int currentVolume;
//    当前亮度(在开始滑动手势时的亮度(0.0f - 1.0f,如果是负自动调整))
    private float currentBrightness;

    public CustomMediaController(Context context) {
        super(context);
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        window = ((Activity) context).getWindow();
//        设置默认亮度
//        WindowManager.LayoutParams layoutParams = window.getAttributes();
//        layoutParams.screenBrightness = 0.5f;
//        window.setAttributes(layoutParams);
//        设置默认音量
//        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume/2,AudioManager.FLAG_SHOW_UI);
    }
//    重写makeControllerView（继承自Vitamio MediaController）,自定义MediaController视图
    @Override
    protected View makeControllerView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_custom_video_controller,this);
        initView();
        return view;
    }
//    父类的MediaPlayerControl是私有的,重写这个方法，将player保存一份，方便使用
    @Override
    public void setMediaPlayer(MediaPlayerControl player) {
        super.setMediaPlayer(player);
        this.mediaPlayerControl = player;
    }

    private void initView() {
//        设置快进forward
        ImageButton btnFastForward = (ImageButton) findViewById(R.id.btnFastForward);
        btnFastForward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                获取当前进度
                long position = mediaPlayerControl.getCurrentPosition();
//                设置快进10秒
                if ((mediaPlayerControl.getDuration() - mediaPlayerControl.getCurrentPosition()) < 10000) {
                    position = mediaPlayerControl.getDuration();
                    mediaPlayerControl.seekTo(position);
                }else {
                    position += 10000;
                    mediaPlayerControl.seekTo(position);
                }
            }
        });
//        设置快退rewind
        ImageButton btnFastRewind = (ImageButton) findViewById(R.id.btnFastRewind);
        btnFastRewind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                获取当前进度
                long position = mediaPlayerControl.getCurrentPosition();
//                设置快退10秒
                position -= 10000;
                mediaPlayerControl.seekTo(position);
            }
        });

        final View adjustView = findViewById(R.id.adjustView);
//        手势处理（采用系统提供的）
        final GestureDetector gestureDetector = new GestureDetector(getContext(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onScroll(
                            MotionEvent e1,
                            MotionEvent e2,
                            float distanceX,
                            float distanceY) {
//                        设置scroll起始位置
                        float startX = e1.getX();
                        float startY = e1.getY();
//                        设置scroll结束位置
                        float endX = e2.getX();
                        float endY = e2.getY();
//                        设置滑动范围宽高
                        float width = adjustView.getWidth();
                        float height = adjustView.getHeight();
//                        设置滑动比例
                        float percentage = (startY - endY) / height;
//                        判断滑动发生于屏幕左侧，调整亮度
                        if (startX < width/2){
                            adjustBrightness(percentage);
                            return true;
//                        判断滑动发生于屏幕右侧，调整音量
                        }else if (startX > width/2){
                            adjustVolume(percentage);
                            return true;
                        }
                        return false;
                    }
                });

        adjustView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
//                按下事件时(马上将开始手势处理)获取当前音量及亮度，使用ACTION_MASK过滤掉多点触屏事件
                if ((motionEvent.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
                    currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    currentBrightness = window.getAttributes().screenBrightness;
                }
                gestureDetector.onTouchEvent(motionEvent);
                CustomMediaController.this.show();
                return true;
            }
        });
    }

    private void adjustBrightness(float percentage) {
//        计算亮度
        float targetBrightness = percentage + currentBrightness;
        targetBrightness = targetBrightness > 1.0f ? 1.0f : targetBrightness;
        targetBrightness = targetBrightness < 0.01f ? 0.01f : targetBrightness;
//        设置亮度
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.screenBrightness = targetBrightness;
        window.setAttributes(layoutParams);
    }

    private void adjustVolume(float percentage) {
//        计算音量
        int targetVolume = (int) (percentage * maxVolume) + currentVolume;
        targetVolume = targetVolume > maxVolume ? maxVolume : targetVolume;
        targetVolume = targetVolume < 0 ? 0 : targetVolume;
//        设置音量
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, targetVolume, AudioManager.FLAG_SHOW_UI);
    }
}
