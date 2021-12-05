package id.ac.umn.travelgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PemesananActivity extends AppCompatActivity {

    TextView namaHotelTV, hargaHotelTV;
    ImageView gambarHotel;
    String namahotel;
    int gambar, hargahotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        namaHotelTV = (TextView) findViewById(R.id.namahotelBooking);
        hargaHotelTV = (TextView) findViewById(R.id.hargahotelBooking);
        gambarHotel = (ImageView) findViewById(R.id.gambarhotelBooking);

        Intent getData = getIntent();
        gambar = getData.getIntExtra("gambar", 1);
        namahotel = getData.getStringExtra("nama");
        hargahotel = getData.getIntExtra("harga", 1);

        gambarHotel.setImageResource(gambar);
        namaHotelTV.setText(namahotel);
        hargaHotelTV.setText(String.valueOf(hargahotel));
    }
}

