package info.ravneetpunia.wordpaging1.Repository;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.widget.Toast;

import info.ravneetpunia.wordpaging1.Database.Word;
import info.ravneetpunia.wordpaging1.Database.WordDao;
import info.ravneetpunia.wordpaging1.Database.WordDatabase;

public class WordRepository {

    private static WordDatabase INSTANCE;
    public static final Object LOCK = new Object();
    private static Context ctx;
    private WordDao wordDao;

    private WordDao mWordDao;

    public synchronized static WordDatabase getDatabase(Context context){
        if(INSTANCE == null){
            ctx = context;
            synchronized (LOCK){
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context,
                            WordDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomdatabaseCallBack)
                            .build();
                }

            }
        }

        return INSTANCE;
    }

    public WordRepository(Application application){
        if(INSTANCE !=null){
            wordDao = INSTANCE.wordDao();
        }else {
            Toast.makeText(application, "DataBase Not Found....So cant insert Word", Toast.LENGTH_SHORT).show();
        }
    }

    private static RoomDatabase.Callback roomdatabaseCallBack = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDBAsync(INSTANCE).execute();
        }
    };

    // Now adding a word through AsyncTask
    private static class PopulateDBAsync extends AsyncTask<Void,Void,Void> {

        private final WordDao wordDao;

        public PopulateDBAsync(WordDatabase db){
            wordDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //wordDao.deleteAll();

            Word word = new Word("Hello");
            wordDao.insert(word);

//            for(int i = 0;i<10;i++){
//                word = new Word("World");
//                wordDao.insert(word);
//            }
                word = new Word("World");
                wordDao.insert(word);

            return null;
        }
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
