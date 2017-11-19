/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.funcionarios;

/**
 *
 * @author carcaroff
 */

import br.ufsc.ine5605.trabalho02.cargos.Cargo;
import java.io.Serializable;
import java.util.Date;

public class Funcionario implements Serializable{
    
    private final Integer matricula;
    private String nome;
    private Date nascimento;
    private String telefone;
    private Integer salario;
    private Cargo cargo;
    private boolean blocked;
    
    public Funcionario(Integer matricula, String nome, Date nascimento, String telefone, Integer salario, Cargo cargo){
        this.matricula = matricula;
        this.nome = nome;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.salario = salario;
        this.cargo = cargo;
        blocked = false;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    
}
