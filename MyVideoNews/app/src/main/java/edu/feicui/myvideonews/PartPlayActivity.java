package edu.feicui.myvideonews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.feicui.videoplayer.part.MyVideoViewPlayer;

public class PartPlayActivity extends AppCompatActivity {

    @BindView(R.id.myVideoViewPlayer) MyVideoViewPlayer myVideoViewPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_play);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        myVideoViewPlayer.setVideoPath(getVideoViewPath());
    }

    @Override
    protected void onResume() {
        super.onResume();
        myVideoViewPlayer.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        myVideoViewPlayer.onPause();
    }

    private String getVideoViewPath() {
        return "http://o9ve1mre2.bkt.clouddn.com/raw_%E6%B8%A9%E7%BD%91%E7%94%B7%E5%8D%95%E5%86%B3%E8%B5%9B.mp4";
    }
}
