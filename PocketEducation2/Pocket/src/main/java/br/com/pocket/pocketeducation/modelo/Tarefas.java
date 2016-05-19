package br.com.pocket.pocketeducation.modelo;

import java.io.Serializable;

/**
 * Created by daniel on 19/05/16.
 */
public class Tarefas implements Serializable {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String tarefa;

    @Override
    public String toString() {
        return "Aluno - " + getNome() + " Tarefa -"+ getTarefa();
    }
}
