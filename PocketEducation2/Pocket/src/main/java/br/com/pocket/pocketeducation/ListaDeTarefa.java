package br.com.pocket.pocketeducation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.pocket.pocketeducation.dao.AlunoDAO;
import br.com.pocket.pocketeducation.modelo.Aluno;

public class ListaDeTarefa extends AppCompatActivity {
    private ListView listaDeTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_tarefa);
    }

}
