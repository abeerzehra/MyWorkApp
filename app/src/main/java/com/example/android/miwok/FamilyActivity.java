package com.example.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity
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
        list.add(new Word("Father","әpә",R.drawable.family_father,R.raw.family_father));
        list.add(new Word("Mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        list.add(new Word("Son","angsi",R.drawable.family_son,R.raw.family_son));
        list.add(new Word("Daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        list.add(new Word("Older Brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        list.add(new Word("Younger Brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        list.add(new Word("Older Sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        list.add(new Word("Younger Sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        list.add(new Word("Grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        list.add(new Word("Grandfather","Paapa",R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter listAdapter = new WordAdapter(this,list,R.color.category_family);
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
                mediaPlayer=MediaPlayer.create(FamilyActivity.this,word.getAudioResourseId());
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
