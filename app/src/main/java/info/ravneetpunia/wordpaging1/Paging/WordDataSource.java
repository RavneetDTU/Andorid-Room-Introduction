package info.ravneetpunia.wordpaging1.Paging;

import android.arch.paging.ItemKeyedDataSource;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import info.ravneetpunia.wordpaging1.Database.Word;
import info.ravneetpunia.wordpaging1.Database.WordDao;
import info.ravneetpunia.wordpaging1.Database.WordDatabase;
import info.ravneetpunia.wordpaging1.Repository.WordRepository;

public class WordDataSource extends ItemKeyedDataSource<Integer, Word> {

    private WordDao wordDao;

    public WordDataSource(Context context) {
        wordDao = WordDatabase.getDatabase(context).wordDao();
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Word> callback) {

        List<Word> words = wordDao.getWordBySize(0,params.requestedLoadSize);

        callback.onResult(words,0,words.size());

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Word> callback) {

        List<Word> wordList = wordDao.getWordBySize(params.key,params.requestedLoadSize);
        callback.onResult(wordList);

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Word> callback) {

    }

    @NonNull
    @Override
    public Integer getKey(@NonNull Word item) {
        return item.getId();
    }
}
