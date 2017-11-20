/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.acessos;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;

import br.ufsc.ine5605.trabalho02.cargos.IntervaloDeAcesso;
import br.ufsc.ine5605.trabalho02.funcionarios.Funcionario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author levycosta
 */
public class ControladorAcesso implements IControladorAcesso {

	private MapeadorAcesso mapAcessos = new MapeadorAcesso();
	private SimpleDateFormat formatadorHora;
	private TelaEntrada telaEntrada;
	private TelaAcesso telaAcesso;

	private static IControladorAcesso controladorAcesso;

	public ControladorAcesso() {
		telaEntrada = new TelaEntrada();
		telaAcesso = new TelaAcesso();
		formatadorHora = new SimpleDateFormat("HH:mm");
	}

	/**
	 * Retorna a instancia do controlador Acesso.
	 *
	 * @return Controlador Acesso
	 */
	public static IControladorAcesso getInstance() {
		if (controladorAcesso == null) {
			controladorAcesso = new ControladorAcesso();
		}
		return controladorAcesso;
	}

	/**
	 * Faz a validacao do acesso baseado na matricula e hora de acesso recebida.
	 * 
	 * @param matricula
	 * @param horaDeAcesso
	 * @return String da descricao do tipo de Acesso
	 * @throws ParseException
	 *             se as variaveis n forem corretas
	 */
	@Override
	public String validaAcesso(Object matriculaParam, Object tempoParam) {

		try {
			int matricula = this.parseInt(matriculaParam);
			Date horaDeAcesso = this.parseDate(tempoParam);
			Funcionario funcionario = null;

			// testa se matrícula existe
			if (ControladorPrincipal.getInstance().hasFuncionarioByMatricula(matricula)) {
				funcionario = ControladorPrincipal.getInstance().getFuncionarioByMatricula(matricula);
			} else {
				return TipoAcesso.SEMMATRICULA.descricao();
			}

			// comeca testando se está bloqueado ou nao
			if (funcionario.isBlocked()) {
				mapAcessos.put(new Acesso(TipoAcesso.ACESSOBLOQUEADO, matricula, horaDeAcesso));
				return TipoAcesso.ACESSOBLOQUEADO.descricao();

			} else if (!funcionario.getCargo().mayEnter()) { // se nao, testa se pode entrar

				if (getAcessosByMatricula(funcionario.getMatricula()).size() > 2) {
					funcionario.setBlocked(true);
				}
				mapAcessos.put(new Acesso(TipoAcesso.NAOPOSSUIACESSO, matricula, horaDeAcesso));
				return TipoAcesso.NAOPOSSUIACESSO.descricao();

			} else if (funcionario.getCargo().isGerencial()) {

				return TipoAcesso.AUTORIZADO.descricao();

			} else if (validaHorario(funcionario, horaDeAcesso)) {

				return TipoAcesso.AUTORIZADO.descricao();

			} else {
				if (getAcessosByMatricula(funcionario.getMatricula()).size() > 2) {
					funcionario.setBlocked(true);
				}
				mapAcessos.put(new Acesso(TipoAcesso.HORARIONAOPERMITIDO, matricula, horaDeAcesso));
				return TipoAcesso.HORARIONAOPERMITIDO.descricao();
			}
		} catch (NumberFormatException e) {
			System.out.println(e);
			return "Matricula inválida";
		} catch (ParseException e) {
			System.out.println(e);
			return "Hora inválida";
		}

	}

	/**
	 * Realiza um comando "parse" no Objeto recebido, o transformando em "int".
	 *
	 * @param Object
	 * @return int
	 * @throws NumberFormatException
	 */
	public int parseInt(Object entrada) throws NumberFormatException {

		return Integer.parseInt(entrada.toString());

	}

