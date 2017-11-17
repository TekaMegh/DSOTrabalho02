/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02;

import java.sql.Date;

import br.ufsc.ine5605.trabalho02.acessos.MapeadorAcesso;
import br.ufsc.ine5605.trabalho02.cargos.Cargo;
import br.ufsc.ine5605.trabalho02.funcionarios.ControladorFuncionario;
import br.ufsc.ine5605.trabalho02.funcionarios.TelaFuncionario;

/**
 *
 * @author rak_w
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Cargo cargo1 = new Cargo(123, "badeco", true);
		cargo1.setGerencial(false);

		cargo1.addIntervalo("08:00", "12:00");
		cargo1.addIntervalo("14:00", "18:00");

		MapeadorAcesso map = new MapeadorAcesso();
		map.getHash().clear();

		try {
			ControladorFuncionario.getInstance().incluiFuncionario("levy", new Date(0), "(85)8548-8526", 5000, cargo1);
		} catch (Exception e) {
			TelaFuncionario.printExceptionMessage(e);
		}

	}

}
