package com.example.retrofitcrud_client0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnAddBook;
    Button btnGetBookList;
    ListView listView;
    BookInterface bookInterface;
    ArrayList<Book> listOfBooks = new ArrayList<>();
    RecyclerView recyclerView;
    RecycAdapter recycAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listView = findViewById(R.id.listView);

        btnAddBook = findViewById(R.id.btnAddBook);
        btnGetBookList = findViewById(R.id.btnGetBookList);
        btnGetBookList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getBookList();
            }
        });
// added
        recyclerView = findViewById(R.id.recyclerView);
        recycAdapter = new RecycAdapter(this, listOfBooks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycAdapter);
//
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                intent.putExtra("bookName", "");
                startActivity(intent);
            }
        });

        bookInterface = ApiUtils.getBookInterface();
    }

    public void getBookList(){
        Call<ArrayList<Book>> call = bookInterface.getBooks();
        call.enqueue(new Callback<ArrayList<Book>>(){
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                if(response.isSuccessful()){
                    listOfBooks = response.body();
                }
            recycAdapter.setData(listOfBooks);
                //listOfBooks.clear();
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
            }
        });
    }
}
