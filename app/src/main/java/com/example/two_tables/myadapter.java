package com.example.two_tables;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    //initialize all the data
    private Context context;
    private ArrayList<moviesModel> MoviesArraylist;

    //create constructor
    public myadapter(Context context, ArrayList<moviesModel> movieArrayList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.MoviesArraylist = movieArrayList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    //find all the views
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView movie_name, price;
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.movie_name = itemView.findViewById(R.id.movieName);
            this.price = itemView.findViewById(R.id.movieprice);
            this.img = itemView.findViewById(R.id.imageview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface !=null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemclick(position);
                        }
                    }
                }
            });
        }
    }

    //implement methods
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);

        return new ViewHolder(view);
    }

    //set new value to the views
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        moviesModel model = MoviesArraylist.get(position);


        holder.movie_name.setText(model.getName());
        holder.price.setText(model.getPrice());

        byte[] byteImage = model.getPoster();
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteImage,0,byteImage.length);
        //-----
        holder.img.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return MoviesArraylist.size();
    }


}
