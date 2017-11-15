/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

/**
 *
 * @author rak_w
 */
public class ControladorCargo implements IControladorCargo{
    
    private static ControladorCargo controladorCargo;
    private TelaCargo telaCargo;
    private TelaCadastroCargo telaCadastroCargo;
        
    public ControladorCargo() {
        this.telaCargo = new TelaCargo();
        this.telaCadastroCargo = new TelaCadastroCargo();
    }
    
    /**
     * 
     * @return IControladorCargo
     * Retorna a unica instancia do ControladorCargo como interface.
     */
    public static IControladorCargo getInstance() {
        if (controladorCargo == null) {
            controladorCargo = new ControladorCargo();
        }
        return controladorCargo;
    }

    @Override
    public void inicia() {
        this.telaCargo.setVisible(true);
    }

    @Override
    public void iniciaCadastro() {
        this.telaCargo.setVisible(false);
        this.telaCadastroCargo.setVisible(true);
    }
}