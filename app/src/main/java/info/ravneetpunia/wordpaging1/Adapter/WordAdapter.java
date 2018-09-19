package info.ravneetpunia.wordpaging1.Adapter;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import info.ravneetpunia.wordpaging1.Database.Word;
import info.ravneetpunia.wordpaging1.R;

public class WordAdapter extends PagedListAdapter<Word, WordAdapter.WordViewHolder> {

    public static final String TAG = "false";

    public WordAdapter(){
        super(DIFF_CALLBACK);
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new WordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word thisWord = getItem(position);
        Log.d(TAG, "onBindViewHolder: "+thisWord);
        if(thisWord != null){
            holder.bindTO(getItem(position));
        }
    }

    //DiffUtil is used to find out whether two object in the list are same or not
    public static DiffUtil.ItemCallback<Word> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Word>() {
                @Override
                public boolean areItemsTheSame(@NonNull Word oldWord,
                                               @NonNull Word newWord) {
                    return oldWord.getId() == newWord.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Word oldWord,
                                                  @NonNull Word newWord) {
                    return oldWord.equals(newWord);
                }
            };

    class WordViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;

        public WordViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_word_listItem);
        }

        public void bindTO(Word word){
            textView.setText(word.getWord()+"; ID:"+word.getId());
        }
    }
}
