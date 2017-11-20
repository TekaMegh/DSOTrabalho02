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
    private TelaIntervalosDeAcesso telaIntervalosDeAcesso;
    private TelaCadastroIntervalosDeAcesso telaCadastroIntervalosDeAcesso;
    private MapeadorCargo mapCargo;
    private int numCodigo;

    /**
     * Método construtor da classe Inicializa os atributos da classe
     */
    public ControladorCargo() {
        this.telaCargo = new TelaCargo();
        this.telaCadastroCargo = new TelaCadastroCargo();
        this.mapCargo = new MapeadorCargo();
        this.telaIntervalosDeAcesso = new TelaIntervalosDeAcesso();
        this.telaCadastroIntervalosDeAcesso = new TelaCadastroIntervalosDeAcesso();
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
        this.telaCargo.updateData();
        this.telaCadastroIntervalosDeAcesso.setVisible(false);
        this.telaCadastroCargo.setVisible(false);
        this.telaIntervalosDeAcesso.setVisible(false);
        this.telaCargo.setVisible(true);
    }

    @Override
    public void iniciaTelaCadastroCargo() {
    	this.telaCadastroIntervalosDeAcesso.setVisible(false);
        this.telaCargo.setVisible(false);
        this.telaIntervalosDeAcesso.setVisible(false);
        this.telaCadastroCargo.setVisible(true);
    }

    @Override
	public void configuraTelaCadastroCargo(ArrayList<String> intervalos) {
    	
		this.telaCadastroCargo.updateData(intervalos);
		
	}

    @Override
    public void iniciaTelaIntervalosDeAcesso() {
    	this.telaIntervalosDeAcesso.setVisible(true);
    }

    @Override
	public void configuraTelaIntervaloDeAcesso(Cargo cargo) {
		this.telaIntervalosDeAcesso.updateData(cargo);
	}
    
    @Override
	public void iniciaTelaCadastroIntervalosDeAcesso() {
		this.telaCadastroIntervalosDeAcesso.setVisible(true);
	}
    	
@Override
    public void iniciaTelaPrincipal() {
        this.telaCargo.setVisible(false);
        this.telaCadastroCargo.setVisible(false);
        this.telaIntervalosDeAcesso.setVisible(false);
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
        for (Cargo cargo : this.mapCargo.getList()) {
            if (cargo.getNome().equals(nome)) {
                return cargo;
            }
        }
        return null;
    }

    /**
     *
     * @param nome
     * @param codigo
     * @param mayEnter
     * @param gerencial
     * @return Cargo Instancia um Cargo com codigo unico Adiciona o Cargo e
     * retorna o Cargo
     */
    @Override
    public void incluiCargo(String nome, int codigo, boolean mayEnter, boolean gerencial) {
        boolean codigoExiste;

        codigoExiste = this.hasCodigo(codigo);

        if (!codigoExiste) {
            Cargo cargo = new Cargo(this.numCodigo, nome, mayEnter, gerencial);
            this.numCodigo += 1;
            this.mapCargo.putCargo(cargo);
        }
    }

    @Override
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
    public void removeCargo(int codigo) {
        Cargo cargo = this.getCargoByCodigo(codigo);
        boolean hasFuncionario = ControladorPrincipal.getInstance().hasFuncionarioByCargo(cargo);
        if (!hasFuncionario) {
            this.mapCargo.removeCargo(codigo);
        }
        this.iniciaTelaCargo();
    }

    /**
     *
     * @param codigo
     * @param deHora
     * @param ateHora
     * @throws Exception
     */
    @Override
    public void setIntervaloInCargoByCodigo(int codigo, String deHora, String ateHora) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.addIntervalo(deHora, ateHora);
    }


    public void removeIntervalosByCodigo(int codigo, IntervaloDeAcesso intervalo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.removeIntervalo(intervalo);
    }

    public ArrayList<IntervaloDeAcesso> getIntervalosByCodigo(int codigo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        return cargo.getIntervalos();
    }

    public int parseInt(Object object) {
    	return Integer.parseInt(object.toString());
    }


	@Override
	public Cargo incluiCargo(String nome, boolean mayEnter, boolean gerencial) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
