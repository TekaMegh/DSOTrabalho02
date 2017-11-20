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
    
    public void iniciaTelaIntervalosDeAcesso();
    
    public void configuraTelaIntervaloDeAcesso(Cargo cargo);
    
    public Collection<Cargo> getListaCargos();
    
    public ArrayList<String> getNomeCargos();
        
    public void removeCargo(int parseInt);

    public Cargo getCargoByNome(String nome);
    
    public Cargo getCargoByCodigo(int codigo);

    public void incluiCargo(String nome, int codigo, boolean mayEnter, boolean gerencial);

    public void setIntervaloInCargoByCodigo(int parseInt, String string, String string0) throws Exception;
    
    public Cargo incluiCargo(String nome, boolean mayEnter, boolean gerencial);
    
    public int parseInt(Object selectedItem);

	public void iniciaTelaCadastroIntervalosDeAcesso();

	public void configuraTelaCadastroCargo(ArrayList<String> intervalos);

	


    
}
