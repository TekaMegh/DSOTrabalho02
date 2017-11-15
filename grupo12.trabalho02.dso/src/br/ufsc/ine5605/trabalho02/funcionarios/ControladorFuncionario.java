/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.funcionarios;

/**
 *
 * @author rak_w
 */
public class ControladorFuncionario implements IControladorFuncionario {
    
    private static ControladorFuncionario controladorFuncionario;
    
    /**
     * Retorna a instancia do controlador Funcionário.
     *
     * @return Controlador Funcionario
     */
    public static IControladorFuncionario getInstance() {
        if (controladorFuncionario == null) {
            controladorFuncionario = new ControladorFuncionario();
        }
        return controladorFuncionario;
    }
}
