/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.acessos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author levycosta
 */
public class MapeadorAcesso {
	private HashMap<Integer, Acesso> cacheAcessos = new HashMap<>();
	private final String fileName = "Acessos.BATATA";

	public MapeadorAcesso() {
		load();
	}

	public void put(Acesso acesso) {
		int idAcesso = 0;
		if (cacheAcessos.size() > 0) {
			Object key = cacheAcessos.size() - 1;
			idAcesso = cacheAcessos.get(key).getIdAcesso();
		}
		acesso.setIdAcesso(idAcesso + 1);
		cacheAcessos.put(acesso.getIdAcesso(), acesso);
		persist();
	}

	/**
	 * Pega um acesso específico baseado no id passado.
	 * 
	 * @param idAcesso
	 * @return
	 */
	public Acesso get(Integer idAcesso) {
		return cacheAcessos.get(idAcesso);
	}

	/**
	 * 
	 * @return Coleção dos acessos.
	 */
	public Collection<Acesso> getList() {
		load();
		return cacheAcessos.values();
	}

	/**
	 * 
	 * @return Hash inteiro.
	 */

	public HashMap<Integer, Acesso> getHash() {
		return cacheAcessos;
	}

	/**
	 * Remove um acesso.
	 * 
	 * @param acesso
	 */
	public void remove(Acesso acesso) {
		cacheAcessos.remove(acesso.getIdAcesso());

		persist();
	}

	public void persist() {
		try {
			FileOutputStream fOS = new FileOutputStream(fileName);
			ObjectOutputStream oOS = new ObjectOutputStream(fOS);

			oOS.writeObject(cacheAcessos);

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

			this.cacheAcessos = (HashMap<Integer, Acesso>) oIS.readObject();

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
