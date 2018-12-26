package com.example.retrofitcrud_client0;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecycAdapter extends RecyclerView.Adapter<RecycAdapter.ViewHolder> {

    Context context;
    ArrayList<Book> bookArrayList;
    LayoutInflater layoutInflater;

    public RecycAdapter(Context context, ArrayList<Book> bookArrayList) {
        this.context = context;
        this.bookArrayList = bookArrayList;
        layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = layoutInflater.inflate(R.layout.listbook, viewGroup,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("id", bookArrayList.get(i).getId());
                intent.putExtra("title", bookArrayList.get(i).getTitle());
                intent.putExtra("author", bookArrayList.get(i).getAuthor());
                intent.putExtra("description", bookArrayList.get(i).getDescription());
                intent.putExtra("published", bookArrayList.get(i).getPublished());
                context.startActivity(intent);
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Book book = bookArrayList.get(i);
        viewHolder.bookId.setText(book.getId().toString());
        viewHolder.bookTitle.setText(book.getTitle());
        viewHolder.bookAuthor.setText(book.getAuthor());
        viewHolder.bookDescription.setText(book.getDescription());
        viewHolder.bookPublished.setText(book.getPublished().toString());
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        final TextView bookId;
        final TextView bookTitle;
        final TextView bookAuthor;
        final TextView bookDescription;
        final TextView bookPublished;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bookId = itemView.findViewById(R.id.bookId);
            this.bookTitle = itemView.findViewById(R.id.bookTitle);
            this.bookAuthor = itemView.findViewById(R.id.bookAuthor);
            this.bookDescription = itemView.findViewById(R.id.bookDescription);
            this.bookPublished = itemView.findViewById(R.id.bookPublished);
        }

    }
    public void  setData (ArrayList<Book> setBookList) {
        bookArrayList.clear();
        bookArrayList.addAll(setBookList);
         notifyDataSetChanged();
// latest update
        }

}
