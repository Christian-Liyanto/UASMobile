package id.ac.umn.travelgo;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import id.ac.umn.travelgo.R;

public class hotelAdapter extends RecyclerView.Adapter<hotelAdapter.MyViewHolder> {

    String hname[];
    int img[];
    Context context;

    public hotelAdapter(Context ct, String hotelname[], int images[]){
        context = ct;
        hname = hotelname;
        img = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.hotelrow, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.hoteltext.setText(hname[position]);
        holder.hotelimg.setImageResource(img[position]);
    }

    @Override
    public int getItemCount() {
        return hname.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView hoteltext;
        ImageView hotelimg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            hoteltext = itemView.findViewById(R.id.hotelname);
            hotelimg = itemView.findViewById(R.id.hotelimg);

        }
    }
}
