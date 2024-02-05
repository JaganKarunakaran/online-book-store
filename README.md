# BOOK API - Spring Boot, Postgres, JPA Rest API Tutorial

Build Restful CRUD API for a online Book-Store application using Spring Boot, Postgres and Hibernate.

## Requirements

1. Java - 17.x

2. Maven - 3.8.x

3. Postgres - 15

4. Spring Boot - 4.x

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/JaganKarunakaran/online-book-store.git
```

**2. Run Postgres Queries**

```bash
CREATE TABLE book_mst (
  book_id numeric(20,0),
  title VARCHAR(255),
  author_id numeric(20,0),
  isbn_num numeric(20,0) unique,
  review varchar(1000),
  CONSTRAINT book_id_primary PRIMARY KEY(book_id),
  CONSTRAINT fk_author FOREIGN KEY(author_id) REFERENCES author_mst(author_id)
);
CREATE SEQUENCE book_MST_book_Id_seq OWNED BY book_MST.book_id;

CREATE TABLE author_mst (
  author_id numeric(20,0),
  author_name VARCHAR(255),
  CONSTRAINT author_id_primary PRIMARY KEY(author_id)
);

```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET /onlineBook-app/api/books - Retrieve all books with filters for searching.

    POST /onlineBook-app/api/books - Add a new book to the inventory.
    sample Book object 
    {
    "title": "book 1",
    "isbnNum": "1234567891015",
    "author":{
    "authorId": "1",
    "authorName":"Scott"
    },
    "authorId": "1"
    }
    Valid ISBN number - 13 digits
    
    GET /onlineBook-app/api/books/{isbn} - Retrieve details for a specific book by ISBN.
    
    PUT /onlineBook-app/api/books/{isbn} - Update details of a book by ISBN.

    DELETE /onlineBook-app/api/books/{isbn} - Remove a book from the inventory by ISBN.

    POST /onlineBook-app/api/books/{isbn}/reviews - Submit a review for a book.

    GET /onlineBook-app/api/authors/{authorId} - Retrieve details for a specific author

You can test them using postman or any other rest client.
