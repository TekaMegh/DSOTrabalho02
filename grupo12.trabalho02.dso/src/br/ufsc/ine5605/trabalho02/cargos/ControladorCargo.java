/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author rak_w
 */
public class ControladorCargo implements IControladorCargo {

    private static ControladorCargo controladorCargo;
    private TelaCargo telaCargo;
    private TelaCadastroCargo telaCadastroCargo;
    private MapeadorCargo mapCargo;
    private int numCodigo;

    /**
     * Método construtor da classe Inicializa os atributos da classe
     */
    public ControladorCargo() {
        this.telaCargo = new TelaCargo();
        this.telaCadastroCargo = new TelaCadastroCargo();
        this.mapCargo = new MapeadorCargo();
        this.numCodigo = this.mapCargo.getList().size();
    }

    /**
     *
     * @return IControladorCargo Retorna a unica instancia do ControladorCargo
     * como interface.
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

    public ArrayList<String> getNomeCargos() {
        ArrayList<String> nomes = new ArrayList<>();
        for (Cargo cargo : this.mapCargo.getList()) {
            String nome = cargo.getNome();
            nomes.add(nome);
        }
        return nomes;
    }

    public Cargo getCargoByNome(String nome) {
        for(Cargo cargo : this.mapCargo.getList()) {
            if(cargo.getNome().equals(nome)) {
                return cargo;
            }
        }
        return null;
    }

    /**
     *
     * @param nome
     * @param mayEnter
     * @param gerencial
     * @return Cargo Instancia um Cargo com codigo unico Adiciona o Cargo e
     * retorna o Cargo
     */
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

    @Override
    public void removeCargoByCodigo(int codigo) {
        int identificador = this.getIdentificadorByCodigo(codigo);
        Cargo cargo = this.getCargoByCodigo(codigo);
        boolean hasFuncionario = ControladorPrincipal.getInstance().hasFuncionarioByCargo(cargo);
        if (!hasFuncionario) {
            this.mapCargo.removeCargo(identificador);
        }
    }

    public Cargo getCargoByCodigo(int codigo) {
        for (Cargo cargo : this.mapCargo.getList()) {
            if (cargo.getCodigo() == codigo) {
                return cargo;
            }
        }
        return null;
    }

    /**
     *
     * @param codigo
     * @return boolean Verifica a existencia de um cargo com o codigo recebido
     * Retorna uma booleana indicando a existencia ou não do cargo
     */
    public boolean hasCodigo(int codigo) {
        for (Cargo cargo : getListaCargos()) {
            if (cargo.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getIdentificadorByCodigo(int codigo) {
        Cargo cargo = this.getCargoByCodigo(codigo);
        
    }

    @Override
    public void removeCargo(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
