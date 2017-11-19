/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

import java.util.ArrayList;

/**
 *
 * @author rak_w
 */
public interface IControladorCargo {

    public void iniciaTelaCadastroCargo();

    public void iniciaTelaCargo();

    public void iniciaTelaPrincipal();

    public Iterable<Cargo> getListaCargos();
    
    public ArrayList<String> getNomeCargos();
    
    public Cargo getCargo(int identificador);

    public void removeCargoByIdentifier(Object valueAt);
       
}
