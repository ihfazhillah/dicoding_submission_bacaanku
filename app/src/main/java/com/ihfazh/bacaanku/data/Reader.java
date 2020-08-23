package com.ihfazh.bacaanku.data;

import java.util.List;

public interface Reader {
    public List<Book> getBooks();
    public Book getBookById(String id);
}
