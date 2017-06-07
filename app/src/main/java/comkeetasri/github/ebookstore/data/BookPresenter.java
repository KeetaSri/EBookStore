package comkeetasri.github.ebookstore.data;

import comkeetasri.github.ebookstore.model.Book;
import comkeetasri.github.ebookstore.model.BookRepository;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class BookPresenter implements Observer {
    private BookView view;
    private BookRepository repository;
    ArrayList<Book> books;

    public BookPresenter(BookRepository repository, BookView view){
        this.repository = repository;
        this.view = view;

    }

    public void init(){
        repository.addObserver(this);
        repository.fetchAllBooks();

    }

    @Override
    public void update(Observable o, Object arg) {
        if ( o == repository ){
            books = new ArrayList<Book> ( repository.loadBooks() );
            view.setBookList(books);

        }
    }
}
