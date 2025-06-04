package com.example.app_ifrs;

import static com.example.app_ifrs.R.id.recycler;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_ifrs.adapters.ImageAdapter;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.Arrays;
import java.util.List;

import Helpers.NavigationUtils;

public class ComplementaryActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_complementary_activities);
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
        RecyclerView recyclerView = findViewById(recycler);
        recyclerView.setLayoutManager(new CarouselLayoutManager());

        // Lista de IDs de recursos das imagens
        List<Integer> localImages = Arrays.asList(
                R.drawable.atvd1,
                R.drawable.atvd2,
                R.drawable.atvd3,
                R.drawable.atvd4,
                R.drawable.atvd5
        );

        ImageAdapter adapter  = new ImageAdapter(this, localImages);
        adapter.setOnItemClickListener((imageView, resId) -> {
            Intent intent = new Intent(this, ImageViewActivity.class);
            intent.putExtra("image_res", resId);

            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, imageView, "image");

            startActivity(intent, options.toBundle());
        });

        recyclerView.setAdapter(adapter);
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
        }else if (id == R.id.menu_localizacao) {
            openTransportsScreen();
        }else if (id == R.id.menu_bolsas) {
            openOprtunity();
            return true;
        }
        else if (id == R.id.menu_atividades) {
            openAtctivities();
            return true;
        }else if (id == R.id.menu_principal) {
            openMainScreen();
            return true;
        }else if (id == R.id.menu_sobre) {
            openDeveloperTeam();
            return true;
        }else if (id == R.id.menu_obras) {
            openContructionInProgress();
            return true;
        }else if (id == R.id.menu_prova) {
            openExamGuide();
            return true;
        }else if (id == R.id.menu_assistencia) {
            openStudentAssistence();
            return true;
        }


// Outros itens do menu...
        return super.onOptionsItemSelected(item);
    }

    private void openCoursesScreen() {
        NavigationUtils.openActivity(this, ModalitiesOffered.class);
    }
    private void openStudentAssistence() {
        NavigationUtils.openActivity(this, StudentAssistence.class);
    }
    private void openExamGuide() {
        NavigationUtils.openActivity(this, ExamGuide.class);
    }
    private void openContructionInProgress() {
        NavigationUtils.openActivity(this, ConstructionInProgress.class);
    }
    private void openDeveloperTeam() {
        NavigationUtils.openActivity(this, DeveloperTeam.class);
    }
    private void openIfrsSite() {
        NavigationUtils.openUrl(this, "https://ifrs.edu.br/rolante/");
    }

    private void openTransportsScreen(){
        NavigationUtils.openActivity(this, Transports.class);
    }
    private void openAtctivities() {
        NavigationUtils.openActivity(this, ComplementaryActivities.class);
    }
    private void openOprtunity(){
        NavigationUtils.openActivity(this, Opportunities.class);
    }
    private void openMainScreen(){
        NavigationUtils.openActivity(this, MainActivity.class);
    }

    private void openSelectionProcess() {
        NavigationUtils.openActivity(this, SelectionProcess.class);
    }
    public void openTechnicalVisits(View v){
        NavigationUtils.openActivity(this, TechnicalVisits.class);
    }
    public void openPraticSports(View v){
        NavigationUtils.openActivity(this, PraticSports.class);
    }
}