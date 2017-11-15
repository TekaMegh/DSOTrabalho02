/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author rak_w
 */
public class TelaCadastroCargo extends JFrame{
    
    private GerenciadorTelaCadastroCargo gerenciadorTelaCadastroCargo;
    
    public TelaCadastroCargo() {
        super("Cargo");
        this.gerenciadorTelaCadastroCargo = new GerenciadorTelaCadastroCargo();
        this.configuraTelaCadastroCargo();        
    }

    private void configuraTelaCadastroCargo() {

    }

    private class GerenciadorTelaCadastroCargo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
