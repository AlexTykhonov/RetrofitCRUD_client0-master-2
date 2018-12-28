package com.example.retrofitcrud_client0;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.ArrayList;

public class BookDiffUtil extends DiffUtil.Callback {

    ArrayList<Book> oldBookList;
    ArrayList<Book> newBookList;

    public BookDiffUtil(ArrayList<Book> oldBookList, ArrayList<Book> newBookList) {
        this.oldBookList = oldBookList;
        this.newBookList = newBookList;
    }

    @Override
    public int getOldListSize() {
        return oldBookList.size();
    }

    @Override
    public int getNewListSize() {
        return newBookList.size();
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        return oldBookList.get(i).getId()==newBookList.get(i1).getId();
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        final Book oldBook = oldBookList.get(i);
        final Book newBook = newBookList.get(i1);
        return oldBook.equals(newBook);
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
