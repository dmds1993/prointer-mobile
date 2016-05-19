package br.com.pocket.pocketeducation;

import br.com.pocket.pocketeducation.modelo.Aluno;
import android.widget.EditText;
/**
 * Created by daniel on 15/05/16.
 */
public class FormularioHelper {
    private final EditText campoNome;
    private final EditText campoTarefa;
    private final EditText campoTelefone;
    private final EditText campoEmail;
    private Aluno aluno;

    public  FormularioHelper(Formulario activity)  {
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoTarefa = (EditText) activity.findViewById(R.id.formulario_tarefa);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
        aluno = new Aluno();
    }

    public Aluno pegaAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setTarefa(campoTarefa.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        return aluno;
    }

    public void preencheFormulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoTarefa.setText(aluno.getTarefa());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
        this.aluno = aluno;
    }
}
