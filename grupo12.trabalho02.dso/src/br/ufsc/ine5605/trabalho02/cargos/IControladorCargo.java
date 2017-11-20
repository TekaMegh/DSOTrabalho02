/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author rak_w
 */
public interface IControladorCargo {

    public void iniciaTelaCadastroCargo();

    public void iniciaTelaCargo();

    public void iniciaTelaPrincipal();

    public Collection<Cargo> getListaCargos();
    
    public ArrayList<String> getNomeCargos();
    
    public Cargo getCargo(String nome);

    public int getIdentificadorByCodigo(int codigo);

    public void removeCargoByCodigo(int parseInt);

    public void removeCargo(int parseInt);

    public Cargo getCargoByNome(String nome);
    
    public Cargo incluiCargo(String nome, boolean mayEnter, boolean gerencial);

    
}
