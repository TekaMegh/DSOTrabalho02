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
    
    SEMMATRICULA("Não existe funcionário com essa matrícula."),
    NAOPOSSUIACESSO("Esse funcionario nao possui acesso."),
    HORARIONAOPERMITIDO("Funcionario fora do horario autorizado para acesso."),
    ACESSOBLOQUEADO("Acesso bloqueado."),
    AUTORIZADO("Acesso autorizado.");

    private String descricao;

    TipoAcesso(String descricaoTipo) {
        descricao = descricaoTipo;
    }

    public String descricao() {
        return descricao;
    }
}
