package com.ihfazh.bacaanku.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonReader implements Reader {
    private ArrayList<Book> books = new ArrayList<>();
    private Context context;

    public JsonReader(Context ctx) throws IOException, JSONException {
        this.context = ctx;
        loadBooks();
    }

    private void loadBooks() throws IOException, JSONException {
        InputStream inputStream = this.context.getAssets().open("daftarBuku.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String line = bufferedReader.readLine();
        while (line != null){
            stringBuilder.append(line).append("\n");
            line = bufferedReader.readLine();
        }

        String string = stringBuilder.toString();
        JSONArray booksArray = new JSONArray(string);

        for (int index = 0; index < booksArray.length(); index++) {
            JSONObject bookObject =  booksArray.getJSONObject(index);

            Book book = new Book();
            book.setAuthor(bookObject.getString("author"));
            book.setId(bookObject.getInt("id"));
            book.setDescription(bookObject.getString("description"));
            book.setTitle(bookObject.getString("title"));
            book.setImageUrl(bookObject.getString("image"));

            JSONObject attributesObject = bookObject.getJSONObject("attributes");
            Iterator<String> keys = attributesObject.keys();

            ArrayList<Attribute> attributesTemp = new ArrayList<>();

            while (true){

                Boolean noNextKey = !keys.hasNext();
                if (noNextKey){
                    break;
                }

                String key = keys.next();
                Attribute attribute = new Attribute();
                attribute.setKey(key);
                attribute.setValue(attributesObject.getString(key));
                attributesTemp.add(attribute);
            }

            book.setAttributeList(attributesTemp);

            books.add(book);

        }
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Book getBookById(String id) {
        for (Book book :
                books) {
            if (id.equals(String.valueOf(book.getId()))){
                return book;
            }
        }

        return null;
    }
}
