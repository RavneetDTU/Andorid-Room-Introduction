package com.example.android.roomwordssample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WordListAdapter extends PagedListAdapter<Word, WordListAdapter.WordViewHolder> {

    public static final String TAG = "false";

    protected WordListAdapter(){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        return new WordViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word thisWord = getItem(position);
        Log.d(TAG, "onBindViewHolder: "+thisWord.getWord());
        holder.wordItemView.setText(thisWord.getWord());

    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
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


}