	/**
	 * Realiza um comando "parse" no objeto recebido, o transformando em um "Date".
	 *
	 * @param Object
	 * @return Date do Object recebido
	 * @throws java.text.ParseException
	 */
	public Date parseDate(Object object) throws ParseException {

		String data = object.toString();
		System.out.println(data.substring(11, 16));
		Date date = formatadorHora.parse(data.substring(11, 16));
		return date;

	}

	/**
	 * Recebe Tipo de Acesso e retorna todas as tentativas já registradas;
	 *
	 * @param tipo
	 * @return lista de <Acesso>
	 */
	public ArrayList<Acesso> getAcessosByTipo(TipoAcesso tipo) {
		ArrayList<Acesso> listaAcessosByTipo = new ArrayList<>();
		for (Acesso acesso : mapAcessos.getList()) {
			if (acesso.getTipo() == tipo) {
				listaAcessosByTipo.add(acesso);
			}
		}
		return listaAcessosByTipo;
	}

	/**
	 * Recebe matrícula de funcionario e retorna suas tentativas de acesso já
	 * registradas;
	 *
	 * @param matricula
	 * @return lista de <Acesso>
	 */
	public ArrayList<Acesso> getAcessosByMatricula(int matricula) {

		ArrayList<Acesso> listaAcessosByMatricula = new ArrayList<>();
		for (Acesso acesso : mapAcessos.getHash().values()) {
			if (acesso.getMatricula() == matricula) {
				listaAcessosByMatricula.add(acesso);
			}
		}
		return listaAcessosByMatricula;
	}

	/**
	 * Testa se horario recebido está entre os intervalos de acesso permitidos de
	 * acordo com o cargo do funcionario recebido.
	 *
	 * @param funcionario
	 * @param horaAtual
	 * @return true caso o horário esteja dentro dos intervalos; false caso o
	 *         horário esteja fora dos intervalos
	 *
	 *
	 */
	public boolean validaHorario(Funcionario funcionario, Date horaAtual) {

		Date now, inicio, fim;
		ArrayList<IntervaloDeAcesso> intervalos = funcionario.getCargo().getIntervalos();
		now = horaAtual;
		for (IntervaloDeAcesso interv : intervalos) {

			inicio = interv.getHorarioInicial();
			fim = interv.getHorarioFinal();

			if (now.getTime() <= fim.getTime() && now.getTime() >= inicio.getTime()) {
				return true;
			}

		}
		return false;
	}

	/**
	 * Torna tela de Entrada invisível e invoca tela de Acesso
	 * 
	 */
	@Override
	public void iniciaTelaAcesso() {
		this.telaEntrada.setVisible(false);
		this.telaAcesso.mostraTela();
	}

	/**
	 * torna Tela de Acesso invisível e invoca tela de Entrada
	 */
	@Override
	public void iniciaTelaEntrada() {
		this.telaAcesso.setVisible(false);
		this.telaEntrada.mostrarTela();
	}

	/**
	 * Retorna colecao de Acesso
	 */
	@Override
	public Collection<Acesso> getAcessos() {
		return mapAcessos.getList();
	}

	/**
	 * Formata a Date recebida para uma String na forma de "HH:mm"
	 */
	@Override
	public String formatToHour(Date horaDeAcesso) {
		return formatadorHora.format(horaDeAcesso);

	}

	/**
	 * formata a Data recebida para uma String na forma de "dd/MM/yyyy"
	 */
	@Override
	public String formatToDate(Date dataDaTentativa) {
		DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
		return formatadorData.format(dataDaTentativa);
	}

	@Override
	public ArrayList<Integer> getMatriculasFuncionarios() {
		ArrayList<Integer> lista = new ArrayList<>();
		for (Funcionario funcionario : ControladorPrincipal.getInstance().getListaFuncionarios()) {

			lista.add(funcionario.getMatricula());

		}
		return lista;
	}

	@Override
	public void removeAcessoByMatricula(int matricula) {
		for (Acesso acesso : mapAcessos.getList()) {
			if (acesso.getMatricula() == matricula) {
				mapAcessos.remove(acesso);
			}
		}
	}

}
