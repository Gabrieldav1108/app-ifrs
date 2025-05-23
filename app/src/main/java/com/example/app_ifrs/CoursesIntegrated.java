package com.example.app_ifrs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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
            abrirProcessoSeletivo();
            return true;
        }else if (id == R.id.menu_localizacao) {
            openTransportsScreen();
            return true;
        }
// Outros itens do menu...
        return super.onOptionsItemSelected(item);
    }

    private void openModalitiesScreen() {
        NavigationUtils.openActivity(this, ModalitiesOffered.class);
    }

    private void abrirProcessoSeletivo() {
        // Implemente a navegação para o processo seletivo
    }
    private void openAtctivitys() {
        NavigationUtils.openActivity(this, Transports.class);
    }
    private void openTransportsScreen(){
        NavigationUtils.openActivity(this, Transports.class);
    }
    private void openOprtunity(){
        NavigationUtils.openActivity(this, Transports.class);
    }
    public void openMainScreen(View v){
        NavigationUtils.openActivity(this, MainActivity.class);
    }

    public void openAdmScreen(View v){
        NavigationUtils.openActivity(this, AdmScreen.class);
    }

    public void openAgroScreen(View v){
        NavigationUtils.openActivity(this, AgroScreen.class);
    }

    public void openInfoScreen(View v){
        NavigationUtils.openActivity(this, InfoScreen.class);
    }
    public void openOuthersCourses(View v){
        NavigationUtils.openActivity(this, ListCourses.class);
    }

}