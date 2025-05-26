package com.example.app_ifrs;

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

public class Transports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transports);
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
        }else if (id == R.id.menu_bolsas) {
            openOportunity();
            return true;
        }
        else if (id == R.id.menu_atividades) {
            openActivities();
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
    private void openIfrsSite() {
        NavigationUtils.openUrl(this, "https://ifrs.edu.br/rolante/");
    }

    private void openTransportsScreen(){
        NavigationUtils.openActivity(this, Transports.class);
    }
    private void openActivities() {
        NavigationUtils.openActivity(this, ComplementaryActivities .class);
    }
    private void openOportunity(){
        NavigationUtils.openActivity(this, Opportunities.class);
    }

    private void abrirProcessoSeletivo() {
        // Implemente a navegação para o processo seletivo
    }

    public void openMainScreen(View v){
        NavigationUtils.openActivity(this, MainActivity.class);
    }
    public void openCompany(View v){
        NavigationUtils.openActivity(this, TransportsCompanys.class);
    }
    public void openFreePass(View v){
        NavigationUtils.openActivity(this, FreePass.class);
    }
}