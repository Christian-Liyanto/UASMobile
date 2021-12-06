package id.ac.umn.travelgo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class ProfileActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PERMISSION_CODE = 1001;

    private ImageView kotakFoto;
    private Button foto;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    public Uri imageUri;

    DatabaseReference reference;
    TextView usernameTV, emailTV, passwordTV;
    String username, email, password;
    Button editProfile, deleteAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        foto = findViewById(R.id.imageButton);
        kotakFoto = findViewById(R.id.profileImage);

        usernameTV = (TextView) findViewById(R.id.profileUsername);
        emailTV = (TextView) findViewById(R.id.profileEmail);
        passwordTV = (TextView) findViewById(R.id.profilePassword);
        editProfile = (Button) findViewById(R.id.editProfile);
        deleteAcc = (Button) findViewById(R.id.deleteacc);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    pickImageFromGallery();
                }
            }
        });


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

    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery();
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE_CAPTURE) {
            imageUri = data.getData();
            kotakFoto.setImageURI(imageUri);
            uploadPicture();
        }
    }

    private void uploadPicture() {
        final String randomkey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomkey);
        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), "Failed To Upload", Toast.LENGTH_LONG).show();
                    }
                });
    }

    void deleteAccountUser(String namaPengguna){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(namaPengguna);
        reference.removeValue();
        DatabaseReference booking = FirebaseDatabase.getInstance().getReference("booking").child(namaPengguna);
        booking.removeValue();
        Intent logout = new Intent(getApplicationContext(), MainActivity.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logout);
    }
}
// Script By Christian Liyanto - 00000033739
// Script By Adipta Muhammad - 00000034712