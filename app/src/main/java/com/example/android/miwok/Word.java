package com.example.android.miwok;

import android.media.Image;
import android.widget.ImageView;

public class Word
{
    private String mEnglish;
    private String mMiwok;
    private int mImage;
    private int mAudio;


    public Word(String English, String Miwok,int Audio)
    {
        mEnglish=English;
        mMiwok=Miwok;
        mAudio=Audio;
    }


    public Word(String English, String Miwok, int Image,int audio)
    {
        mEnglish=English;
        mMiwok=Miwok;
        mImage=Image;
        mAudio=audio;
    }

    public String getDefaultTranslation()
    {
        return mEnglish;
    }

    public String getMiwokTranslation()
    {
        return mMiwok;
    }

    public int getImageResourceId()
    {

        return mImage;
    }

    public int getAudioResourseId()
    {
        return mAudio;
    }

}