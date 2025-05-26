package com.example.app_ifrs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdmScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adm_screen);
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
            openCoursesScreen();
            return true;
        } else if (id == R.id.menu_processo) {
            abrirProcessoSeletivo();
            return true;
        }else if (id == R.id.menu_localizacao) {
            openTransportsScreen();
            return true;
        }else if (id == R.id.menu_bolsas) {
            openOprtunity();
            return true;
        }
        else if (id == R.id.menu_atividades) {
            openAtctivities();
            return true;
        }else if (id == R.id.menu_principal) {
            openIfrsSite();
            return true;
        }
// Outros itens do menu...
        return super.onOptionsItemSelected(item);
    }



    private void openCoursesScreen() {
        NavigationUtils.openActivity(this, ModalitiesOffered.class);
    }

    private void abrirProcessoSeletivo() {
        // Implemente a navegação para o processo seletivo
    }
    private void openIfrsSite() {
        NavigationUtils.openUrl(this, "https://ifrs.edu.br/rolante/");
    }

    public void openMainScreen(View v){
        NavigationUtils.openActivity(this, MainActivity.class);
    }

    public void openAdmSite(View v){
        NavigationUtils.openUrl(this, "https://ifrs.edu.br/rolante/ensino/curso-tecnico-em-administracao-integrado-ao-ensino-medio/ ");
    }

    public void openOuthersCourses(View v){
        NavigationUtils.openActivity(this, ListCourses.class);
    }
    private void openAtctivities() {
        NavigationUtils.openActivity(this, ComplementaryActivities.class);
    }
    private void openTransportsScreen(){
        NavigationUtils.openActivity(this, Transports.class);
    }
    private void openOprtunity(){
        NavigationUtils.openActivity(this, Opportunities.class);
    }

}