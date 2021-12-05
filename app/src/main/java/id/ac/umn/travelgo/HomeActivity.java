package id.ac.umn.travelgo;

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
            R.drawable.hotela, R.drawable.hotelb, R.drawable.hotelc,
            R.drawable.hotela, R.drawable.hotelb, R.drawable.hotelc,
            R.drawable.hotela};
    TextView home;
    ImageButton profile, history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        home = (TextView) findViewById(R.id.HOME);
        profile = (ImageButton) findViewById(R.id.profileUser);
        history = (ImageButton) findViewById(R.id.bookingHistory);
        instance = this;
        recyclerView = findViewById(R.id.recyclerView);
        hotelname = getResources().getStringArray(R.array.hotelname);

        hotelAdapter hotelAdapter = new hotelAdapter(this, hotelname, images, price);
        recyclerView.setAdapter(hotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        home.setText("Selamat Datang "+username);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
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