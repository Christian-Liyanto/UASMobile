package id.ac.umn.travelgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PemesananActivity extends AppCompatActivity {

    TextView namaHotelTV, hargaHotelTV, totalhargaBooking, namaBooking;
    EditText jumlahBooking, lamaBooking;
    ImageView gambarHotel;
    String namahotel;
    int gambar, hargahotel, totalHarga, jumlahKamar, lamaNginap;
    Button cekHarga, bookingKamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        namaHotelTV = (TextView) findViewById(R.id.namahotelBooking);
        hargaHotelTV = (TextView) findViewById(R.id.hargahotelBooking);
        gambarHotel = (ImageView) findViewById(R.id.gambarhotelBooking);
        totalhargaBooking = (TextView) findViewById(R.id.totalhargaBooking);
        jumlahBooking = (EditText) findViewById(R.id.jumlahBooking);
        lamaBooking = (EditText) findViewById(R.id.lamaBooking);
        namaBooking = (TextView) findViewById(R.id.namaBooking);
        cekHarga = (Button) findViewById(R.id.cekhargaBooking);
        bookingKamar = (Button) findViewById(R.id.buttonBooking);

        Intent getData = getIntent();
        gambar = getData.getIntExtra("gambar", 1);
        namahotel = getData.getStringExtra("nama");
        hargahotel = getData.getIntExtra("harga", 1);

        gambarHotel.setImageResource(gambar);
        namaHotelTV.setText(namahotel);
        hargaHotelTV.setText(String.valueOf(hargahotel));

        String username = HomeActivity.getInstance().getUsername();
        namaBooking.setText(username);

        cekHarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1, data2;
                data1 = jumlahBooking.getEditableText().toString();
                data2 = lamaBooking.getEditableText().toString();
                if(data1.isEmpty() || data2.isEmpty()){
                    Toast.makeText(PemesananActivity.this, "Masukkan Jumlah kamar dan Lama Hari!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    jumlahKamar = Integer.parseInt(data1);
                    lamaNginap = Integer.parseInt(data2);
                    totalHarga = hargahotel * jumlahKamar * lamaNginap;
                    totalhargaBooking.setText(String.valueOf(totalHarga));
                }
            }
        });

        bookingKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = String.valueOf(totalHarga);
                if(value.equals("0")){
                    Toast.makeText(PemesananActivity.this, "Silahkan cek total harga terlebih dahulu!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), ValidasiBooking.class);
                    intent.putExtra("pemesan",username);
                    intent.putExtra("hotel",namahotel);
                    intent.putExtra("kamar",String.valueOf(jumlahKamar));
                    intent.putExtra("hari",String.valueOf(lamaNginap));
                    intent.putExtra("total", String.valueOf(totalHarga));
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
// Script By Christian Liyanto - 00000033739