/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.funcionarios;

/**
 *
 * @author carcaroff
 */
import br.ufsc.ine5605.trabalho02.cargos.Cargo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public interface IControladorFuncionario {

    /**
     * Abre a tela de Funcionários e encaminha opção selecionada para o metodo
     * de switch.
     */
    public void inicia();

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
    public void incluiFuncionario(String nome, Date nascimento, String telefone, Double salario, Cargo cargo) throws Exception;

    /**
     * Exclui um funcionário através da matrícula.
     *
     * @param matricula
     * @throws Exception
     */
    public void removeFuncionario(int matricula) throws Exception;

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
    public void modificaFuncionario(int matricula, String nome, Date nascimento, String telefone, Double salario, Cargo cargo) throws Exception;

    public Collection<Funcionario> getListaFuncionario();

    /**
     * Percorre lista de funcionários checando a presença de funcionários com o
     * cargo informado.
     *
     * @param cargo
     * @return boolean indicando a presença ou não de funcionários com o cargo
     * informado.
     */
    public boolean hasFuncionarioByCargo(Cargo cargo);

    /**
     * Procura por funcionários associados ao cargo passado como parâmetro
     * adiciona numa lista, a qual será impressa na tela posteriormente.
     *
     * @param cargo
     */
    public void printFuncionarioByCargo(Cargo cargo);

	public void iniciaTelaCadastroFuncionario();

	public void iniciaTelaAlteraFuncionario(ArrayList<Object> values) throws ParseException;

	public void iniciaTelaPrincipal();

	public Funcionario findFuncionarioByMatricula(Integer matricula) throws Exception;

	public String[] listaCargos();
	
	public Cargo getCargoByNome(String nome);


}
