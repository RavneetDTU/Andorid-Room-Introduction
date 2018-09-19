package info.ravneetpunia.wordpaging1.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WordDao {

    @Query("select * from word_table where id >= :id LIMIT :size")
    public List<Word> getWordBySize(int id, int size);

    @Query("DELETE from word_table")
    void deleteall();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Word word);

}
