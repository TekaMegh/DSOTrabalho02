/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.funcionarios;

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
 * @author carcaroff
 */
public class TelaCadastroFuncionario extends JFrame{
    
    private JLabel lbNome;
    private JLabel lbMatricula;
    private JLabel lbDataNascimento;
    private JLabel lbCargo;
    private JLabel lbSalario;
    private JLabel lbTelefone;
    
    
    private JTextField tfNome;
    private JTextField tfMatricula;
    private JTextField tfDataNascimento;
    private JTextField tfCargo;
    private JTextField tfSalario;
    private JTextField tfTelefone;
    
    private JButton btSave;
    private JButton btCancel;
    private JButton btAdd;
    
    private GerenciadorBotoes btManager;
    
    public TelaCadastroFuncionario() {
        super("Funcionário");
        this.btManager = new GerenciadorBotoes();
        this.configuraTela();
    }

    private void configuraTela() {
        
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        
        //Configuracao JLabel Nome
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        lbNome = new JLabel("Nome:");
        container.add(lbNome, constraints);
        
        //Configuracao JLabel Matricula
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 2;
        lbMatricula = new JLabel("Matrícula:");               
        container.add(lbMatricula, constraints);
        
        //Configuracao JLabel Data de Nascimento
        constraints.gridx = 1;
        constraints.gridy = 3;
        lbDataNascimento = new JLabel("Data de Nascimento:");               
        container.add(lbDataNascimento, constraints);
        
        //Configuracao JLabel Cargo
        constraints.gridx = 1;
        constraints.gridy = 4;
        lbCargo = new JLabel("Cargo:");               
        container.add(lbCargo, constraints);
        
        //Configuracao JLabel Salario
        constraints.gridx = 1;
        constraints.gridy = 5;
        lbSalario = new JLabel("Salário:");
        container.add(lbSalario, constraints);
        
        //Configuracao JLabel Data de Nascimento
        constraints.gridx = 1;
        constraints.gridy = 6;
        lbTelefone = new JLabel("Telefone:");
        container.add(lbTelefone, constraints);
        
        //Configuracao JTextField Nome
        constraints.gridheight = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 2;
        constraints.gridy = 1;
        tfNome = new JTextField(10);               
        container.add(tfNome, constraints);
        
        //Configuracao JTextField Matricula
        constraints.gridheight = 1;
        constraints.gridwidth = 3;
        constraints.gridx = 2;
        constraints.gridy = 2;
        tfMatricula = new JTextField(10);               
        container.add(tfMatricula, constraints);
        
        setSize(700, 550);
        
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}