/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.acessos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author levycosta
 */
public class Acesso implements Serializable {

	private int idAcesso;
	private TipoAcesso tipo;
	private int matricula;
	private Date horadeacesso;
	private Date dataDaTentativa;

	public Acesso(TipoAcesso tipo, int matricula, Date horadeacesso) {
		this.tipo = tipo;
		this.matricula = matricula;
		this.horadeacesso = horadeacesso;
		this.dataDaTentativa = Calendar.getInstance().getTime();
	}

	public TipoAcesso getTipo() {
		return tipo;
	}

	public void setTipo(TipoAcesso tipo) {
		this.tipo = tipo;
	}

	public int getMatricula() {
		return matricula;
	}

	public Date getHoraDeAcesso() {
		return horadeacesso;
	}

	public Date getDataDaTentativa() {
		return dataDaTentativa;
	}

	public int getIdAcesso() {
		return idAcesso;
	}

	public void setIdAcesso(int idAcesso) {
		this.idAcesso = idAcesso;
	}
}