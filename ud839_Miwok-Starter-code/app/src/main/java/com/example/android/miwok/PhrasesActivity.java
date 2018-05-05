package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
        private MediaPlayer mediaPlayer;
        MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                releaseMediaPlayer();
            }
        };
    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mediaPlayer.pause();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK) {
                        mediaPlayer.pause();
                    }
                }};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.word_list);
            final ArrayList<Word> words = new ArrayList<Word>();
            words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
            words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
            words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
            words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
            words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
            words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
            words.add(new Word("Yes, I’m coming", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
            words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
            words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
            words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));
            WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

            ListView listView = (ListView) findViewById(R.id.list);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    releaseMediaPlayer();
                    Word word = words.get(position);
                    int result = mAudioManager.requestAudioFocus(afChangeListener,
                            AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                        mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmAudioResource());
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(mCompletionListener);
                    }

                }
            });
        }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer() {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
                mAudioManager.abandonAudioFocus(afChangeListener);
            }
        }
}