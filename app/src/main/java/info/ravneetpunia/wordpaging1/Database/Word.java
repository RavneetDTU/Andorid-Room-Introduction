package info.ravneetpunia.wordpaging1.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Word {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String word;

    public Word(String word) {
        this.word = word;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
