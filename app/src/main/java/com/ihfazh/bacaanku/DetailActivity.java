package com.ihfazh.bacaanku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ihfazh.bacaanku.data.Book;
import com.ihfazh.bacaanku.data.JsonReader;

import org.json.JSONException;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity {

    final public static String BOOK_ID = "book_id";
    private TextView tvTitle, tvAuthor, tvDescription;
    private ImageView img;
    private Button btnSearch;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String bookId = getIntent().getStringExtra(BOOK_ID);

        tvTitle = findViewById(R.id.tv_title);
        tvAuthor = findViewById(R.id.tv_author);
        tvDescription = findViewById(R.id.tv_description);

        img = findViewById(R.id.img_book);

        btnSearch = findViewById(R.id.cari_penjual);

        try {
            JsonReader reader = new JsonReader(this);
            book = reader.getBookById(bookId);
            displayDetailData();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }

    private void displayDetailData() {
        Glide.with(this)
                .load(book.getImageUrl())
                .into(img);
        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());
        tvDescription.setText(book.getDescription());

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, "Beli buku " + book.getTitle());
                startActivity(intent);
            }
        });

    }
}