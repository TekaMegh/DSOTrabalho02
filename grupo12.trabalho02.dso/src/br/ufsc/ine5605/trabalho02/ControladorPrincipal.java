/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02;

import br.ufsc.ine5605.trabalho02.acessos.ControladorAcesso;
import br.ufsc.ine5605.trabalho02.acessos.IControladorAcesso;
import br.ufsc.ine5605.trabalho02.cargos.ControladorCargo;
import br.ufsc.ine5605.trabalho02.cargos.IControladorCargo;
import br.ufsc.ine5605.trabalho02.funcionarios.ControladorFuncionario;
import br.ufsc.ine5605.trabalho02.funcionarios.IControladorFuncionario;

/**
 *
 * @author rak_w
 */
public class ControladorPrincipal {
    
    private static ControladorPrincipal controladorPrincipal;
    private TelaPrincipal telaPrincipal;
    private IControladorCargo ctrlCargo;
    private IControladorFuncionario ctrlFuncionario;
    private IControladorAcesso ctrlAcesso;
    
    private ControladorPrincipal() {
        this.telaPrincipal = new TelaPrincipal();
        this.ctrlCargo = ControladorCargo.getInstance();
        ctrlFuncionario = ControladorFuncionario.getInstance();
        ctrlAcesso = ControladorAcesso.getInstance();
    }
    
    /**
     * Retorna o controlador Principal.
     *
     * @return ControladorPrincipal
     */
    public static ControladorPrincipal getInstance() {
        if (controladorPrincipal == null) {
            controladorPrincipal = new ControladorPrincipal();
        }
        return controladorPrincipal;
    }

    public void inicia() {
        this.telaPrincipal.setVisible(true);    
    }

    public void iniciaCargo() {
        this.telaPrincipal.setVisible(false);
        ControladorCargo.getInstance().iniciaTelaCargo();
    }
}