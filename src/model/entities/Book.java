package model.entities;

import model.entities.exception.InvalidOperationException;

public class Book {
    private String tittle;
    private String author;
    private int year;
    private String isbn;
    private boolean isBorrowed;

    public Book( String isbn, String tittle, String author, int year) {
        this.isbn = isbn;
        this.tittle = tittle;
        this.author = author;
        this.year = year;
        this.isBorrowed = false;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle( String tittle ) {
        this.tittle = tittle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor( String author ) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear( int year ) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn( String isbn ) {
        this.isbn = isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrow () throws InvalidOperationException {
        if (isBorrowed) {
            throw new InvalidOperationException("Livro já está alugado.");
        }
        isBorrowed = true;
    }

    public void returnBook() throws InvalidOperationException{
        if (!isBorrowed) {
            throw new InvalidOperationException("Livro já está disponível.");
        }
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return "Livro: \n"
                +"isbn: " + isbn + "\n"
                +"titulo: " + tittle + "\n"
                +"autor: " + author + "\n"
                +"ano: " + year + "\n"
                +"Alugado = " + (isBorrowed ? "Sim" : "Não") + "\n"
                +"===================";
    }
}
