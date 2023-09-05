package com.example.two_tables;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class detailPage extends AppCompatActivity {

    private ImageView poster;
    private TextView movieName, movieDescription;
    private Button add_to_cart, share_button, back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_page);

        movieName = findViewById(R.id.movie_name);
        movieDescription = findViewById(R.id.movie_description);
        poster = findViewById(R.id.movie_poster);
        add_to_cart = findViewById(R.id.add_to_cart);
        share_button = findViewById(R.id.share_button);
        back_button = findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detailPage.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //remove the action bar
        getSupportActionBar().hide();


        //get all the values
        String movie_name = getIntent().getStringExtra("movie_name");
        String movies_description = getIntent().getStringExtra("movies_description");
        String movie_price = getIntent().getStringExtra("movies_price");
        String video_id = getIntent().getStringExtra("vid_id");
        byte[] byte_poster = getIntent().getByteArrayExtra("poster");


        //set player
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youTubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = video_id;
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        //set movie name
        movieName.setText(movie_name);

        //set description
        movieDescription.setText(movies_description);

        //set poster
        Bitmap bitmap = BitmapFactory.decodeByteArray(byte_poster,0,byte_poster.length);
        //-----
        poster.setImageBitmap(bitmap);


        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(detailPage.this, cart.class);

                intent.putExtra("name", movie_name);
                intent.putExtra("price", movie_price);
                intent.putExtra("poster", byte_poster);

                startActivity(intent);
            }
        });


        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");

                String _moviename = movie_name;
                String _movieId = "https://youtu.be/" + video_id;

                intent.putExtra(Intent.EXTRA_SUBJECT, "Love this movie!!!");
                intent.putExtra(Intent.EXTRA_TEXT, " Checkout this movie: " + _moviename + "!!!\n" + " Here is the trailer:  "+ _movieId );

                startActivity(Intent.createChooser(intent, "Choose a platform"));
            }
        });

    }
}
