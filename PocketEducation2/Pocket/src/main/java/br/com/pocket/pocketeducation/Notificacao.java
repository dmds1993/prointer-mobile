package br.com.pocket.pocketeducation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.pocket.pocketeducation.dao.AlunoDAO;
import br.com.pocket.pocketeducation.modelo.Aluno;
import br.com.pocket.pocketeducation.modelo.Tarefas;

public class Notificacao extends AppCompatActivity {
    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);
        listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        tarefas();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_notificacao, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.enviar_tarefas:
                new EnviaTarefasTask(this).execute();
                break;

            case R.id.lista_alunos:
                Intent intentVaiParaListaDeAlunos = new Intent(Notificacao.this, PocketEducation.class);
                startActivity(intentVaiParaListaDeAlunos);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void tarefas() {
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Tarefas> tarefas = alunoDAO.tarefas();
        alunoDAO.close();


        ArrayAdapter<Tarefas> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarefas);
        listaAlunos.setAdapter(adapter);
    }
}
