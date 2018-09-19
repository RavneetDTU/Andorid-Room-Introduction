package info.ravneetpunia.wordpaging1.Paging;

import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import info.ravneetpunia.wordpaging1.Database.Word;
import info.ravneetpunia.wordpaging1.Database.WordDao;
import info.ravneetpunia.wordpaging1.Database.WordDatabase;
import info.ravneetpunia.wordpaging1.Repository.WordRepository;

public class WordDataSourceLimit extends PageKeyedDataSource<Integer, Word> {

    private WordDao wordDao;

    public WordDataSourceLimit(Context ctx){
        wordDao = WordDatabase.getDatabase(ctx).wordDao();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, Word> callback) {

        List<Word> words = wordDao.getWordBySize(0,params.requestedLoadSize);

        // This will handle first request after DB is created or app is Installed

        int noOfTries = 0;
        while (words.size() == 0){
            words = wordDao.getWordBySize(0,params.requestedLoadSize);
            noOfTries++;
            if(noOfTries == 6){
                break;
            }try {
                Thread.sleep(500);
            }catch (InterruptedException e){}
        }

        callback.onResult(words,null,words.size()+1);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Word> callback) {

    }

    // It is Called to load pages of Data using key passed in Params
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Integer, Word> callback) {

        List<Word> wordList = wordDao.getWordBySize(params.key,params.requestedLoadSize);
        int nextKey =  params.key+wordList.size();
        callback.onResult(wordList,nextKey);

    }
}
