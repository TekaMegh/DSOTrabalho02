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
        
    public void removeCargo(int parseInt);

    public Cargo getCargoByNome(String nome);

    public void incluiCargo(String nome, int codigo, boolean b, boolean b0);

    public void setIntervaloInCargoByCodigo(int parseInt, String string, String string0) throws Exception;

    

    

    
}
