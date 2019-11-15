package ru.startandroid.develop.proba141119;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    EditText Name,Email,Phone,Username,Password;
    Button btnInsert;
    FirebaseDatabase database;
    DatabaseReference ref;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.Name);
        Phone = findViewById(R.id.Phone);
        Email = findViewById(R.id.Email);
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        btnInsert = findViewById(R.id.btnInsert);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("User");
        user = new User();

    }

    private void  getValues(){

        user.setName(Name.getText().toString());
        user.setEmail(Email.getText().toString());
        user.setPhone(Phone.getText().toString());
        user.setUserName(Username.getText().toString());
        user.setPassword(Password.getText().toString());
    };


    public void btnInsert (View view){
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getValues();
                ref.child("User01").setValue(user);
                Toast.makeText(MainActivity.this,"Data Insert....",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}


