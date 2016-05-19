package br.com.pocket.pocketeducation;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.pocket.pocketeducation.converter.AlunoConverter;
import br.com.pocket.pocketeducation.dao.AlunoDAO;
import br.com.pocket.pocketeducation.modelo.Aluno;

/**
 * Created by daniel on 19/05/16.
 */
public class EnviaTarefasTask extends AsyncTask<Object, Object, String> {
    private Context context;
    private ProgressDialog dialog;
    public EnviaTarefasTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando alunos", true, true);
    }

    @Override
    protected String doInBackground(Object... params) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();
        AlunoConverter conversor = new  AlunoConverter();
        String json = conversor.converteParaJSON(alunos);

        WebClient client = new WebClient();
        client.post(json);
        String respota = client.post(json);
        return respota;
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context, resposta, Toast.LENGTH_SHORT).show();
        super.onPostExecute(resposta);
    }
}
