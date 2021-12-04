package id.ac.umn.travelgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    EditText username1, email, password1, confirmPassword;
    Button signup1, cancelsignup;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username1 = (EditText)findViewById(R.id.username1);
        email = (EditText)findViewById(R.id.email);
        password1 = (EditText)findViewById(R.id.password1);
        confirmPassword = (EditText)findViewById(R.id.confirmPassword);
        signup1 = (Button)findViewById(R.id.signup1);
        cancelsignup = (Button)findViewById(R.id.cancelsignup);

        cancelsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //get all values
                String user = username1.getText().toString();
                String em = email.getText().toString();
                String pass = password1.getText().toString();
                String repass = confirmPassword.getText().toString();

                if(user.isEmpty() || em.isEmpty() || pass.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Harap untuk mengisi field berikut !!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(Patterns.EMAIL_ADDRESS.matcher(em).matches()){
                        if(pass.length() >= 6){
                            if(pass.equals(repass)){

                                Query uniqueUser = reference.orderByChild("username").equalTo(user);
                                uniqueUser.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists()){
                                            username1.setError("Username sudah terdarfar silahkan gunakan username lain!!");
                                        }
                                        else{
                                            UserHelperClass helperClass = new UserHelperClass(user, em, pass, repass);
                                            reference.child(user).setValue(helperClass);
                                            Toast.makeText(RegisterActivity.this, "Akun pengguna berhasil teregister!!", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                            else{
                                confirmPassword.setError("Confirm Password tidak sama dengan Password!!");
                            }
                        }
                        else {
                            password1.setError("Password setidaknya terdiri dari 6 kata/angka !!");
                        }

                    }
                    else {
                        email.setError("Email yang diinput tidak valid!!");
                    }
                }
            }
        });
    }
}