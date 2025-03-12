package model.entities;

import model.entities.exception.BookAlreadyBorrowedException;
import model.entities.exception.BookNotBorrowedByUserException;
import model.entities.exception.InvalidOperationException;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;

    List<Book> borrowedBooks;

    public User( int id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public void borrowBook (Book book) throws BookAlreadyBorrowedException, InvalidOperationException {
        if (borrowedBooks.contains(book)){
            throw new BookAlreadyBorrowedException(book.getIsbn());
        }
        if (book.isBorrowed()){
            throw new BookAlreadyBorrowedException(book.getIsbn());
        }
        borrowedBooks.add(book);
        book.borrow();
    }


    public void returnBook (Book book) throws BookNotBorrowedByUserException, InvalidOperationException {
        if (!borrowedBooks.contains(book)){
            throw new BookNotBorrowedByUserException(book.getIsbn(), this.id);
        }
        borrowedBooks.remove(book);
        book.returnBook();
    }

    @Override
    public String toString() {
        return "Usuário{" +
                "id= " + id +
                ", name= " + name +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}
