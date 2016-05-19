package br.com.pocket.pocketeducation.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.pocket.pocketeducation.modelo.Aluno;
import br.com.pocket.pocketeducation.modelo.Tarefas;

/**
 * Created by daniel on 15/05/16.
 */
public class AlunoDAO extends SQLiteOpenHelper{

    public AlunoDAO(Context context) {
        super(context, "CentralAlunos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =" CREATE TABLE Alunos (id INTEGER PRIMARY KEY," +
                " nome TEXT NOT NULL, " +
                " tarefa TEXT, " +
                " email TEXT, " +
                " telefone TEXT);";
        db.execSQL(sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getContentValues(aluno);
        db.insert("Alunos", null, dados);
    }

    @NonNull
    private ContentValues getContentValues(Aluno aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("tarefa", aluno.getTarefa());
        dados.put("email", aluno.getEmail());
        dados.put("telefone", aluno.getTelefone());
        return dados;
    }

    public List<Aluno> buscaAlunos() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM Alunos;", null);

        List<Aluno> alunos = new ArrayList<Aluno>();

        while (cs.moveToNext()) {
            Aluno aluno = new Aluno();
            aluno.setId(cs.getLong(cs.getColumnIndex("id")));
            aluno.setNome(cs.getString(cs.getColumnIndex("nome")));
            aluno.setEmail(cs.getString(cs.getColumnIndex("email")));
            aluno.setTelefone(cs.getString(cs.getColumnIndex("telefone")));
            aluno.setTarefa(cs.getString(cs.getColumnIndex("tarefa")));

            alunos.add(aluno);
        }
        cs.close();
        return alunos;
    }

    public List<Tarefas> tarefas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM Alunos;", null);

        List<Tarefas> tarefas = new ArrayList<Tarefas>();

        while (cs.moveToNext()) {
            Tarefas tarefa = new Tarefas();
            tarefa.setId(cs.getLong(cs.getColumnIndex("id")));
            tarefa.setNome(cs.getString(cs.getColumnIndex("nome")));
            tarefa.setEmail(cs.getString(cs.getColumnIndex("email")));
            tarefa.setTelefone(cs.getString(cs.getColumnIndex("telefone")));
            tarefa.setTarefa(cs.getString(cs.getColumnIndex("tarefa")));

            tarefas.add(tarefa);
        }
        cs.close();
        return tarefas;
    }

    public void onDelete(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        String [] params = {aluno.getId().toString()};
        db.delete("Alunos", "id = ?", params );

    }

    public void altera(Aluno aluno) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = getContentValues(aluno);
        String [] params = {aluno.getId().toString()};
        db.update("Alunos", values, "id = ?", params);
    }
}
