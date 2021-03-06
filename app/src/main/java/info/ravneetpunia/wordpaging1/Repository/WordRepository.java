package info.ravneetpunia.wordpaging1.Repository;

import android.app.Application;
import android.os.AsyncTask;

import info.ravneetpunia.wordpaging1.Database.Word;
import info.ravneetpunia.wordpaging1.Database.WordDao;
import info.ravneetpunia.wordpaging1.Database.WordDatabase;

public class WordRepository {

    private WordDao mWordDao;

    public WordRepository(Application application){
        WordDatabase db = WordDatabase.getDatabase(application);
        mWordDao = db.wordDao();
    }


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

}
