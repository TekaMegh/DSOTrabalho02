/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rak_w
 */
public class TelaCadastroCargo extends JFrame{
    
    private JLabel lbNome;
    private JLabel lbCodigo;
    private JLabel lbAcesso;
    private JButton btSalvar;
    private JButton btCancelar;
    private JTextField tfNome;
    private JTextField tfCodigo;
    private JComboBox cbAcesso;
    private String[] acesso = {"Sem acesso", "Comercial", "Gerencial", "Especial"};
    
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
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets(5,5,5,5);
        
        //Configuracao JLabel Nome
        constraint.gridheight = 1;
        constraint.gridwidth = 3;
        constraint.gridx = 1;
        constraint.gridy = 1;
        lbNome = new JLabel("Nome:");               
        container.add(lbNome, constraint);
        
        //Configuracao JLabel Codigo
        constraint.gridx = 1;
        constraint.gridy = 2;
        lbCodigo = new JLabel("Codigo:");               
        container.add(lbCodigo, constraint);
        
        //Configuracao JLabel Acesso
        constraint.gridx = 1;
        constraint.gridy = 3;
        lbAcesso = new JLabel("Acesso:");               
        container.add(lbAcesso, constraint);
        
        //Configuracao JTextField Nome
        constraint.gridx = 4;
        constraint.gridy = 1;
        tfNome = new JTextField(10);               
        container.add(tfNome, constraint);
        
        //Configuracao JTextField Codigo
        constraint.gridx = 4;
        constraint.gridy = 2;
        tfCodigo = new JTextField(10);               
        container.add(tfCodigo, constraint);
        
        //Configuracao JComoboBox Acesso
        constraint.gridx = 4;
        constraint.gridy = 3;
        cbAcesso = new JComboBox(acesso);
        container.add(cbAcesso, constraint);
        
        //Configuracao JLabel Intervalo
        constraint.gridx = 1;
        constraint.gridy = 4;
        lbIntervalo = new JLabel("Intervalo(s) de Acesso:");               
        container.add(lbIntervalo, constraint);
                
        //Configuracao JTable Intervalo
        tbIntervalo = new JTable();
        tbIntervalo.setPreferredScrollableViewportSize(new Dimension(200, 100));
        tbIntervalo.setFillsViewportHeight(true);
        constraint.gridheight = 4;
        constraint.gridwidth = 6;
        constraint.gridx = 1;
        constraint.gridy = 5;
        DefaultTableModel modelTbIntervalo = new DefaultTableModel();
        modelTbIntervalo.addColumn("Inicio");
        modelTbIntervalo.addColumn("Fim");
        tbIntervalo.setModel(modelTbIntervalo);
        container.add(tbIntervalo, constraint);
        
        
        //Configuracao JLabel Codigo
        constraint.gridheight = 1;
        constraint.gridwidth = 3;
        constraint.gridx = 1;
        constraint.gridy = 8;
        lbInicio = new JLabel("Inicio:");               
        container.add(lbInicio, constraint);
        
        //Configuracao JLabel Codigo
        constraint.gridx = 1;
        constraint.gridy = 9;
        lbCodigo = new JLabel("Codigo:");               
        container.add(lbCodigo, constraint);
        
        //Configuracao JButton Salvar
        constraint.gridx = 1;
        constraint.gridy = 11;
        btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(gerenciadorTelaCadastroCargo);
        container.add(btSalvar, constraint);
        
        //Configuracao JButton Cancelar
        constraint.gridx = 4;
        constraint.gridy = 11;
        btCancelar = new JButton("Cancelar");
        btCancelar.addActionListener(gerenciadorTelaCadastroCargo);
        container.add(btCancelar, constraint);
        
        setSize(300, 350);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    
    private class GerenciadorTelaCadastroCargo implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
             if(e.getSource().equals(btCancelar)) {
                ControladorCargo.getInstance().iniciaTelaCargo();
            } else if(e.getSource().equals(btSalvar)) {
                ControladorCargo.getInstance().iniciaTelaCargo();
                JOptionPane.showMessageDialog(null, "Cargo salvo!", "Cargo", 1);
               //ControladorCargo.getInstance().incluiCargo();
            }
        }
    }
}
