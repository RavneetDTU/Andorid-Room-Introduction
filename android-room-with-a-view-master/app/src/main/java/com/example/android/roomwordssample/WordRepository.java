package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */

// TODO(5): Change code of Repository

public class WordRepository {

    private WordDao mWordDao;
    private static WordRoomDatabase INSTANCE;
    private static final Object LOCK = new Object();

    public static synchronized WordRoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (LOCK){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,WordRoomDatabase.class,"word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    public WordRepository(){
        mWordDao = INSTANCE.wordDao();
    }

    /* You must call this on a non-UI thread or your app will crash.
     Like this, Room ensures that you're not doing any long running operations on the main
     thread, blocking the UI.
     */
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     *
     * If you want to populate the database only when the database is created for the 1st time,
     * override RoomDatabase.Callback()#onCreate
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }
}
