package com.example.rentit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Houses> houses;


    public MyAdapter(Context context, List<Houses> houses) {
        this.context = context;
        this.houses = houses;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context)
                .inflate(R.layout.name,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Houses currentHouse=houses.get(position);


        String imageUrl=currentHouse.getImgHouse();
        holder.name.setText(currentHouse.getName());
        holder.city.setText(currentHouse.getCity());
        holder.price.setText(currentHouse.getPrice());
        holder.type.setText(currentHouse.getType());
        holder.address.setText(currentHouse.getAddress());
        holder.number.setText(currentHouse.getNumber());



        // using glide library to show images
         Glide.with(context).load(imageUrl).fitCenter().into(holder.imgHouse);
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }


    // ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHouse;
        TextView name,city,price,type,address,number;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHouse=itemView.findViewById(R.id.imgHouse);
            name=itemView.findViewById(R.id.name);
            city=itemView.findViewById(R.id.city);
            price=itemView.findViewById(R.id.price);
            type=itemView.findViewById(R.id.type);
            address=itemView.findViewById(R.id.address);
            number=itemView.findViewById(R.id.number);


        }
    }

}
