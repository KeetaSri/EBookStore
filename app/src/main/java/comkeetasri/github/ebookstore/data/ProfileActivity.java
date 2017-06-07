package comkeetasri.github.ebookstore.data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import comkeetasri.github.ebookstore.R;

import static comkeetasri.github.ebookstore.data.MainActivity.user;

public class ProfileActivity extends AppCompatActivity {

    TextView balance;
    Button addButton, homeButton, cartButton, myBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        balance = (TextView) findViewById(R.id.showBalance);
        addButton = (Button) findViewById(R.id.addMoneyButton);

        balance.setText( "" + user.getBalance() );

        homeButton = (Button) findViewById(R.id.homeButton);
        cartButton = (Button) findViewById(R.id.cartButton);
        myBookButton = (Button) findViewById(R.id.myBookButton);

        homeButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View v ) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });

        cartButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(i);

            }
        });

        myBookButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View v ) {
                Intent i = new Intent(getApplicationContext(), MyBook.class);
                startActivity(i);

            }
        });

        if(addButton.isClickable()) {
            addMoney(addButton);
        }
    }

    public void addMoney( Button b ){
        b.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View v ) {
                user.setBalance( user.getBalance() + 500 );
                balance.setText( "" + user.getBalance() );

            }

        });
    }
}
