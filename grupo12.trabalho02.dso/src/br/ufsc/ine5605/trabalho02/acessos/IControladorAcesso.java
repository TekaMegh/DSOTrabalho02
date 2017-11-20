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

	public void iniciaTelaAcesso();

	public void iniciaTelaEntrada();

	public String validaAcesso(Object matriculaParam, Object tempoParam);

	public ArrayList<Acesso> getAcessosByTipo(TipoAcesso tipo);

	public ArrayList<Acesso> getAcessosByMatricula(int matricula);

	public Collection<Acesso> getAcessos();
	
	public Date parseDate(Object object) throws ParseException;

	public String formatToHour(Date horaDeAcesso);

	public String formatToDate(Date dataDaTentativa);

	public ArrayList<Integer> getMatriculasFuncionarios();

	public int parseInt(Object selectedItem);
	
	public void removeAcessoByMatricula(int matricula);

}
