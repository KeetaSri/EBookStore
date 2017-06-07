package comkeetasri.github.ebookstore.model;

import java.util.ArrayList;
import java.util.List;

public class MockUpRepository extends BookRepository {

    private List<Book> books;
    private static  MockUpRepository instance = null;

    public static MockUpRepository getInstance() {
        if( instance == null ) {
            instance = new MockUpRepository();

        }

        return instance;

    }

    private MockUpRepository() {
        books = new ArrayList<Book>();

        books.add( new Book ( "How to be a god", "2014", 1, 350, "img") );
        books.add( new Book ( "SKE Life", "2016", 2, 150, "img" ) );

    }

    @Override
    public void fetchAllBooks() {
        setChanged();
        notifyObservers();

    }

    @Override
    public List<Book> loadBooks() {
        return books;
    }

}
