/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.acessos;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author levycosta
 */
public interface IControladorAcesso {
	/**
	 * Torna tela de Entrada invisível e invoca tela de Acesso
	 * 
	 */
	public void iniciaTelaAcesso();

	/**
	 * torna Tela de Acesso invisível e invoca tela de Entrada
	 */
	public void iniciaTelaEntrada();

	/**
	 * Faz a validacao do acesso baseado na matricula e hora de acesso recebida.
	 * 
	 * @param matricula
	 * @param horaDeAcesso
	 * @return String da descricao do tipo de Acesso
	 * @throws ParseException
	 *             se as variaveis n forem corretas
	 */
	public String validaAcesso(Object matriculaParam, Object tempoParam);

	public ArrayList<Acesso> getAcessosByTipo(TipoAcesso tipo);

	public ArrayList<Acesso> getAcessosByMatricula(int matricula);

	/**
	 * Retorna colecao de Acesso
	 */
	public Collection<Acesso> getAcessos();

	public Date parseDate(Object object) throws ParseException;

	/**
	 * Formata a Date recebida para uma String na forma de "HH:mm"
	 */
	public String formatToHour(Date horaDeAcesso);

	/**
	 * formata a Data recebida para uma String na forma de "dd/MM/yyyy"
	 */
	public String formatToDate(Date dataDaTentativa);

	public ArrayList<Integer> getMatriculasFuncionarios();

	/**
	 * Realiza um comando "parse" no Objeto recebido, o transformando em "int".
	 *
	 * @param Object
	 * @return int
	 * @throws NumberFormatException
	 */
	public int parseInt(Object selectedItem);

	public void removeAcessoByMatricula(int matricula);

}
