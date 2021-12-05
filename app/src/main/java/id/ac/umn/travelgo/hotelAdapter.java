package id.ac.umn.travelgo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.umn.travelgo.R;

public class hotelAdapter extends RecyclerView.Adapter<hotelAdapter.MyViewHolder> {

    String hname[];
    int img[], cost[];
    Context context;

    public hotelAdapter(Context ct, String hotelname[], int images[], int price[]){
        context = ct;
        hname = hotelname;
        img = images;
        cost = price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.hotelrow, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.hoteltext.setText(hname[position]);
        holder.hotelprice.setText(String.valueOf(cost[position]));
        holder.hotelimg.setImageResource(img[position]);

        holder.booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent booking = new Intent(context, PemesananActivity.class);
                booking.putExtra("gambar", img[position]);
                booking.putExtra("nama", hname[position]);
                booking.putExtra("harga", cost[position]);
                context.startActivity(booking);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hname.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        Button booking;
        TextView hoteltext, hotelprice;
        ImageView hotelimg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hoteltext = itemView.findViewById(R.id.hotelname);
            hotelprice = itemView.findViewById(R.id.hotelprice);
            hotelimg = itemView.findViewById(R.id.hotelimg);
            booking = itemView.findViewById(R.id.bookinghotel);
        }
    }
}
// Script By Christian Liyanto - 00000033739