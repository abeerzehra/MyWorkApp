package com.example.android.miwok;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        final ArrayList<Word> list = new ArrayList<Word>();
        list.add(new Word("Where are you going?","mintowuksus",R.raw.phrase_where_are_you_going));
        list.add(new Word("What is your name?","tinnәoyaase'nә",R.raw.phrase_what_is_your_name));
        list.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        list.add(new Word("How are you feeling?","kuchiachit",R.raw.phrase_how_are_you_feeling));
        list.add(new Word("I'm feeling good.","әәnәs'aa?",R.raw.phrase_im_feeling_good));
        list.add(new Word("Are you coming?","hәә’әәnәm",R.raw.phrase_are_you_coming));
        list.add(new Word("Yes, I'm coming","әәnәm",R.raw.phrase_yes_im_coming));
        list.add(new Word("Let's go.","yoowutis",R.raw.phrase_lets_go));
        list.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

        WordAdapter listAdapter = new WordAdapter(this,list,R.color.category_phrases);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(listAdapter);

//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.text);

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
                mediaPlayer=MediaPlayer.create(PhrasesActivity.this,word.getAudioResourseId());
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

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener()
            {
                public void onAudioFocusChange(int focusChange)
                {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT)
                    {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        onPause();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
                    {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        onStop();
                    }
                    else if (focusChange ==
                            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
                    {
                        // Lower the volume, because something else is also
                        // playing audio over you.
                        // i.e. for notifications or navigation directions
                        // Depending on your audio playback, you may prefer to
                        // pause playback here instead. You do you.
                        onPause();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
                    {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        onResume();
                    }
                }
            };

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}