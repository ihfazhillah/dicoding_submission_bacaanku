package com.ihfazh.bacaanku;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ihfazh.bacaanku.data.Book;
import com.ihfazh.bacaanku.data.JsonReader;
import com.ihfazh.bacaanku.data.Reader;
import com.ihfazh.bacaanku.menus.AboutMenuClick;
import com.ihfazh.bacaanku.menus.MenuClick;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private JsonReader reader;
    private ArrayList<Book> books = new ArrayList<>();
    private RecyclerView rv;
    private ArrayList<MenuClick> menuClicks = new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_root, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        for (MenuClick menuClick : menuClicks) {
                if (menuClick.test(item.getItemId())) {
                    menuClick.click(this);
                }
            }
        return super.onOptionsItemSelected(item);
    }

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

        menuClicks.add(new AboutMenuClick());

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

