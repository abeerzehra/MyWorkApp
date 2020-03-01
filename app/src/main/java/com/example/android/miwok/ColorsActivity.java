package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity
{
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener()
    {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer)
        {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        final ArrayList<Word> list = new ArrayList<Word>();
        list.add(new Word("Red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
        list.add(new Word("Green","Chokokki",R.drawable.color_green,R.raw.color_green));
        list.add(new Word("Brown","Takaakki",R.drawable.color_brown,R.raw.color_brown));
        list.add(new Word("Gray","Topoppi",R.drawable.color_gray,R.raw.color_gray));
        list.add(new Word("Black","Kululli",R.drawable.color_black,R.raw.color_black));
        list.add(new Word("White","kelelli",R.drawable.color_white,R.raw.color_white));
        list.add(new Word("Dust Yellow","ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        list.add(new Word("Mustard Yellow","chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        final WordAdapter listAdapter = new WordAdapter(this,list,R.color.category_colors);
        final ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id)
            {
                // TODO Auto-generated method stub
                //Toast.makeText(MainActivity.this, listView[position], Toast.LENGTH_SHORT).show();

                releaseMediaPlayer();

                Word word = list.get(position);

                mediaPlayer=MediaPlayer.create(ColorsActivity.this,word.getAudioResourseId());

                if(!mediaPlayer.isPlaying())
                {
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(mCompletionListener);

                }
                else
                {
                    mediaPlayer.pause();
                }
            }
        });

    }

        private void releaseMediaPlayer()
        {
            // If the media player is not null, then it may be currently playing a sound.
            if (mediaPlayer != null)
            {
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mediaPlayer.release();

                // Set the media player back to null. For our code, we've decided that
                // setting the media player to null is an easy way to tell that the media player
                // is not configured to play an audio file at the moment.
                mediaPlayer = null;
            }
        }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}