package com.example.mymusicdemo.helper;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class MediaPlayerHelper {

    private Context mContext;
    private String mPath;
    private MediaPlayer mMediaPlayer;
    private OnMediaPlayerHelperListener mOnMediaPlayerHelperListener;

    public void setOnMediaPlayerHelperListener(OnMediaPlayerHelperListener onMediaPlayerHelperListener) {
        mOnMediaPlayerHelperListener = onMediaPlayerHelperListener;
    }

    private static MediaPlayerHelper instance;

    public static MediaPlayerHelper getInstance(Context context){

        if(instance == null){
            synchronized (MediaPlayerHelper.class){
                if (instance == null){
                    instance = new MediaPlayerHelper(context);
                }
            }
        }
        return instance;
    }

    private MediaPlayerHelper(Context context){
        mContext = context;
        mMediaPlayer = new MediaPlayer();
    }

    /**
     * 1、setPath：当前需要播放的音乐
     * 2、start：播放音乐
     * 3、pause：暂停播放
     */


    /**
     * 当前需要播放的音乐
     * @param path
     */
    public void setPath(String path){
        /**
         * 1、音乐正在播放，重置音乐播放状态
         * 2、设置播放音乐路径
         * 3、准备播放
         */

        mPath = path;
//        1、音乐正在播放，重置音乐播放状态
        if(mMediaPlayer.isPlaying()){
            mMediaPlayer.reset();
        }

//        2、设置播放音乐路径
        try {
            mMediaPlayer.setDataSource(mContext, Uri.parse(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

//      3、准备播放
        mMediaPlayer.prepareAsync();
        mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mOnMediaPlayerHelperListener != null){
                    mOnMediaPlayerHelperListener.onPrepared(mp);
                }
            }
        });
    }

    /**
     * 返回正在播放的音乐路径
     * @return
     */
    public String getPath(){
        return mPath;
    }

    /**
     * 播放音乐
     */
    public void start(){
        if(mMediaPlayer.isPlaying()) return;
        mMediaPlayer.start();
    }

    /**
     * 暂停播放
     */
    public void pause(){
        mMediaPlayer.pause();
    }


    public interface OnMediaPlayerHelperListener{
        void onPrepared(MediaPlayer mp);
    }
}
