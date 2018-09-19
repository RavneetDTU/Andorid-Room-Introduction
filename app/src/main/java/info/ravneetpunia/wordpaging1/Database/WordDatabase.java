package info.ravneetpunia.wordpaging1.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Word.class}, version = 3, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase {

    private static WordDatabase INSTANCE;
    public static final Object LOCK = new Object();
    private static Context ctx;
    public abstract WordDao wordDao();

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
            wordDao.deleteall();

            Word word = new Word(1,"Ravneet");
            wordDao.insert(word);

//            String[] arr = initCoupons;
//
//            for(int i = 0;i<arr.length;i++){
//                word = new Word(arr[i]);
//                wordDao.insert(word);
//            }

            return null;
        }
    }

//    private static String[] initCoupons = {"amazon|falt 20% off on fashion",
//            "amazon|upto 30% off on electronics",
//            "ebay|falt 20% off on fashion", "ebay|upto 40% off on electronics",
//            "nordstorm|falt 30% off on fashion", "bestbuy|upto 80% off on electronics",
//            "sears|falt 60% off on fashion", "ee|upto 40% off on electronics",
//            "macys|falt 30% off on fashion", "alibaba|upto 90% off on electronics",
//            "nordstorm|falt 90% off on fashion", "ebay|upto 40% off on electronics",
//            "nordstorm|falt 30% off on fashion", "ebay|upto 70% off on electronics",
//            "jcpenny|falt 50% off on fashion", "ebay|upto 50% off on electronics",
//            "khols|falt 70% off on fashion", "ebay|upto 40% off on electronics",
//            "target|falt 30% off on fashion", "ebay|upto 20% off on electronics",
//            "costco|falt 80% off on fashion", "ebay|upto 40% off on electronics",
//            "walmart|falt 10% off on fashion", "ebay|upto 10% off on electronics",
//            "nordstorm|falt 30% off on fashion", "ebay|upto 70% off on electronics",
//            "ebay|falt 40% off on fashion", "ebay|upto 40% off on electronics",
//            "nordstorm|falt 70% off on fashion", "ebay|upto 80% off on electronics",
//            "nordstorm|falt 30% off on fashion", "ebay|upto 40% off on electronics",
//            "nordstorm|falt 60% off on fashion", "ebay|upto 50% off on electronics",
//            "ebay|falt 30% off on fashion", "ebay|upto 70% off on electronics",
//            "ebay|falt 30% off on fashion", "ebay|upto 40% off on electronics",
//            "uuuu|falt 30% off on fashion", "ebay|upto 40% off on electronics",
//            "tttt|falt 30% off on fashion", "ebay|upto 40% off on electronics",
//            "ssss|falt 30% off on fashion", "ebay|upto 40% off on electronics",
//            "eee|falt 30% off on fashion", "ebay|upto 40% off on electronics",
//            "www|falt 30% off on fashion", "ebay|upto 40% off on electronics",
//            "rrrr|falt 30% off on fashion", "tyyyy|upto 40% off on electronics",
//            "vvvv|falt 30% off on fashion", "wwwwe|upto 40% off on electronics",
//            "bbbb|falt 30% off on fashion", "ssssssssssssa|upto 40% off on electronics",
//            "mmmm|falt 30% off on fashion", "rrtttt|upto 40% off on electronics",
//
//    };
}
