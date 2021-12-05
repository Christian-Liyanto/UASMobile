package id.ac.umn.travelgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ValidasiBooking extends AppCompatActivity {

    TextView pemesan, hotel, jumlah, lama, total;
    String user, namahotel, kamar, hari, harga;
    Button validasiBooking, batalValidasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validasi_booking);

        pemesan = (TextView) findViewById(R.id.namaPemesan);
        hotel = (TextView) findViewById(R.id.namaHotelPemesan);
        jumlah = (TextView) findViewById(R.id.jumlahKamarPemesan);
        lama = (TextView) findViewById(R.id.lamaPemesanan);
        total = (TextView) findViewById(R.id.hargaPemesanan);
        validasiBooking = (Button) findViewById(R.id.btnvalidasiBooking);
        batalValidasi = (Button) findViewById(R.id.btnbatalValidasi);

        Intent getData = getIntent();
        user = getData.getStringExtra("pemesan");
        namahotel = getData.getStringExtra("hotel");
        kamar = getData.getStringExtra("kamar");
        hari = getData.getStringExtra("hari");
        harga = getData.getStringExtra("total");

        pemesan.setText("Pemesan : "+user);
        hotel.setText("Hotel : "+namahotel);
        jumlah.setText("Jumlah Kamar : "+kamar+" Ruangan");
        lama.setText("Lama Hari : "+hari+" Hari");
        total.setText("Total Harga : Rp."+harga);

        batalValidasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
// Script By Christian Liyanto - 00000033739