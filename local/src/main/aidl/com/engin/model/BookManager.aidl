// BookManager.aidl
package com.engin.model;

// Declare any non-default types here with import statements
import com.engin.model.Book;
interface BookManager {
    List<Book> getBook();
    void addBook(in Book book);
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
