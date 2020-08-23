package com.ihfazh.bacaanku;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    final public static String BOOK_ID = "book_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String bookId = getIntent().getStringExtra(BOOK_ID);

        Toast.makeText(this, "Hello from " + bookId, Toast.LENGTH_SHORT).show();
    }
}