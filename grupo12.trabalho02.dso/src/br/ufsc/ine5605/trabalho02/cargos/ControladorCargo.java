/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;
import java.util.Collection;

/**
 *
 * @author rak_w
 */
public class ControladorCargo implements IControladorCargo{
    
    private static ControladorCargo controladorCargo;
    private TelaCargo telaCargo;
    private TelaCadastroCargo telaCadastroCargo;
    private MapeadorCargo mapCargo;
    private int numCodigo;
    
    /**
     * Método construtor da classe
     * Inicializa os atributos da classe
     */    
    public ControladorCargo() {
        this.telaCargo = new TelaCargo();
        this.telaCadastroCargo = new TelaCadastroCargo();
        this.mapCargo = new MapeadorCargo();
        this.numCodigo = this.mapCargo.getList().size();
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
    public void iniciaTelaCargo() {
        this.telaCargo.updateDate();
        this.telaCadastroCargo.setVisible(false);
        this.telaCargo.setVisible(true);
    }

    @Override
    public void iniciaTelaCadastroCargo() {
        this.telaCargo.setVisible(false);
        this.telaCadastroCargo.setVisible(true);
    }

    @Override
    public void iniciaTelaPrincipal() {
        this.telaCargo.setVisible(false);
        this.telaCadastroCargo.setVisible(false);
        ControladorPrincipal.getInstance().inicia();
    }

    @Override
    public Collection<Cargo> getListaCargos() {
        return this.mapCargo.getList();
    }
    
    /**
     *
     * @param nome
     * @param mayEnter
     * @return Cargo
     * Instancia um Cargo com codigo unico
     * Adiciona o Cargo e retorna o Cargo
     */
    @Override
    public Cargo incluiCargo(String nome, boolean mayEnter, boolean gerencial) {
        boolean codigoExiste;
        do {
            codigoExiste = this.hasCodigo(numCodigo);
            if (codigoExiste) {
                this.numCodigo += 1;
            }
        } while (codigoExiste);
        Cargo cargo = new Cargo(this.numCodigo, nome, mayEnter, gerencial);
        this.numCodigo += 1;
        this.mapCargo.putCargo(cargo);
        return cargo;
    }
    
    /**
     * 
     * @param codigo
     * @return boolean
     * Verifica a existencia de um cargo com o codigo recebido
     * Retorna uma booleana indicando a existencia ou não do cargo
     */
    public boolean hasCodigo(int codigo) {
        //if (this.mapCargo.getCargo(codigo)) {
              
        
        return false;
    }
    
}
