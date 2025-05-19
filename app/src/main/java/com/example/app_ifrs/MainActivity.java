package com.example.app_ifrs;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

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
    }
}