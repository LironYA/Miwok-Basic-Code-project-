package com.example.android.miwok;

import android.media.MediaPlayer;

public class Word {
    /** Default translation for the word */

    private String mDefaultTranslation;

            /** Miwok translation for the word */
            private String mMiwokTranslation;
            private int mImageResourceId = NO_IMAGE;
            private static final int NO_IMAGE = -1;
            private int mColorResourceId;
            private int mAudioResource;

            /**
      * Create a new Word object.
      *
      * @param defaultTranslation is the word in a language that the user is already familiar with
      *                           (such as English)
    * @param miwokTranslation is the word in the Miwok language
     */
            public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioResource) {
                mDefaultTranslation = defaultTranslation;
                mMiwokTranslation = miwokTranslation;
                mImageResourceId = imageResourceId;
                mAudioResource = audioResource;
           }

    public Word(String defaultTranslation, String miwokTranslation, int audioResource) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResource = audioResource;
    }
            /**
     * Get the default translation of the word.
     */
            public String getDefaultTranslation() {
                return mDefaultTranslation;
            }

            /**
      * Get the Miwok translation of the word.
      */
            public String getMiwokTranslation() {
               return mMiwokTranslation;
            }
            public int getImageResourceId() {
        return mImageResourceId;
    }
            public boolean hadImage() {
            return mImageResourceId != NO_IMAGE;}
            public int getmAudioResource(){ return mAudioResource;}

}

