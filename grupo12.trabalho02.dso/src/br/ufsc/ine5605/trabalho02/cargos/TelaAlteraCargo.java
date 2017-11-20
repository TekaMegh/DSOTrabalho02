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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rak_w
 */
public class TelaAlteraCargo extends JFrame {

    private JLabel lbNome;
    private JLabel lbCodigo;
    private JLabel lbAcesso;
    private JButton btSalvar;
    private JButton btCancelar;
    private JTextField tfNome;
    private JLabel lbCodigoCadastro;
    private JComboBox cbAcesso;
    private String[] acesso = {"Sem acesso", "Comercial", "Gerencial", "Especial"};
    private ArrayList<String> intervalos;
    private JTable tbIntervalo;
    private JScrollPane spItens;

    private SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");

    private GerenciadorTelaAlteraCargo gerenciadorTelaAlteraCargo;

    public TelaAlteraCargo() {
        super("Cargo");
        this.gerenciadorTelaAlteraCargo = new GerenciadorTelaAlteraCargo();
        this.configuraTelaAlteraCargo();
    }

    private void configuraTelaAlteraCargo() {

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets(5, 5, 5, 5);

        // Configuracao JLabel Nome
        constraint.gridheight = 1;
        constraint.gridwidth = 3;
        constraint.gridx = 1;
        constraint.gridy = 1;
        lbNome = new JLabel("Nome:");
        container.add(lbNome, constraint);

        // Configuracao JLabel Codigo
        constraint.gridx = 1;
        constraint.gridy = 2;
        lbCodigo = new JLabel("Codigo:");
        container.add(lbCodigo, constraint);

        // Configuracao JLabel Acesso
        constraint.gridx = 1;
        constraint.gridy = 3;
        lbAcesso = new JLabel("Acesso:");
        container.add(lbAcesso, constraint);

        // Configuracao JTextField Nome
        constraint.gridx = 4;
        constraint.gridy = 1;
        tfNome = new JTextField(10);
        container.add(tfNome, constraint);

        // Configuracao JLabel CodigoCadastro
        constraint.gridx = 4;
        constraint.gridy = 2;
        lbCodigoCadastro = new JLabel("");
        container.add(lbCodigoCadastro, constraint);

        // Configuracao JComboBox Acesso
        constraint.gridx = 4;
        constraint.gridy = 3;
        cbAcesso = new JComboBox(acesso);
        cbAcesso.addActionListener(gerenciadorTelaAlteraCargo);
        container.add(cbAcesso, constraint);

        // Configuracao JTable Intervalo
        tbIntervalo = new JTable();
        tbIntervalo.setPreferredScrollableViewportSize(new Dimension(200, 50));
        tbIntervalo.setFillsViewportHeight(true);
        constraint.gridheight = 4;
        constraint.gridwidth = 6;
        constraint.gridx = 1;
        constraint.gridy = 5;
        spItens = new JScrollPane(tbIntervalo);
        container.add(spItens, constraint);
        spItens.setVisible(false);

        // Configuracao JButton Salvar
        constraint.gridheight = 1;
        constraint.gridwidth = 3;
        constraint.gridx = 1;
        constraint.gridy = 11;
        btSalvar = new JButton("Salvar");
        btSalvar.addActionListener(gerenciadorTelaAlteraCargo);
        container.add(btSalvar, constraint);

        // Configuracao JButton Cancelar
        constraint.gridx = 4;
        constraint.gridy = 11;
        btCancelar = new JButton("Cancelar");
        btCancelar.addActionListener(gerenciadorTelaAlteraCargo);
        container.add(btCancelar, constraint);

        setSize(300, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void updateData(ArrayList<String> intervalos) {
        DefaultTableModel modelTbItens = new DefaultTableModel();
        this.intervalos = intervalos;
        modelTbItens.addColumn("Intervalo(s) de Acesso:");
        if (intervalos != null) {
            for (String intervalo : intervalos) {
                modelTbItens.addRow(new Object[]{intervalo});
            }
        }
        tbIntervalo.setModel(modelTbItens);
        spItens.setVisible(true);
        this.repaint();
    }

    public void updateData(String nome, int codigo, int index, ArrayList<String> intervalos) {
        tfNome.setText(nome);
        lbCodigoCadastro.setText(String.valueOf(codigo));
        cbAcesso.setSelectedIndex(index);
        if(index == 3) {
            this.updateData(intervalos);
        }
    }

    private class GerenciadorTelaAlteraCargo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btCancelar)) {
                ControladorCargo.getInstance().iniciaTelaCargo();
            }
            
        }

    }
}
