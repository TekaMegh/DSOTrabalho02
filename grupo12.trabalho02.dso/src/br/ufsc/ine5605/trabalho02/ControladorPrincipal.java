/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02;

import java.util.Collection;

import br.ufsc.ine5605.trabalho02.acessos.ControladorAcesso;
import br.ufsc.ine5605.trabalho02.acessos.IControladorAcesso;
import br.ufsc.ine5605.trabalho02.cargos.Cargo;
import br.ufsc.ine5605.trabalho02.cargos.ControladorCargo;
import br.ufsc.ine5605.trabalho02.cargos.IControladorCargo;
import br.ufsc.ine5605.trabalho02.funcionarios.ControladorFuncionario;
import br.ufsc.ine5605.trabalho02.funcionarios.Funcionario;
import br.ufsc.ine5605.trabalho02.funcionarios.IControladorFuncionario;
import java.util.ArrayList;

/**
 *
 * @author rak_w
 */
public class ControladorPrincipal {

	private static ControladorPrincipal controladorPrincipal;
	private TelaPrincipal telaPrincipal;
	
	private ControladorPrincipal() {
		this.telaPrincipal = new TelaPrincipal();
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

	/**
	 * Abre menu principal
	 */
	public void inicia() {
		this.telaPrincipal.setVisible(true);
	}

	/**
	 * Abre menu de Cargos
	 */
	public void iniciaCargo() {
		this.telaPrincipal.setVisible(false);
		ControladorCargo.getInstance().iniciaTelaCargo();
	}

	/**
	 * Abre menu de Acessos
	 */
	public void iniciaAcesso() {
		this.telaPrincipal.setVisible(false);
		ControladorAcesso.getInstance().iniciaTelaAcesso();
	}

	/**
	 * Abre menu de Entrada
	 */
	public void iniciaEntrada() {
		this.telaPrincipal.setVisible(false);
		ControladorAcesso.getInstance().iniciaTelaEntrada();
	}

	/**
	 * Abre menu de Funcionários
	 */
	public void iniciaFuncionario() {
		this.telaPrincipal.setVisible(false);
		ControladorFuncionario.getInstance().inicia();

	}
        
        public ArrayList<String> getNomeCargos() {
            return ControladorCargo.getInstance().getNomeCargos();
        }
        
        public Cargo getCargoByNome(String nome) {
            return ControladorCargo.getInstance().getCargoByNome(nome);
        }

	public Collection<Funcionario> getListaFuncionarios() {
		return ControladorFuncionario.getInstance().getListaFuncionario();
	}

	/**
	 * Percorre lista de funcionarios cadastrados até encontrar um com a matricula
	 * dada.
	 *
	 *
	 * @param matricula
	 * @return funcionario encontrado caso a matrícula exista;
	 * @return null caso não haja funcionario com a mesma matricula;
	 */
	public Funcionario getFuncionarioByMatricula(Integer matricula) {
		Funcionario funcionario = null;
		try{
			funcionario = ControladorFuncionario.getInstance().findFuncionarioByMatricula(matricula);
		} catch (Exception e) {
			
		}
		return funcionario;
	}

	public void chooseCargo(){

		ControladorCargo.getInstance().iniciaTelaCargo();

	}

	/**
	 * Percorre lista de funcionarios e verifica a existência de algum que possui o
	 * cargo passado como parâmetro.
	 * 
	 * @param cargo
	 * @return Funcionario encontrado.
	 */
	public boolean hasFuncionarioByCargo(Cargo cargo) {
		boolean hasFuncionario = ControladorFuncionario.getInstance().hasFuncionarioByCargo(cargo);
		if (hasFuncionario) {
			ControladorFuncionario.getInstance().printFuncionarioByCargo(cargo);
		}
		return hasFuncionario;
	}

	public boolean hasFuncionarioByMatricula(int matricula) {
		Funcionario funcionario = this.getFuncionarioByMatricula(matricula);
		if(funcionario == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void removeAcessosByMatricula(int matricula) {
		ControladorAcesso.getInstance().removeAcessosByMatricula(matricula);
	}
	
	
	
}