package com.example.retrofitcrud_client0;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,  int i) {

        View view = layoutInflater.inflate(R.layout.listbook, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Book book = bookArrayList.get(i);
        viewHolder.bookId.setText(book.getId().toString());
        viewHolder.bookTitle.setText(book.getTitle());
        viewHolder.bookAuthor.setText(book.getAuthor());
        viewHolder.bookDescription.setText(book.getDescription());
        viewHolder.bookPublished.setText(book.getPublished().toString());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(context,
                        "BookID"+bookArrayList.get(i).getId(), Toast.LENGTH_LONG);
                toast.show();
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("id", bookArrayList.get(i).getId());
                intent.putExtra("title", bookArrayList.get(i).getTitle());
                intent.putExtra("author", bookArrayList.get(i).getAuthor());
                intent.putExtra("description", bookArrayList.get(i).getDescription());
                intent.putExtra("published", bookArrayList.get(i).getPublished());
                context.startActivity(intent);
            }
        });
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
        final LinearLayout linearLayout;
        final CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.bookId = itemView.findViewById(R.id.bookId);
            this.bookTitle = itemView.findViewById(R.id.bookTitle);
            this.bookAuthor = itemView.findViewById(R.id.bookAuthor);
            this.bookDescription = itemView.findViewById(R.id.bookDescription);
            this.bookPublished = itemView.findViewById(R.id.bookPublished);
            this.linearLayout = itemView.findViewById(R.id.linearLayout);
            this.cardView = itemView.findViewById(R.id.card_view);
        }

    }
    public void  setData (ArrayList<Book> setBookList) {

        final BookDiffUtil bookDiffUtil = new BookDiffUtil(bookArrayList, setBookList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(bookDiffUtil);

        bookArrayList.clear();
        bookArrayList.addAll(setBookList);
        diffResult.dispatchUpdatesTo(this);
        //         notifyDataSetChanged();
// latest update



        }

}
