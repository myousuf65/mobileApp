package com.example.two_tables;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private ArrayList<moviesModel> allRetreivedMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        getSupportActionBar().hide();

        //video id
        String avengers_endgameVIDID = "TcMBFSGVi1c";
        String black_adamVIDID = "mkomfZHG5q4";
        String black_pantherVIDID = "_Z3QKkl1WyM";
        String captain_americaVIDID = "JerVrbLldXw";
        String captain_marvelVIDID = "Z1BCujX3pw8";
        String deadpoolVIDID = "ONHBaC-pfsk";
        String fast_and_furiousVIDID  = "uisBaTkQAEs";
        String Free_GuyVIDID =  "X2m-08cOAbc";
        String Harry_PotterVIDID = "MxqsmsA8y5k";
        String InceptionVIDID =  "YoHD9XEInc0";
        String InterstallerVIDID = "zSWdZVtXT7E";
        String ironmanVIDID = "8ugaeA-nMTc";
        String jokerVIDID = "zAGVQLHvwOY";
        String justice_leagueVIDID = "3cxixDgHUYw";
        String loganVIDID = "Div0iP65aZo";
        String man_of_steelVIDID = "wArmHSPIvlQ";


        //description
        String avengers_endgameVIDdesc = "After Thanos, an intergalactic warlord, disintegrates half of the universe, the Avengers must reunite and assemble again to reinvigorate their trounced allies and restore balance.";
        String black_adamVIDdesc = "In ancient Kahndaq, Teth Adam was bestowed the almighty powers of the gods. After using these powers for vengeance, he was imprisoned, becoming Black Adam. Nearly 5,000 years have passed, and Black Adam has gone from man to myth to legend.";
        String black_pantherVIDdesc = "Queen Ramonda, Shuri, M'Baku, Okoye and the Dora Milaje fight to protect their nation from intervening world powers in the wake of King T'Challa's death. As the Wakandans strive to embrace their next chapter, the heroes must band together with Nakia and Everett Ross to forge a new path for their beloved kingdom.";
        String captain_americaVIDdesc = "During World War II, Steve Rogers decides to volunteer in an experiment that transforms his weak body. He must now battle a secret Nazi organisation headed by Johann Schmidt to defend his nation";
        String captain_marvelVIDdesc = "Captain Marvel is a 2019 American superhero film based on Marvel Comics featuring the character Carol Danvers / Captain Marvel. Produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures, it is the 21st film in the Marvel Cinematic Universe (MCU).";
        String deadpoolVIDdesc = "Ajax, a twisted scientist, experiments on Wade Wilson, a mercenary, to cure him of cancer and give him healing powers. However, the experiment leaves Wade disfigured and he decides to exact revenge.";
        String fast_and_furiousVIDdesc  = "Dom encounters a mysterious woman, Cipher, who gets him involved in the world of terrorism. The crew has to reunite to stop Cipher and save the man who brought them together as a family.";
        String Free_GuyVIDdesc =  "When a bank teller discovers he's actually a background player in an open-world video game, he decides to become the hero of his own story -- one that he can rewrite himself. In a world where there's no limits, he's determined to save the day his way before it's too late, and maybe find a little romance with the coder who conceived him.";
        String Harry_PotterVIDdesc = "After Voldemort takes over the Ministry of Magic, Harry, Ron and Hermione are forced into hiding. They try to decipher the clues left to them by Dumbledore to find and destroy Voldemort's Horcruxes.";
        String InceptionVIDdesc =  "Cobb steals information from his targets by entering their dreams. Saito offers to wipe clean Cobb's criminal history as payment for performing an inception on his sick competitor's son.";
        String ironmanVIDdesc = "Iron Man is a 2008 American superhero film based on the Marvel Comics character of the same name. Produced by Marvel Studios and distributed by Paramount Pictures, it is the first film in the Marvel Cinematic Universe (MCU).";
        String jokerVIDdesc = "Arthur Fleck, a party clown, leads an impoverished life with his ailing mother. However, when society shuns him and brands him as a freak, he decides to embrace the life of crime and chaos";
        String justice_leagueVIDdesc = "Steppenwolf and his Parademons return after eons to capture Earth. However, Batman seeks the help of Wonder Woman to recruit and assemble Flash, Cyborg and Aquaman to fight the powerful new enemy.";
        String loganVIDdesc = "Logan comes out of retirement to escort a young mutant named Laura to a safe place. He meets with other mutants, who run from an evil corporation that has been experimenting with them, along the way.";
        String man_of_steelVIDdesc = "Clark learns about the source of his abilities and his real home when he enters a Kryptonian ship in the Artic. However, an old enemy follows him to Earth in search of a codex and brings destruction.";

        //posters
        byte[] avengers_endgame = img_to_byte(BitmapFactory.decodeResource(getResources(), R.drawable.avengers_endgame));
        byte[] black_adam = img_to_byte(BitmapFactory.decodeResource(getResources(),R.drawable.black_adam));
        byte[] black_panther = img_to_byte(BitmapFactory.decodeResource(getResources(),R.drawable.black_panther));
        byte[] captain_america = img_to_byte(BitmapFactory.decodeResource(getResources(),R.drawable.captain_america));
        byte[] captain_marvel =img_to_byte(BitmapFactory.decodeResource(getResources(),R.drawable.captain_marvel));
        byte[] deadpool =img_to_byte(BitmapFactory.decodeResource(getResources(),R.drawable.deadpool));
        byte[] fast_and_furious = img_to_byte(BitmapFactory.decodeResource(getResources(),R.drawable.fast_and_the_furious));
        byte[] free_guy = img_to_byte(BitmapFactory.decodeResource(getResources(),R.drawable.free_guy));
        byte[] harry_potter= img_to_byte(BitmapFactory.decodeResource(getResources(), R.drawable.harry_potter));
        byte[] inception= img_to_byte(BitmapFactory.decodeResource(getResources(), R.drawable.inception));
        byte[] iron_man= img_to_byte(BitmapFactory.decodeResource(getResources(), R.drawable.iron_man));
        byte[] joker= img_to_byte(BitmapFactory.decodeResource(getResources(), R.drawable.joker));
        byte[] justice_league= img_to_byte(BitmapFactory.decodeResource(getResources(), R.drawable.justice_league));
        byte[] logan= img_to_byte(BitmapFactory.decodeResource(getResources(), R.drawable.logan));
        byte[] man_of_steel= img_to_byte(BitmapFactory.decodeResource(getResources(), R.drawable.man_of_steel));

        databaseHelper helper = new databaseHelper(MainActivity.this);

        helper.addOne("Avengers Endgame", "$150.80", avengers_endgameVIDdesc, avengers_endgame, avengers_endgameVIDID );
        helper.addOne("Black Adam", "$150.00",black_adamVIDdesc, black_adam, black_adamVIDID);
        helper.addOne("Black Panther", "$250.70" ,black_pantherVIDdesc, black_panther, black_pantherVIDID);
        helper.addOne("Captain America", "$550.90", captain_americaVIDdesc, captain_america, captain_americaVIDID);
        helper.addOne("Captain Marvel", "$150.00" , captain_marvelVIDdesc, captain_marvel, captain_marvelVIDID );
        helper.addOne("Deadpool", "$150.00", deadpoolVIDdesc, deadpool, deadpoolVIDID);
        helper.addOne("Fast and Furious 8", "$150.00" ,fast_and_furiousVIDdesc, fast_and_furious, fast_and_furiousVIDID);
        helper.addOne("Free Guy", "$1750.00", Free_GuyVIDdesc, free_guy, Free_GuyVIDID );
        helper.addOne("Harry Potter", "$150.00" , Harry_PotterVIDdesc, harry_potter, Harry_PotterVIDID);
        helper.addOne("Inception", "$1509.00",InceptionVIDdesc, inception, InceptionVIDID );
        helper.addOne("Ironman", "$150.60", ironmanVIDdesc, iron_man, ironmanVIDID);
        helper.addOne("Joker", "$150.80" , jokerVIDdesc, joker, jokerVIDID);
        helper.addOne("Justice League", "$100.10", justice_leagueVIDdesc,justice_league, justice_leagueVIDID);
        helper.addOne("Logan", "$190.20", loganVIDdesc,logan,loganVIDID);
        helper.addOne("Man of Steel", "$190.20", man_of_steelVIDdesc,man_of_steel,man_of_steelVIDID);



        allRetreivedMovies = helper.getall();

        myadapter myadapter = new myadapter(this, allRetreivedMovies, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);
    }

    public byte[] img_to_byte(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, bos);
        byte[] img = bos.toByteArray();

        return img;
    }

    @Override
    public void onItemclick(int position) {

        Intent intent = new Intent(MainActivity.this, detailPage.class);

        intent.putExtra("movie_name", allRetreivedMovies.get(position).getName());
        intent.putExtra("movies_description" , allRetreivedMovies.get(position).getDescription());
        intent.putExtra("movies_price", allRetreivedMovies.get(position).getPrice());
        intent.putExtra("vid_id", allRetreivedMovies.get(position).getVideo_id());
        intent.putExtra("poster", allRetreivedMovies.get(position).getPoster());

        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        //nothing happens
        databaseHelper helper = new databaseHelper(MainActivity.this);
        Toast.makeText(this, "Hi", Toast.LENGTH_SHORT).show();

    }




}