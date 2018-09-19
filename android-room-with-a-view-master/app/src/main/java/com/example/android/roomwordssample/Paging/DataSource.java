package com.example.android.roomwordssample.Paging;

import android.arch.paging.PageKeyedDataSource;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.roomwordssample.Word;
import com.example.android.roomwordssample.WordDao;
import com.example.android.roomwordssample.WordRepository;
import com.example.android.roomwordssample.WordRoomDatabase;

import java.util.List;

// TODO(3): Create DataSource Class

public class DataSource extends PageKeyedDataSource<Integer, Word> {

    public static final String TAG = "false";

    private WordDao wordDao;

    public DataSource(Context context){
        wordDao = WordRepository.getDatabase(context).wordDao();
    }

    // This Method is Called to load initial Data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, Word> callback) {

        Log.d(TAG, "loadInitial: "+params);

        List<Word> list = wordDao.getWordsBySize(0,params.requestedLoadSize);

        // Now we need to handle first request after db created or app is installed
        int noOfWords = 0;
        while (list.size() == 0){
            list = wordDao.getWordsBySize(0,params.requestedLoadSize);
            noOfWords++;
            if(noOfWords == 1){// Here I am limiting at 2 words only because at stating we are just insearting 2 words only ("Hello" &"World")
                break;
            }
            try{
                Thread.sleep(500);
            }catch (InterruptedException e){ }
        }

        callback.onResult(list,null,list.size()+1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Word> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Integer, Word> callback) {

        Log.d(TAG, "loadAfter: "+params);

        List<Word> wordList = wordDao.getWordsBySize(params.key,params.requestedLoadSize);
        int nextkey = params.key+wordList.size();
        callback.onResult(wordList,nextkey);

    }
}
