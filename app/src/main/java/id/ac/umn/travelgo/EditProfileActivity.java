package id.ac.umn.travelgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    Button updateProfile, cancelUpdate;
    EditText emailET, passwordET, confirmpasswordET;
    String username, email, password, confirmpassword;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        updateProfile = (Button) findViewById(R.id.confirmUpdate);
        cancelUpdate = (Button) findViewById(R.id.cancelUpdate);
        emailET = (EditText) findViewById(R.id.editEmail);
        passwordET = (EditText) findViewById(R.id.editPassword);
        confirmpasswordET = (EditText) findViewById(R.id.editConfirmPassword);

        Intent getData = getIntent();
        username = getData.getStringExtra("username");
        email = getData.getStringExtra("email");
        password = getData.getStringExtra("password");
        confirmpassword = getData.getStringExtra("password");
        emailET.setText(email);

        reference = FirebaseDatabase.getInstance().getReference("users");

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = emailET.getText().toString();
                String pass = passwordET.getText().toString();
                String repass = confirmpasswordET.getText().toString();
                Boolean validation = fieldChecker(em, pass, repass);
                if(validation){

                    UserHelperClass helperClass = new UserHelperClass(username, em, pass, repass);
                    reference.child(username).setValue(helperClass);

                    Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(logout);
                }
            }
        });

        cancelUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(getApplicationContext(), ProfileActivity.class);
                cancel.putExtra("username", username);
                startActivity(cancel);
                finish();
            }
        });

    }

    Boolean fieldChecker(String email, String password, String confirmpassword){
        if(email.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()){
            Toast.makeText(this, "Harap isi semua field!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if (password.length() >= 6){
                    if(password.equals(confirmpassword)){
                        return true;
                    }
                    else{
                        confirmpasswordET.setError("Confirm Password tidak sama dengan Password!!");
                        return false;
                    }
                }
                else {
                    passwordET.setError("Password setidaknya terdiri dari 6 kata/angka!!");
                    return false;
                }
            }
            else {
                emailET.setError("Email tidak valid!!");
                return false;
            }
        }
    }
}
// Script By Christian Liyanto - 00000033739