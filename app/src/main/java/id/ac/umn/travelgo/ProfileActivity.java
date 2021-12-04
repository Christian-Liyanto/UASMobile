package id.ac.umn.travelgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    DatabaseReference reference;
    TextView usernameTV, emailTV, passwordTV;
    String username, email, password;
    Button editProfile, deleteAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameTV = (TextView) findViewById(R.id.profileUsername);
        emailTV = (TextView) findViewById(R.id.profileEmail);
        passwordTV = (TextView) findViewById(R.id.profilePassword);
        editProfile = (Button) findViewById(R.id.editProfile);
        deleteAcc = (Button) findViewById(R.id.deleteacc);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        reference = FirebaseDatabase.getInstance().getReference("users");
        Query getUserData = reference.orderByChild("username").equalTo(username);
        getUserData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    email = snapshot.child(username).child("email").getValue(String.class);
                    password = snapshot.child(username).child("password").getValue(String.class);

                    usernameTV.setText("Username  : "+username);
                    emailTV.setText("Email          : "+email);
                    passwordTV.setText("Password  : "+password);

                    editProfile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent editprofile = new Intent(getApplicationContext(), EditProfileActivity.class);
                            editprofile.putExtra("username", username);
                            editprofile.putExtra("email", email);
                            editprofile.putExtra("password", password);
                            startActivity(editprofile);
                        }
                    });

                    deleteAcc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            deleteAccountUser(username);
                            Toast.makeText(ProfileActivity.this, "Akun pengguna berhasil di hapus!!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else{
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void deleteAccountUser(String namaPengguna){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(namaPengguna);
        reference.removeValue();
        Intent logout = new Intent(getApplicationContext(), MainActivity.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logout);
    }
}