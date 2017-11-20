/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02;

import java.sql.Date;

import br.ufsc.ine5605.trabalho02.acessos.MapeadorAcesso;
import br.ufsc.ine5605.trabalho02.cargos.Cargo;
import br.ufsc.ine5605.trabalho02.cargos.ControladorCargo;
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
		
		ControladorCargo.getInstance().incluiCargo("badeco", 1, true , false);
		
		ControladorCargo.getInstance().getCargoByNome("badeco").addIntervalo("08:00", "12:00");
		ControladorCargo.getInstance().getCargoByNome("badeco").addIntervalo("14:00", "18:00");


		try {
			ControladorFuncionario.getInstance().incluiFuncionario("levy", new Date(0), "(85)8548-8526", 5000.00, ControladorCargo.getInstance().getCargoByNome("badeco"));
			ControladorFuncionario.getInstance().incluiFuncionario("joao", new Date(0), "(85)8548-8526", 5000.00, ControladorCargo.getInstance().getCargoByNome("badeco"));
			ControladorFuncionario.getInstance().incluiFuncionario("ronaldo", new Date(0), "(85)8548-8526", 5000.00, ControladorCargo.getInstance().getCargoByNome("badeco"));
		} catch (Exception e) {

			e.printStackTrace();

		}

		ControladorPrincipal.getInstance().inicia();
		
		ControladorPrincipal.getInstance().getNomeCargos();
	}

}
