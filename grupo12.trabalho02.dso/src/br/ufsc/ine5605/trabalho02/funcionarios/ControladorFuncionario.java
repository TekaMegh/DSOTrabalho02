/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.funcionarios;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;
import br.ufsc.ine5605.trabalho02.cargos.Cargo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author carcaroff
 */
public class ControladorFuncionario implements IControladorFuncionario {

	private static ControladorFuncionario controladorFuncionario;
	private MapeadorFuncionario mapeadorFuncionario;
	private Integer numMatricula;
	private final TelaFuncionario telaFuncionario;
	private TelaCadastroFuncionario telaCadastroFuncionario;
	private TelaAlteraFuncionario telaAlteraFuncionario;

	public ControladorFuncionario() {
		mapeadorFuncionario = new MapeadorFuncionario();
		this.numMatricula = atualizaMatricula();
		telaFuncionario = new TelaFuncionario();
		telaCadastroFuncionario = new TelaCadastroFuncionario();
		telaAlteraFuncionario = new TelaAlteraFuncionario();
	}

	/**
	 * Abre a tela de Funcion�rios e encaminha op��o selecionada para o metodo de
	 * switch.
	 */
	@Override
	public void inicia() {
		telaFuncionario.updateData();
		telaFuncionario.setVisible(true);
		telaCadastroFuncionario.setVisible(false);
		telaAlteraFuncionario.setVisible(false);
	}

	/**
	 * O m�todo inclui o novo funcion�rio na lista(arraylist) de funcionarios.
	 *
	 * @param nome
	 * @param nascimento
	 * @param telefone
	 * @param salario
	 * @param cargo
	 * @throws Exception
	 */
	@Override
	public void incluiFuncionario(String nome, Date nascimento, String telefone, Double salario, Cargo cargo)
			throws Exception {
		Funcionario funcionario1 = new Funcionario(numMatricula, nome, nascimento, telefone, salario, cargo);
		mapeadorFuncionario.put(funcionario1);
		numMatricula += 1;
		// telaFuncionario.printCadastroSucesso();
	}

	/**
	 * Exclui um funcion�rio atrav�s da matr�cula.
	 *
	 * @param matricula
	 * @throws Exception
	 */
	@Override
	public void removeFuncionario(int matricula) throws Exception {
		if (matricula <= 0) {
			throw new IllegalArgumentException("Matr�cula n�o pode ter valor inferior a zero!");
		}
		 try {
		 mapeadorFuncionario.remove(findFuncionarioByMatricula(matricula));
		 ControladorPrincipal.getInstance().removeAcessoByMatricula(matricula);

		 } catch (Exception e) {

		 }
		inicia();
	}

	/**
	 * Modifica os dados do funcion�rio.
	 *
	 * @param matricula
	 * @param nome
	 * @param nascimento
	 * @param telefone
	 * @param salario
	 * @param cargo
	 * @throws Exception
	 */
	@Override
	public void modificaFuncionario(int matricula, String nome, Date nascimento, String telefone, Double salario,
			Cargo cargo) throws Exception {
		if (matricula <= 0) {
			throw new IllegalArgumentException("Matr�cula n�o pode ter valor inferior a zero!");
		}
		if (nome == null) {
			throw new NullPointerException("Nome n�o pode ter valor nulo!");
		}
		if (nascimento == null) {
			throw new NullPointerException("Data de nascimento n�o pode ter valor nulo!");
		}
		if (telefone == null) {
			throw new NullPointerException("Telefone n�o pode ter valor nulo!");
		}
		if (cargo == null) {
			throw new NullPointerException("Cargo n�o pode ter valor nulo!");
		}

		try {
			Funcionario funcionario = findFuncionarioByMatricula(matricula);
			funcionario.setNascimento(nascimento);
			funcionario.setCargo(cargo);
			funcionario.setSalario(salario);
			funcionario.setTelefone(telefone);
			funcionario.setNome(nome);

		} catch (Exception e) {

		}
		controladorFuncionario.inicia();
	}

	/**
	 * Procura e retorna o funcion�rio associado � matricula informada.
	 *
	 * @param matricula
	 * @return Funcion�rio associado � matricula informada.
	 * @throws Exception
	 */
	public Funcionario findFuncionarioByMatricula(Integer matricula) throws Exception {
		Funcionario funcionario = mapeadorFuncionario.get(matricula);
		if (funcionario != null) {
			return funcionario;
		}
		throw new RuntimeException("N�o existe o funcion�rio com a matr�cula informada");
	}

	@Override
	public Collection<Funcionario> getListaFuncionario() {
		return mapeadorFuncionario.getList();
	}

	/**
	 * Retorna a instancia do controlador Funcion�rio.
	 *
	 * @return Controlador Funcionario
	 */
	public static IControladorFuncionario getInstance() {
		if (controladorFuncionario == null) {
			controladorFuncionario = new ControladorFuncionario();
		}
		return controladorFuncionario;
	}

	/**
	 * Percorre lista de funcion�rios checando a presen�a de funcion�rios com o
	 * cargo informado.
	 *
	 * @param cargo
	 * @return boolean indicando a presen�a ou n�o de funcion�rios com o cargo
	 *         informado.
	 */
	@Override
	public boolean hasFuncionarioByCargo(Cargo cargo) {
		for (Funcionario funcionario : mapeadorFuncionario.getList()) {
			if (funcionario.getCargo().equals(cargo)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Procura por funcion�rios associados ao cargo passado como par�metro
	 * adiciona numa lista, a qual ser� impressa na tela posteriormente.
	 *
	 * @param cargo
	 */
	@Override
	public void printFuncionarioByCargo(Cargo cargo) {
		Collection<Funcionario> colecao = mapeadorFuncionario.getList();
		for (Funcionario funcionario : colecao) {
			if (funcionario.getCargo().equals(cargo)) {
				mapeadorFuncionario.put(funcionario);
			}
		}
		// telaFuncionario.printLista(colecao);
	}

	@Override
	public void iniciaTelaCadastroFuncionario() {
		telaCadastroFuncionario.updateComboBox(this.listaCargos());
		telaFuncionario.setVisible(false);
		telaCadastroFuncionario.setVisible(true);
		telaAlteraFuncionario.setVisible(false);
	}

	@Override
	public void iniciaTelaAlteraFuncionario(ArrayList<Object> values) {
		telaAlteraFuncionario.setValues(values);
		telaAlteraFuncionario.updateComboBox(this.listaCargos());
		telaFuncionario.setVisible(false);
		telaCadastroFuncionario.setVisible(false);
		telaAlteraFuncionario.setVisible(true);

	}

	@Override
	public void iniciaTelaPrincipal() {
		telaFuncionario.setVisible(false);
		ControladorPrincipal.getInstance().inicia();
	}
	
	public String[] listaCargos() {
		ArrayList<String> arrayCargos = ControladorPrincipal.getInstance().getNomeCargos();
		String[] listaCargos = new String[arrayCargos.size()];
		listaCargos = arrayCargos.toArray(listaCargos);
		return listaCargos;
		
	}
	
	public Cargo getCargoByNome(String nome) {
		Cargo cargo = ControladorPrincipal.getInstance().getCargoByNome(nome);
		return cargo;
	}
	
	public Integer atualizaMatricula() {
		Collection<Integer> keySets = mapeadorFuncionario.keySets();
		Integer maxValue = 0;
		for (Integer keys : keySets) {
			if(keys > maxValue) {
				maxValue = keys;
			}
		}
		maxValue++;
		return maxValue;
	}
}
