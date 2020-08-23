package com.ihfazh.bacaanku.data;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonReader implements Reader {
    private ArrayList<Book> books;
    private Context context;

    public JsonReader(Context ctx) throws IOException, JSONException {
        this.context = ctx;
        loadBooks();
    }

    private void loadBooks() throws IOException, JSONException {
        File file = new File(this.context.getFilesDir(), "daftarBuku.json");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
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
                if (!keys.hasNext()){
                    break;
                }

                String key = keys.next();
                Attribute attribute = new Attribute();
                attribute.setKey(key);
                attribute.setValue(attributesObject.getString(key));
                attributesTemp.add(attribute);
            }

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
