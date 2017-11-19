/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

/**
 *
 * @author rak_w
 */
public interface IControladorCargo {

    public void iniciaTelaCadastroCargo();

    public void iniciaTelaCargo();

    public void iniciaTelaPrincipal();

    public Iterable<Cargo> getListaCargos();

    public void removeCargoByIdentifier(Object valueAt);
       
}
