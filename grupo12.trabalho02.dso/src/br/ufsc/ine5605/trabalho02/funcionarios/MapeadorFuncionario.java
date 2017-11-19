/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.funcionarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

import br.ufsc.ine5605.trabalho02.acessos.Acesso;

/**
 *
 * @author rak_w
 */
public class MapeadorFuncionario {
	private HashMap<Integer, Funcionario> cacheFuncionario = new HashMap<>();
	private final String fileName = "Funcionarios.BATATA";

	public MapeadorFuncionario() {
			load();
		}

	public void put(Funcionario funcionario) {

		cacheFuncionario.put(funcionario.getMatricula(), funcionario);
		persist();
	}

	/**
	 * Pega um funcionario específico baseado na matricula passada.
	 * 
	 * @param matricula
	 * @return Funcionario encontrado
	 */
	public Funcionario get(Integer matricula) {
		return cacheFuncionario.get(matricula);
	}

	/**
	 * 
	 * @return Coleção dos funcionarios.
	 */
	public Collection<Funcionario> getList() {
		load();
		return cacheFuncionario.values();
	}

	/**
	 * 
	 * @return Hash inteiro.
	 */

	public HashMap<Integer, Funcionario> getHash() {
		return cacheFuncionario;
	}

	/**
	 * Remove um acesso.
	 * 
	 * @param acesso
	 */
	public void remove(Funcionario funcionario) {
		cacheFuncionario.remove(funcionario.getMatricula());
		persist();
	}

	public void persist() {
		try {
			FileOutputStream fOS = new FileOutputStream(fileName);
			ObjectOutputStream oOS = new ObjectOutputStream(fOS);

			oOS.writeObject(cacheFuncionario);

			oOS.flush();
			fOS.flush();

			oOS.close();
			fOS.close();

		} catch (Exception e) {
			System.out.println(e);
			persist();
		}
	}

	public void load() {
		try {
			FileInputStream fIS = new FileInputStream(fileName);
			ObjectInputStream oIS = new ObjectInputStream(fIS);

			this.cacheFuncionario = (HashMap<Integer, Funcionario>) oIS.readObject();

			oIS.close();
			fIS.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);

		} catch (FileNotFoundException e) {
			System.out.println(e);
			persist();

		} catch (IOException e) {
			System.out.println(e);
			persist();
		}

	}
}
