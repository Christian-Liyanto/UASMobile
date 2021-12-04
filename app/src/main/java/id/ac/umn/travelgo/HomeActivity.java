package id.ac.umn.travelgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import id.ac.umn.travelgo.R;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String hotelname[];
    int images[] = {R.drawable.hotela, R.drawable.hotelb, R.drawable.hotelc,
            R.drawable.hotela, R.drawable.hotelb, R.drawable.hotelc,
            R.drawable.hotela, R.drawable.hotelb, R.drawable.hotelc,
            R.drawable.hotela};

    ImageButton profile, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        profile = (ImageButton) findViewById(R.id.profileUser);
        history = (ImageButton) findViewById(R.id.bookingHistory);

        recyclerView = findViewById(R.id.recyclerView);
        hotelname = getResources().getStringArray(R.array.hotelname);

        hotelAdapter hotelAdapter = new hotelAdapter(this, hotelname, images);
        recyclerView.setAdapter(hotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}