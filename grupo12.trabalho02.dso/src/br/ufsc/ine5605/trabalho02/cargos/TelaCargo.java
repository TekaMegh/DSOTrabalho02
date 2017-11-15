/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author rak_w
 */
public class TelaCargo extends JFrame{
    private JLabel lbTexto;
    private JTable tbCargos;
    private JButton btNovo;
    private JButton btAlterar;
    private JButton btRemover;
    private JButton btVoltar;
    private GerenciadorTelaCargo gerenciadorTelaCargo;
    
    public TelaCargo() {
        super("Menu de Cargos");
        this.gerenciadorTelaCargo = new GerenciadorTelaCargo();
        this.configuraTelaCargo();        
    }

    private void configuraTelaCargo() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints construtor = new GridBagConstraints();
        construtor.fill = GridBagConstraints.BOTH;
                
        //Configuracao JLabel
        construtor.gridheight = 1;
        construtor.gridwidth = 3;
        construtor.gridx = 1;
        construtor.gridy = 1;
        lbTexto = new JLabel("Cargos cadastrados:");               
        container.add(lbTexto, construtor);
        
        //Configuracao JTable de Cargos
        
        //Configuracao JButton Novo
        construtor.gridx = 1;
        construtor.gridy = 3;
        btNovo = new JButton("Novo Cargo");               
        btNovo.addActionListener(gerenciadorTelaCargo);
        container.add(btNovo, construtor);
        
        //Configuracao JButton Alterar
        construtor.gridx = 4;
        construtor.gridy = 3;
        btAlterar = new JButton("Alterar Cargo");               
        btAlterar.addActionListener(gerenciadorTelaCargo);
        container.add(btAlterar, construtor);
        
        //Configuracao JButton Remover
        construtor.gridx = 7;
        construtor.gridy = 3;
        btRemover = new JButton("Remover Cargo");               
        btRemover.addActionListener(gerenciadorTelaCargo);
        container.add(btRemover, construtor);
        
        //Configuracao JButton Voltar
        construtor.gridx = 4;
        construtor.gridy = 4;
        btVoltar = new JButton("Voltar");               
        btVoltar.addActionListener(gerenciadorTelaCargo);
        container.add(btVoltar, construtor);
        
        setSize(450, 350);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private class GerenciadorTelaCargo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(btNovo)) {
               ControladorCargo.getInstance().iniciaTelaCadastroCargo();
            } else if(e.getSource().equals(btAlterar)) {
               ControladorCargo.getInstance().iniciaTelaCadastroCargo();
            } else if(e.getSource().equals(btRemover)) {
               JOptionPane.showMessageDialog(null, "O cargo foi removido", "Cargo", 1);
            } else if(e.getSource().equals(btVoltar)) {
                ControladorCargo.getInstance().iniciaTelaPrincipal();
            }
        }
    }
}
