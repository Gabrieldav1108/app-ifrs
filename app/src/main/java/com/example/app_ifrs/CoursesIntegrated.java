package com.example.app_ifrs;

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

import Helpers.NavigationUtils;

public class CoursesIntegrated extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_courses_integrated);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    // Habilita o ícone de navegação (três riscos)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu); // Seu ícone
        }

    }
    //-------menu--------
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
            openModalitiesScreen();
            return true;
        } else if (id == R.id.menu_processo) {
            openSelectionProcess();
            return true;
        }else if (id == R.id.menu_localizacao) {
            openTransportsScreen();
            return true;
        }else if (id == R.id.menu_atividades) {
            openActivities();
            return true;
        }else if (id == R.id.menu_bolsas) {
            openOportunity();
            return true;
        }else if (id == R.id.menu_principal) {
            openMainScreen();
            return true;
        }else if (id == R.id.menu_sobre) {
            openDeveloperTeam();
            return true;
        }
// Outros itens do menu...
        return super.onOptionsItemSelected(item);
    }

    private void openModalitiesScreen() {
        NavigationUtils.openActivity(this, ModalitiesOffered.class);
    }
    private void openDeveloperTeam() {
        NavigationUtils.openActivity(this, DeveloperTeam.class);
    }
    private void openSelectionProcess() {
        NavigationUtils.openActivity(this, SelectionProcess.class);
    }
    private void openIfrsSite() {
        NavigationUtils.openUrl(this, "https://ifrs.edu.br/rolante/");
    }
    private void openActivities() {
        NavigationUtils.openActivity(this, ComplementaryActivities.class);
    }
    private void openTransportsScreen(){
        NavigationUtils.openActivity(this, Transports.class);
    }
    private void openOportunity(){
        NavigationUtils.openActivity(this, Opportunities.class);
    }
    private void openMainScreen(){
        NavigationUtils.openActivity(this, MainActivity.class);
    }

    public void openAdmScreen(View v){
        v.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction(() -> {
                    v.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start();
                    NavigationUtils.openActivity(this, AdmScreen.class);
                })
                .start();
    }

    public void openAgroScreen(View v){
        v.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction(() -> {
                    v.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start();
                    NavigationUtils.openActivity(this, AgroScreen.class);
                })
                .start();
    }

    public void openInfoScreen(View v){
        v.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction(() -> {
                    v.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start();
                    NavigationUtils.openActivity(this, InfoScreen.class);
                })
                .start();
    }
    public void openOuthersCourses(View v){
        v.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction(() -> {
                    v.animate()
                            .scaleX(1f)
                            .scaleY(1f)
                            .setDuration(100)
                            .start();
                    NavigationUtils.openActivity(this, ListCourses.class);
                })
                .start();
    }

}