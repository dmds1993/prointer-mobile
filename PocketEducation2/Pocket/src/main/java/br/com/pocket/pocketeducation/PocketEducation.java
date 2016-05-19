package br.com.pocket.pocketeducation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.pocket.pocketeducation.converter.AlunoConverter;
import br.com.pocket.pocketeducation.dao.AlunoDAO;
import br.com.pocket.pocketeducation.modelo.Aluno;


public class PocketEducation extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocket_education);

        Button novoALuno = (Button) findViewById(R.id.lista_alunos_cadastrar);
        novoALuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFormulario = new Intent(PocketEducation.this, Formulario.class);
                startActivity(intentFormulario);

            }
        });

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View intem, int position, long id) {
               Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
                Intent vaiParaOformulario = new Intent(PocketEducation.this, Formulario.class);
                vaiParaOformulario.putExtra("aluno", aluno);
                startActivity(vaiParaOformulario);
                Toast.makeText(PocketEducation.this, "Nome" + aluno.getNome() + "Clico", Toast.LENGTH_SHORT).show();

            }
        });
        registerForContextMenu(listaAlunos);
    }

    private void carregaLista() {
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Aluno> alunos = alunoDAO.buscaAlunos();
        alunoDAO.close();


        ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        listaAlunos.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_pocket_education, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);
                AlunoDAO alunoDAO = new AlunoDAO(PocketEducation.this);
                alunoDAO.onDelete(aluno);
                alunoDAO.close();
                Toast.makeText(PocketEducation.this, "Deletar aluno" + aluno.getNome(), Toast.LENGTH_SHORT).show();
                onResume();
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.enviar_tarefas:
                new EnviaTarefasTask(this).execute();
                break;

            case R.id.lista_alunos_notificacao:
                Intent intentVaiParaNoticacao = new Intent(PocketEducation.this, Notificacao.class);
                startActivity(intentVaiParaNoticacao);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }

}

