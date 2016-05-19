package br.com.pocket.pocketeducation.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.pocket.pocketeducation.modelo.Aluno;

/**
 * Created by daniel on 19/05/16.
 */
public class AlunoConverter {

    public String converteParaJSON(List<Aluno> alunos) {
        JSONStringer js = new JSONStringer();
        try {
            js.object().key("list").array().object().key("aluno").array();
            for(Aluno aluno : alunos) {
                js.object();
                js.key("nome").value(aluno.getNome());
                js.key("tarefa").value(aluno.getTarefa());
                js.endObject();
            }
            js.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  js.toString();
    }
}
