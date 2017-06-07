package comkeetasri.github.ebookstore.data;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import comkeetasri.github.ebookstore.R;
import comkeetasri.github.ebookstore.model.Book;

import static comkeetasri.github.ebookstore.data.MainActivity.user;
import static comkeetasri.github.ebookstore.data.MainActivity.cartArrayList;

public class CartActivity extends AppCompatActivity {

    ArrayAdapter<Book> cartAdapter;

    Button homeButton, purchaseButton;
    TextView total;
    ListView cartList;

    double sum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartList = (ListView) findViewById(R.id.listView_cart);
        cartAdapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, MainActivity.cartArrayList);
        cartList.setAdapter(cartAdapter);

        total = (TextView) findViewById(R.id.showSum);
        total.setText( "" + totalPrice( MainActivity.cartArrayList ) );

        homeButton = (Button) findViewById(R.id.homeButtonCart);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent ( getApplicationContext(), MainActivity.class );
                startActivity(i);

            }
        });

        checkout();

    }

    public void checkout(){
        purchaseButton = (Button) findViewById(R.id.purchaseButton);
        purchaseButton.setOnClickListener(new AdapterView.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);

                alertDialog.setTitle("Notification");
                alertDialog.setMessage("Buy this book?");

                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick( DialogInterface dialog, int which ) {
                        if ( sum > user.getBalance() ){
                            Toast.makeText( getApplicationContext(), "Insufficient fund", Toast.LENGTH_SHORT ).show();

                        }
                        else {

                            Toast.makeText( getApplicationContext(), "Purchase completed", Toast.LENGTH_SHORT ).show();
                            user.setBalance( user.getBalance() - sum );

                            for ( int i = 0 ; i < cartArrayList.size() ; i++ ) {
                                user.getMyBook().add( cartArrayList.get(i) );

                            }

                            cartArrayList.clear();

                        }
                    }
                });

                alertDialog.setNegativeButton( "No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick( DialogInterface dialog, int which ) {
                        Toast.makeText( getApplicationContext(),"Cancel", Toast.LENGTH_SHORT ).show();

                    }

                });

                alertDialog.show();

            }

        });

    }

    public double totalPrice(ArrayList<Book> cartBook){
        for ( Book b : cartBook ){
            sum += b.getPrice();
        }
        return sum;
    }

}
