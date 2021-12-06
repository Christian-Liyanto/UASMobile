package id.ac.umn.travelgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import id.ac.umn.travelgo.R;

public class HomeActivity extends AppCompatActivity {

    private static HomeActivity instance;

    RecyclerView recyclerView;

    String username;
    String hotelname[];
    int price[] = {400000, 300000, 250000, 500000, 200000,
                   800000, 150000, 600000, 450000, 125000};
    int images[] = {R.drawable.hotela, R.drawable.hotelb, R.drawable.hotelc,
            R.drawable.hotele, R.drawable.hotelf, R.drawable.hotelg,
            R.drawable.hotelh, R.drawable.hoteli, R.drawable.hotelj,
            R.drawable.hoteld};
    TextView home, booking;
    ImageButton profile, refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        instance = this;
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        profile = (ImageButton) findViewById(R.id.profileUser);
        recyclerView = findViewById(R.id.recyclerView);
        refresh = findViewById(R.id.historyUser);
        hotelname = getResources().getStringArray(R.array.hotelname);
        home = (TextView) findViewById(R.id.HOME);
        booking = (TextView) findViewById(R.id.totalBooking);
        hotelAdapter hotelAdapter = new hotelAdapter(this, hotelname, images, price);
        recyclerView.setAdapter(hotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        home.setText("Selamat Datang "+ username);

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTotalBooking();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        getTotalBooking();
    }

    void getTotalBooking(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("booking");
        DatabaseReference reference2 = reference.child(username);
        Query bookingtotal = reference2;
        bookingtotal.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int size = (int) snapshot.getChildrenCount();
                booking.setText("Total Booking : "+String.valueOf(size));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static HomeActivity getInstance(){
        return instance;
    }

    public String getUsername(){
        return username;
    }
}
// Script By Christian Liyanto - 00000033739