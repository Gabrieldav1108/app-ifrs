package com.example.app_ifrs;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;

import com.example.app_ifrs.adapters.ImageAdapter;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
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
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        }


        //Date
        TextView textDate = findViewById(R.id.textViewDate);
        Locale localeBR = new Locale("pt", "BR");
        SimpleDateFormat formatador = new SimpleDateFormat("EEEE, HH:mm", localeBR);

        formatador.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));

        String result = formatador.format(new Date());


        result = result.substring(0, 1).toUpperCase() + result.substring(1);

        textDate.setText(result);

        //---------carrosel----------

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new CarouselLayoutManager());

        // Lista de IDs de recursos das imagens
        List<Integer> localImages = Arrays.asList(
                R.drawable.logo_ifrs,
                R.drawable.logo_ifrs,
                R.drawable.logo_ifrs,
                R.drawable.logo_ifrs
        );

        ImageAdapter adapter  = new ImageAdapter(MainActivity.this, localImages);
        adapter.setOnItemClickListener((imageView, resId) -> {
            Intent intent = new Intent(MainActivity.this, ImageViewActivity.class);
            intent.putExtra("image_res", resId);

            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, imageView, "image");

            startActivity(intent, options.toBundle());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (menu instanceof MenuBuilder) {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            openOptionsMenu();
            return true;
        } else if (id == R.id.menu_cursos) {
            openCoursesScreen();
            return true;
        } else if (id == R.id.menu_processo) {
            openSelectionProcess();
            return true;
        }else if (id == R.id.menu_bolsas) {
            openOportunity();
            return true;
        }
        else if (id == R.id.menu_localizacao) {
            openTransportsScreen();
            return true;
        }
        else if (id == R.id.menu_atividades) {
            openAtctivities();
            return true;
        }else if (id == R.id.menu_principal) {
            openMainScreen();
            return true;
        }
// Outros itens do menu...
        return super.onOptionsItemSelected(item);
    }

    private void openCoursesScreen() {
        Intent intent = new Intent(MainActivity.this, ModalitiesOffered.class);
        startActivity(intent);
    }
    private void openIfrsSite() {
        NavigationUtils.openUrl(this, "https://ifrs.edu.br/rolante/");
    }
    private void openSelectionProcess() {
        NavigationUtils.openActivity(this, SelectionProcess.class);
    }
    private void openMainScreen(){
        NavigationUtils.openActivity(this, MainActivity.class);
    }
    private void openAtctivities() {
        NavigationUtils.openActivity(this, ComplementaryActivities.class);
    }
    private void openTransportsScreen(){
        NavigationUtils.openActivity(this, Transports.class);
    }
    private void openOportunity(){
        NavigationUtils.openActivity(this, Opportunities.class);
    }


}