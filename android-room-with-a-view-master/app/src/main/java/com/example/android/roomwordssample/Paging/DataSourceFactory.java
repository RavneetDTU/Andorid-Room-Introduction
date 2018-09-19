package com.example.android.roomwordssample.Paging;

import android.content.Context;

import com.example.android.roomwordssample.Word;

// TODO(4): Create DataSourceFactory Class

public class DataSourceFactory extends DataSource.Factory<Integer, Word> {

    private Context ctx;
    private DataSource dataSource;

    public DataSourceFactory(Context context){
        this.ctx = context;
    }


    @Override
    public android.arch.paging.DataSource<Integer, Word> create() {
        if(dataSource == null){
            dataSource = new DataSource(ctx);
        }
        return dataSource;
    }
}
