/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.acessos;

import java.io.Serializable;

/**
 *
 * @author rak_w
 */
public enum TipoAcesso implements Serializable {
    
    SEMMATRICULA("Nao existe funcionario com essa matricula.", "Sem Matricula"),
    NAOPOSSUIACESSO("Esse funcionario nao possui acesso.", "Nao possui acesso"),
    HORARIONAOPERMITIDO("Funcionario fora do horario autorizado para acesso.", "Horario nao permitido"),
    ACESSOBLOQUEADO("Acesso bloqueado.", "Acesso bloqueado"),
    AUTORIZADO("Acesso autorizado.", "Acesso Autorizado");

    private String descricao;
    private String toString;

    TipoAcesso(String descricaoTipo) {
        descricao = descricaoTipo;
    }
    
    TipoAcesso(String descricaoTipo, String toString){
    	this.descricao = descricaoTipo;
    	this.toString = toString;
    }

    public String descricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
    	return toString;
    }
}
