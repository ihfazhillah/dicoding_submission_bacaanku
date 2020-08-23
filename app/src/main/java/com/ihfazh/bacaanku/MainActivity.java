package com.ihfazh.bacaanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ihfazh.bacaanku.data.Book;
import com.ihfazh.bacaanku.data.JsonReader;
import com.ihfazh.bacaanku.data.Reader;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    JsonReader reader;
    ArrayList<Book> books = new ArrayList<>();

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

    }
}