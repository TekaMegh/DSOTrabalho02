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
	 * Abre a tela de Funcionários e encaminha opção selecionada para o metodo de
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
	 * O método inclui o novo funcionário na lista(arraylist) de funcionarios.
	 *
	 * @param nome
	 * @param nascimento
	 * @param telefone
	 * @param salario
	 * @param cargo
	 * @throws Exception
	 */
	@Override
	public void incluiFuncionario(String nome, Date nascimento, String telefone, Double salario, Cargo cargo) throws Exception {
		Funcionario funcionario1 = new Funcionario(numMatricula, nome, nascimento, telefone, salario, cargo);
		mapeadorFuncionario.put(funcionario1);
		numMatricula += 1;
		// telaFuncionario.printCadastroSucesso();
	}

	/**
	 * Exclui um funcionário através da matrícula.
	 *
	 * @param matricula
	 * @throws Exception
	 */
	@Override
	public void removeFuncionario(int matricula) throws Exception {
		if (matricula <= 0) {
			throw new IllegalArgumentException("Matrícula não pode ter valor inferior a zero!");
		}
		 try {
		 mapeadorFuncionario.remove(findFuncionarioByMatricula(matricula));

		 } catch (Exception e) {

		 }
		ControladorFuncionario.getInstance().inicia();
	}

	/**
	 * Modifica os dados do funcionário.
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
			throw new IllegalArgumentException("Matrícula não pode ter valor inferior a zero!");
		}
		if (nome == null) {
			throw new NullPointerException("Nome não pode ter valor nulo!");
		}
		if (nascimento == null) {
			throw new NullPointerException("Data de nascimento não pode ter valor nulo!");
		}
		if (telefone == null) {
			throw new NullPointerException("Telefone não pode ter valor nulo!");
		}
		if (cargo == null) {
			throw new NullPointerException("Cargo não pode ter valor nulo!");
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
	 * Procura e retorna o funcionário associado à matricula informada.
	 *
	 * @param matricula
	 * @return Funcionário associado à matricula informada.
	 * @throws Exception
	 */
	public Funcionario findFuncionarioByMatricula(Integer matricula) throws Exception {
		Funcionario funcionario = mapeadorFuncionario.get(matricula);
		if (funcionario != null) {
			return funcionario;
		}
		throw new RuntimeException("Não existe o funcionário com a matrícula informada");
	}

	@Override
	public Collection<Funcionario> getListaFuncionario() {
		return mapeadorFuncionario.getList();
	}

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

	/**
	 * Percorre lista de funcionários checando a presença de funcionários com o
	 * cargo informado.
	 *
	 * @param cargo
	 * @return boolean indicando a presença ou não de funcionários com o cargo
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
	 * Procura por funcionários associados ao cargo passado como parâmetro
	 * adiciona numa lista, a qual será impressa na tela posteriormente.
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
		telaFuncionario.setVisible(false);
		telaCadastroFuncionario.setVisible(true);
		telaAlteraFuncionario.setVisible(false);
	}

	@Override
	public void iniciaTelaAlteraFuncionario() {
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
