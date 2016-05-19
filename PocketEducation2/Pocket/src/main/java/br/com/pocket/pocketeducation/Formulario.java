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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.pocket.pocketeducation.dao.AlunoDAO;
import br.com.pocket.pocketeducation.modelo.Aluno;


public class Formulario extends AppCompatActivity {

    private FormularioHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        helper = new FormularioHelper (this);

        if (aluno != null) {
            helper.preencheFormulario(aluno);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegaAluno();
                AlunoDAO alunoDao = new AlunoDAO(this);
                if (aluno.getId() != null) {
                    alunoDao.altera(aluno);
                    Toast.makeText(this, "Aluno alterado"+aluno.getNome()+"com sucesso", Toast.LENGTH_SHORT).show();
                    alunoDao.close();
                } else {
                    alunoDao.insere(aluno);
                    Toast.makeText(this, "Aluno cadastrado"+aluno.getNome()+"com sucesso", Toast.LENGTH_SHORT).show();
                    alunoDao.close();
                }
                finish();
            break;

            case R.id.menu_formulario_notificacao:
                Intent intentVaiParaNotificacao = new Intent(Formulario.this, Notificacao.class);
                startActivity(intentVaiParaNotificacao);
            break;

            case R.id.historico_atividades:
                Intent intentVaiParaHistorico = new Intent(Formulario.this, Historico.class);
                startActivity(intentVaiParaHistorico);
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
