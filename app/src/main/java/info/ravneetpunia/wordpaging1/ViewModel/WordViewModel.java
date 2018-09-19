package info.ravneetpunia.wordpaging1.ViewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import info.ravneetpunia.wordpaging1.Database.Word;
import info.ravneetpunia.wordpaging1.Paging.WordDataSourceFactory;
import info.ravneetpunia.wordpaging1.Repository.WordRepository;

public class WordViewModel extends ViewModel {

    private WordRepository wordRepository;

    public LiveData<PagedList<Word>> wordList;

    public WordViewModel(Application application){

        wordRepository = new WordRepository(application);

        // Instance of WordDataSourceFactory
        WordDataSourceFactory factory = new WordDataSourceFactory(application);

        // Create PagedList Config
        PagedList.Config config = (new PagedList.Config.Builder()).setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20).setPageSize(25).build();

        wordList = new LivePagedListBuilder<>(factory,config).build();
    }

    public static class WordViewModelFactory extends ViewModelProvider.NewInstanceFactory{
        private Application mApplication;

        public WordViewModelFactory(Application application){
            mApplication = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new WordViewModel(mApplication);
        }
    }

    public void insert(Word word) { wordRepository.insert(word); }
}
