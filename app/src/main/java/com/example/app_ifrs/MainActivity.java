package com.example.app_ifrs;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.appbar.MaterialToolbar;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
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
        //-------menu--------
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Habilita o ícone de navegação (três riscos)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu); // Seu ícone
        }

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

        //---------links-----------

        FlexboxLayout container = findViewById(R.id.cities_container);

        // Mapa com as cidades e seus respectivos links
        Map<String, String> citiesWithUrls = new LinkedHashMap<String, String>() {{
            put("Alvorada", "https://www.alvorada.rs.gov.br");
            put("Bento Gonçalves", "https://www.bentogoncalves.rs.gov.br");
            put("Canoas", "https://www.canoas.rs.gov.br");
            put("Caxias do Sul", "https://www.caxias.rs.gov.br");
            put("Erechim", "https://www.erechim.rs.gov.br");
            put("Farroupilha", "https://www.farroupilha.rs.gov.br");
            put("Feliz", "https://www.feliz.rs.gov.br");
            put("Ibirubá", "https://www.ibiruba.rs.gov.br");
            put("Osório", "https://www.osorio.rs.gov.br");
            put("Porto Alegre", "https://www.portoalegre.rs.gov.br");
            put("Restinga", "https://www.restingaseca.rs.gov.br");
            put("Rio Grande", "https://www.riogrande.rs.gov.br");
            put("Rolante", "https://www.rolante.rs.gov.br");
            put("Sertão", "https://www.sertao.rs.gov.br");
            put("Vacaria", "https://www.vacaria.rs.gov.br");
            put("Veranópolis", "https://www.veranopolis.rs.gov.br");
            put("Viamão", "https://www.viamao.rs.gov.br");
            put("Gramado", "https://www.gramado.rs.gov.br");
            put("Zona Norte de Porto Alegre", "https://www.portoalegre.rs.gov.br/zona-norte");
        }};

        int index = 0;
        for (Map.Entry<String, String> entry : citiesWithUrls.entrySet()) {
            String city = entry.getKey();
            String url = entry.getValue();

            // Cria TextView para a cidade
            TextView cityView = new TextView(this);
            cityView.setBackgroundResource(R.drawable.city_link_background);
            cityView.setPadding(8, 4, 8, 4);
            cityView.setText(city);
            cityView.setTextColor(Color.parseColor("#FFFFFF"));
            cityView.setTextSize(14);
            cityView.setPaintFlags(cityView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

            // Configura clique com a URL específica
            cityView.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            });

            // Configura LayoutParams para Flexbox
            FlexboxLayout.LayoutParams params = new FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 16, 16); // right, bottom
            cityView.setLayoutParams(params);

            container.addView(cityView);

            // Adiciona separador "|" (exceto após o último item)
            if (index < citiesWithUrls.size() - 1) {
                TextView separator = new TextView(this);
                separator.setText("|");
                separator.setTextColor(Color.WHITE);

                FlexboxLayout.LayoutParams sepParams = new FlexboxLayout.LayoutParams(
                        FlexboxLayout.LayoutParams.WRAP_CONTENT,
                        FlexboxLayout.LayoutParams.WRAP_CONTENT
                );
                sepParams.setMargins(0, 0, 16, 16);
                separator.setLayoutParams(sepParams);

                container.addView(separator);
            }
            index++;
        }
    }

    //------menu-----
    private void setSupportActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            openOptionsMenu();
            return true;
        } else if (id == R.id.menu_cursos) {
            abrirTelaCursos();
            return true;
        } else if (id == R.id.menu_processo) {
            abrirProcessoSeletivo();
            return true;
        }
// Outros itens do menu...
        return super.onOptionsItemSelected(item);
    }

    private void abrirTelaCursos() {
        Intent intent = new Intent(MainActivity.this, ModalitiesOffered.class);
        startActivity(intent);
    }

    private void abrirProcessoSeletivo() {
        // Implemente a navegação para o processo seletivo
    }
}