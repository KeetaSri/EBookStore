package comkeetasri.github.ebookstore.data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import comkeetasri.github.ebookstore.R;
import comkeetasri.github.ebookstore.model.Book;

public class MyBook extends AppCompatActivity {

    ArrayAdapter<Book> listadapter;
    Button homeButton;
    ListView myBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_book);

        homeButton = (Button) findViewById(R.id.homeButton2);
        myBook = (ListView) findViewById(R.id.listView_cart);

        listadapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, MainActivity.user.getMyBook());
        myBook.setAdapter(listadapter);

        homeButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent ( getApplicationContext(), MainActivity.class );
                startActivity(i);

            }
        });

    }
}
