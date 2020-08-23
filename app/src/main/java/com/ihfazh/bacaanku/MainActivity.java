package com.ihfazh.bacaanku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.ihfazh.bacaanku.data.Book;
import com.ihfazh.bacaanku.data.JsonReader;
import com.ihfazh.bacaanku.data.Reader;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private JsonReader reader;
    private ArrayList<Book> books = new ArrayList<>();
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            reader = new JsonReader(this);
            books.addAll(reader.getBooks());
            System.out.println(books);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        rv = findViewById(R.id.rv_book_list);

        showBookList();

    }

    private void showBookList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        BookListAdapter bookListAdapter = new BookListAdapter(books);
        bookListAdapter.setOnItemClickCallback(
                new OnItemClickCallback() {
                    @Override
                    public void onItemClicked(Book book) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra(DetailActivity.BOOK_ID, String.valueOf(book.getId()));
                        startActivity(intent);
                    }
                }
        );
        rv.setAdapter(bookListAdapter);
    }
}

