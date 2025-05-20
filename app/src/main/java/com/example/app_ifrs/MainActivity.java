package com.example.app_ifrs;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.carousel.CarouselLayoutManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    Carousel carousel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView textDate = findViewById(R.id.textViewDate);
        Locale localeBR = new Locale("pt", "BR");
        SimpleDateFormat formatador = new SimpleDateFormat("EEEE, HH:mm", localeBR);

        formatador.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

        String result = formatador.format(new Date());


        result = result.substring(0, 1).toUpperCase() + result.substring(1);

        textDate.setText(result);

        //---------carrosel----------

        RecyclerView recyclerView = findViewById(R.id.recycler);
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("https://images.unsplash.com/photo-1747599309107-20504ba6b8dd?q=80&w=2076&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        arrayList.add("https://images.unsplash.com/photo-1747607174999-0ca07c1ef75a?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxmZWF0dXJlZC1waG90b3MtZmVlZHw3fHx8ZW58MHx8fHx8");
        arrayList.add("https://images.unsplash.com/photo-1747396379098-714c21bde6f7?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxmZWF0dXJlZC1waG90b3MtZmVlZHw4fHx8ZW58MHx8fHx8");
        arrayList.add("https://plus.unsplash.com/premium_photo-1747504296849-d477136528ed?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxmZWF0dXJlZC1waG90b3MtZmVlZHw5fHx8ZW58MHx8fHx8");
        arrayList.add("https://images.unsplash.com/photo-1747515203898-2df8f083f417?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxmZWF0dXJlZC1waG90b3MtZmVlZHwxNnx8fGVufDB8fHx8fA%3D%3D");

        ImageAdapter adapter  = new ImageAdapter(MainActivity.this, arrayList);
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener(){
            @Override
            public void onClick(ImageView imageView, String url) {
                startActivity(new Intent(MainActivity.this, ImageViewActivity.class).putExtra("image", url),
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, imageView, "image").toBundle());
            }
        });

        recyclerView.setAdapter(adapter);
    }
}