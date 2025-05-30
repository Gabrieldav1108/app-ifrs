package com.example.app_ifrs;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.app_ifrs.adapters.CourseListAdapter;


public class ListCourses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_courses);
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

        ExpandableListView expandableListView = findViewById(R.id.expandableListView);

        // Prepare data groups
        List<String> groupList = new ArrayList<>();
        groupList.add("Cursos Técnicos Concomitantes/Subsequentes:\n");
        groupList.add("Cursos Superior:\n");
        groupList.add("PROEJA – Educação de Jovens e Adultos:\n");

        // Prepare child items
        HashMap<String, List<String>> itemMap = new HashMap<>();

        List<String> technicalCourses = new ArrayList<>();
        technicalCourses.add("Técnico em Administração Concomitante/Subsequente ao Ensino Médio");
        technicalCourses.add("Técnico em Agropecuária Concomitante/Subsequente ao Ensino Médio");
        technicalCourses.add("Técnico em Recursos Humanos Concomitante ao Ensino Médio");

        List<String> higherEducation = new ArrayList<>();
        higherEducation.add("Tecnologia em Processos Gerenciais");
        higherEducation.add("Tecnologia em Análise e Desenvolvimento de Sistemas");

        List<String> proejaCourses = new ArrayList<>();
        proejaCourses.add("Técnico em Comércio Integrado ao Ensino Médio – PROEJA");
        proejaCourses.add("Técnico em Administração Integrado ao Ensino Médio – EJA EPT");

        itemMap.put(groupList.get(0), technicalCourses);
        itemMap.put(groupList.get(1), higherEducation);
        itemMap.put(groupList.get(2), proejaCourses);

        // Create adapter
        CourseListAdapter adapter = new CourseListAdapter(this, groupList, itemMap);
        expandableListView.setAdapter(adapter);

        // Set click listener
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            TextView childItem = ((ViewGroup) v).findViewById(R.id.childItem);
            String selectedCourse = childItem.getText().toString();

            // Mapeamento direto para URLs
            Map<String, String> urlMap = new HashMap<>();
            urlMap.put("Técnico em Administração Concomitante/Subsequente ao Ensino Médio", "https://ifrs.edu.br/rolante/ensino/curso-tecnico-em-administracao/");
            urlMap.put("Técnico em Agropecuária Concomitante/Subsequente ao Ensino Médio", "https://ifrs.edu.br/rolante/ensino/curso-tecnico-em-agropecuaria/");
            urlMap.put("Técnico em Recursos Humanos Concomitante ao Ensino Médio", "https://ifrs.edu.br/rolante/12146-2/tecnico-em-recurso-humanos-concomitante-ao-ensino-medio/");
            urlMap.put("Tecnologia em Processos Gerenciais", "https://ifrs.edu.br/rolante/ensino/tpg/");
            urlMap.put("Tecnologia em Análise e Desenvolvimento de Sistemas", "https://ifrs.edu.br/rolante/ensino/tecnologia-em-analise-e-desenvolvimento-de-sistemas/");
            urlMap.put("Técnico em Comércio Integrado ao Ensino Médio – PROEJA", "https://ifrs.edu.br/rolante/ensino/%e2%80%8btecnico-em-%e2%80%8bcomercio-integrado-ao-ensino-medio/");
            urlMap.put("Técnico em Administração Integrado ao Ensino Médio – EJA EPT", "https://ifrs.edu.br/rolante/tecnico-em-administracao-integrado-ao-ensino-medio-educacao-de-jovens-e-adultos-eja-ept/");
            // Adicione todos os cursos

            // Extrai apenas o nome principal (antes da quebra de linha se houver)
            String courseKey = selectedCourse.split("\n")[0].trim();
            String url = urlMap.get(courseKey);

            if (url != null) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            } else {
                Toast.makeText(this, "Site não disponível para: " + courseKey, Toast.LENGTH_SHORT).show();
            }
            return true;
        });
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
        }
        else if (id == R.id.menu_localizacao) {
            openTransportsScreen();
            return true;
        }
        else if (id == R.id.menu_bolsas) {
            openOportunity();
            return true;
        }
        else if (id == R.id.menu_atividades) {
            openActivities();
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



    private void openCoursesScreen() {
        NavigationUtils.openActivity(this, ModalitiesOffered.class);
    }
    private void openIfrsSite() {
        NavigationUtils.openUrl(this, "https://ifrs.edu.br/rolante/");
    }
    private void openDeveloperTeam() {
        NavigationUtils.openActivity(this, DeveloperTeam.class);
    }

    private void openSelectionProcess() {
        NavigationUtils.openActivity(this, SelectionProcess.class);
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

}