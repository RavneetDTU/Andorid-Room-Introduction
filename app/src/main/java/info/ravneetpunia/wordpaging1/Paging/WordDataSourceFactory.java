package info.ravneetpunia.wordpaging1.Paging;

import android.arch.paging.DataSource;
import android.content.Context;

import info.ravneetpunia.wordpaging1.Database.Word;

public class WordDataSourceFactory extends DataSource.Factory<Integer, Word> {

    private Context ctx;
    private WordDataSourceLimit wordDataSource;

    public WordDataSourceFactory(Context context){
        this.ctx = context;
    }

    @Override
    public DataSource<Integer, Word> create() {
        if(wordDataSource == null){
            wordDataSource = new WordDataSourceLimit(ctx);
        }

        return wordDataSource;
    }
}
