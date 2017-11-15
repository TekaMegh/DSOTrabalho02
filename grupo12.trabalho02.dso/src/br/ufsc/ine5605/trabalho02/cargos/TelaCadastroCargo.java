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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author rak_w
 */
public class TelaCadastroCargo extends JFrame{
    
    private JLabel lbNome;
    private JLabel lbCodigo;
    private JLabel lbTipo;
    private JButton btSalvar;
    private JButton btCancelar;
    private JTextField tfNome;
    private JTextField tfCodigo;
    private JComboBox cbTipo;
    
    private JLabel lbIntervalo;
    private JTable tbIntervalo;
    private JLabel lbInicio;
    private JLabel lbFim;
    private JTextField tfInicio;
    private JTextField tfFim;
    private JButton btAdd;
    private JButton btAlterar;
    private JButton btRemover;
    
    private GerenciadorTelaCadastroCargo gerenciadorTelaCadastroCargo;
    
    public TelaCadastroCargo() {
        super("Cargo");
        this.gerenciadorTelaCadastroCargo = new GerenciadorTelaCadastroCargo();
        this.configuraTelaCadastroCargo();        
    }

    private void configuraTelaCadastroCargo() {
        
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints construtor = new GridBagConstraints();
        construtor.fill = GridBagConstraints.BOTH;
        
        //Configuracao JLabel Nome
        construtor.gridheight = 1;
        construtor.gridwidth = 3;
        construtor.gridx = 1;
        construtor.gridy = 1;
        lbNome = new JLabel("Nome:");               
        container.add(lbNome, construtor);
        
        //Configuracao JLabel Codigo
        construtor.gridx = 1;
        construtor.gridy = 2;
        lbCodigo = new JLabel("Codigo:");               
        container.add(lbCodigo, construtor);
        
        //Configuracao JLabel Tipo
        construtor.gridx = 1;
        construtor.gridy = 3;
        lbTipo = new JLabel("Tipo:");               
        container.add(lbTipo, construtor);
        
        //Configuracao JTextField Nome
        construtor.gridx = 4;
        construtor.gridy = 1;
        tfNome = new JTextField(10);               
        container.add(tfNome, construtor);
        
        //Configuracao JTextField Codigo
        construtor.gridx = 4;
        construtor.gridy = 2;
        tfCodigo = new JTextField(10);               
        container.add(tfCodigo, construtor);
        
        setSize(450, 350);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private class GerenciadorTelaCadastroCargo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
