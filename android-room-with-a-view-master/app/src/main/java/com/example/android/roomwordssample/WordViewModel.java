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
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.roomwordssample.Paging.DataSourceFactory;

import java.util.List;

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

// TODO(6) : Config ViewModel

public class WordViewModel extends ViewModel {

    public static final String TAG = "false";

    private WordRepository mRepository;

    // This will load controlled data from DataSource
    public LiveData<PagedList<Word>> mAllWords;

    public WordViewModel(Application application){

        // Instantiate DataSourceFactory
        DataSourceFactory factory = new DataSourceFactory(application);

        // Create PagedList Config.
        PagedList.Config config = (new PagedList.Config.Builder()).setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPageSize(25)
                .build();

        // Creating LiveData Object using LivePagedListBuilder which takes....DataSourceFactory and Page Config. as parameters
        mAllWords = new LivePagedListBuilder<>(factory,config).build();
        Log.d(TAG, "WordViewModel: "+mAllWords);
    }

    // Factory for Creating view Model as we need to pass Application to viewModel Object

    public static class ViewModelFactory extends ViewModelProvider.NewInstanceFactory{
        private Application mApplication;
        public ViewModelFactory(Application application){
            mApplication = application;
        }


        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new WordViewModel(mApplication);
        }
    }


    public void insert(Word word) { mRepository.insert(word); }
}