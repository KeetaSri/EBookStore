package comkeetasri.github.ebookstore.data;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;

import comkeetasri.github.ebookstore.R;
import comkeetasri.github.ebookstore.model.Book;
import comkeetasri.github.ebookstore.model.BookRepository;
import comkeetasri.github.ebookstore.model.MockUpRepository;
import comkeetasri.github.ebookstore.model.Profile;
import comkeetasri.github.ebookstore.model.RemoteBookRepository;

public class MainActivity extends AppCompatActivity implements BookView {

    public static ArrayList<Book> cartArrayList = new ArrayList<Book>();
    public static Profile user = new Profile();

    ListView bookListView,cartListView;
    ArrayAdapter<Book> bookAdapter;
    BookPresenter presenter;
    EditText inputText;
    Button titleButton, yearButton, sortTitle, sortYear, profile;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookRepository repository = RemoteBookRepository.getInstance();

        cartListView = (ListView) findViewById(R.id.listView_cart);
        bookListView = (ListView) findViewById(R.id.ListView_book);

        bookAdapter = createAdapter(new ArrayList<Book>());
        bookListView.setAdapter(bookAdapter);

        presenter = new BookPresenter(repository,this);
        presenter.init();

        titleButton = (Button) findViewById(R.id.titleButton);
        yearButton = (Button) findViewById(R.id.yearButton);
        sortTitle = (Button) findViewById(R.id.sortTitle);
        sortYear = (Button) findViewById(R.id.sortYear);
        profile = (Button) findViewById(R.id.profileButton);

        profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(i);
            }
        });

        searchBar();
    }

    private void searchBar() {
        id = titleButton.getId();

        inputText = (EditText) findViewById(R.id.inputText);
        inputText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ( s.toString().equals("")){
                    presenter.init();
                }
                else {
                    if ( id == titleButton.getId()) {
                        searchByTitle(s.toString());
                    }
                    else if ( id == yearButton.getId()){
                        searchByYear(s.toString());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public int searchID( View view ) {
        if ( ( ( RadioButton ) view ).isChecked() ) {
            id = view.getId();

        }

        return id;

    }

    public void searchByTitle(String textToSearch){
        ArrayList<Book> searchBook = new ArrayList<Book>();

        for ( Book b : presenter.books ){
            if ( b.getTitle().toLowerCase().contains(textToSearch.toLowerCase()) ){
                searchBook.add(b);

            }
        }

        updateAdapter( searchBook );

    }

    public void searchByYear(String textToSearch){
        ArrayList<Book> searchBook = new ArrayList<Book>();

        for ( Book b : presenter.books ){
            if ( b.getYear().contains(textToSearch) ){
                searchBook.add(b);
            }
        }

        updateAdapter( searchBook );

    }

    public void sortTitleBtn(View v) {
        if ( ( (RadioButton) v ).isChecked() ) {

            Toast.makeText( this,
                    "Sort by title",
                    Toast.LENGTH_SHORT ) .show() ;

            sortByTitle();

        } else {
            searchByTitle( inputText.getText().toString() );

        }

    }

    public void sortYearBtn(View v) {
        if (((RadioButton) v).isChecked()) {

            Toast.makeText( this,
                    "Sort by year",
                    Toast.LENGTH_SHORT ) .show() ;

            sortByYear();

        } else {
            searchByTitle( inputText.getText().toString() );

        }

    }

    public void sortByTitle(){
        final Collator col = Collator.getInstance();
        bookAdapter.sort( new Comparator<Book>() {

            @Override
            public int compare( Book o1, Book o2 ) {
                return col.compare(o1.getTitle(), o2.getTitle());

            }

        });
    }

    public void sortByYear(){
        final Collator col = Collator.getInstance();
        bookAdapter.sort(new Comparator<Book>() {

            @Override
            public int compare( Book o1, Book o2 ) {
                return col.compare(o1.getYear(), o2.getYear());

            }

        });
    }

    @Override
    public void setBookList( final ArrayList<Book> books ) {
        bookAdapter = createAdapter( books );
        bookListView.setAdapter( bookAdapter );
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Notification");
                alertDialog.setMessage("Add this book to your cart?");

                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Book added", Toast.LENGTH_SHORT).show();
                        Log.d("None",
                                "TITLE" + String.valueOf(
                                        ((Book) bookListView.getItemAtPosition(position)).getTitle()));

                        cartArrayList.add((Book) bookListView.getItemAtPosition(position));

                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();

                    }
                });

                alertDialog.show();

            }
        });

    }

    public void loadBooks(View view) {
        presenter.init();

    }

    public void updateAdapter( ArrayList<Book> b ) {
        bookAdapter = createAdapter(b);
        bookListView.setAdapter(bookAdapter);

    }

    private ArrayAdapter<Book> createAdapter(ArrayList<Book> books) {
        return new ArrayAdapter<Book>(this,android.R.layout.simple_list_item_1,books);
    }
}

