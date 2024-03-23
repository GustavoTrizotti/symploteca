package com.gustavotrizotti.domain.entities.book;

import java.util.Optional;

public class Book {
    private Integer id;
    private Integer edition;
    private Integer numberOfPages;
    private String name;
    private String authors;
    private String publisher;
    private String ISBN;
    private BookGender gender;
    private BookStatus bookStatus;

    public Book() {
        bookStatus = BookStatus.AVAILABLE;
    }

    public Book(Integer edition, Integer numberOfPages, String name, String authors, String publisher, String ISBN, BookGender gender, BookStatus bookStatus) {
        this(null, edition, numberOfPages, name, authors, publisher, ISBN, gender, bookStatus);
    }

    public Book(Integer id, Integer edition, Integer numberOfPages, String name, String authors, String publisher, String ISBN, BookGender gender, BookStatus bookStatus) {
        this.id = id;
        this.edition = edition;
        this.numberOfPages = numberOfPages;
        this.name = name;
        this.authors = authors;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.gender = gender;
        this.bookStatus = bookStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public BookGender getGender() {
        return gender;
    }

    public void setGender(BookGender gender) {
        this.gender = gender;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", edition=" + edition +
                ", numberOfPages=" + numberOfPages +
                ", name='" + name + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", gender=" + gender +
                ", bookStatus=" + bookStatus +
                '}';
    }
}
