/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.acessos;

import java.util.ArrayList;

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

	public void printListaAcessoByMatricula();
}
