package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity
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
        list.add(new Word("One","Lutti",R.drawable.number_one,R.raw.number_one));
        list.add(new Word("Two","Otiko",R.drawable.number_two,R.raw.number_two));
        list.add(new Word("Three","Tolookosu",R.drawable.number_three,R.raw.number_three));
        list.add(new Word("Four","Oyyisa",R.drawable.number_four,R.raw.number_four));
        list.add(new Word("Five","Massokka",R.drawable.number_five,R.raw.number_five));
        list.add(new Word("Six","Temmokka",R.drawable.number_six,R.raw.number_six));
        list.add(new Word("Seven","Kenekaku",R.drawable.number_seven,R.raw.number_seven));
        list.add(new Word("Eight","Kawinta",R.drawable.number_eight,R.raw.number_eight));
        list.add(new Word("Nine","Wo'e",R.drawable.number_nine,R.raw.number_nine));
        list.add(new Word("Ten","Na'aacha",R.drawable.number_ten,R.raw.number_ten));

        WordAdapter listAdapter = new WordAdapter(this,list,R.color.category_numbers);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                // TODO Auto-generated method stub
                //Toast.makeText(MainActivity.this, listView[position], Toast.LENGTH_SHORT).show();

                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.text);
                Word word = list.get(position);

                releaseMediaPlayer();
                mediaPlayer=MediaPlayer.create(NumbersActivity.this,word.getAudioResourseId());
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