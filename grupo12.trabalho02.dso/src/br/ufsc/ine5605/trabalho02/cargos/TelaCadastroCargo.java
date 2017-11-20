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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class TelaCadastroCargo extends JFrame {

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

    private GerenciadorTelaCadastroCargo gerenciadorTelaCadastroCargo;

    public TelaCadastroCargo() {
        super("Cadastro Cargo");
        this.gerenciadorTelaCadastroCargo = new GerenciadorTelaCadastroCargo();
        this.configuraTelaCadastroCargo();
    }

    private void configuraTelaCadastroCargo() {

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
        cbAcesso.addActionListener(gerenciadorTelaCadastroCargo);
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
        btSalvar.addActionListener(gerenciadorTelaCadastroCargo);
        container.add(btSalvar, constraint);

        // Configuracao JButton Cancelar
        constraint.gridx = 4;
        constraint.gridy = 11;
        btCancelar = new JButton("Cancelar");
        btCancelar.addActionListener(gerenciadorTelaCadastroCargo);
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

    public void novoCodigo(String numCodigo) {
        lbCodigoCadastro.setText(numCodigo);
    }

    private class GerenciadorTelaCadastroCargo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btCancelar)) {
                ControladorCargo.getInstance().iniciaTelaCargo();
            }
            if (e.getSource().equals(cbAcesso)) {
                if (cbAcesso.getSelectedItem().toString().equals("Especial")) {
                    ControladorCargo.getInstance().iniciaTelaCadastroIntervalosDeAcesso();
                }
            }
            if (e.getSource().equals(cbAcesso)) {
                if (cbAcesso.getSelectedItem().toString().equals("Sem acesso") ||
                        cbAcesso.getSelectedItem().toString().equals("Gerencial") ||
                                cbAcesso.getSelectedItem().toString().equals("Comercial")) {
                spItens.setVisible(false);
                }
            }

            if (e.getSource().equals(btSalvar)) {
                String nome = tfNome.getText();
                String Acesso = cbAcesso.getSelectedItem().toString();
                if (!nome.isEmpty()) {
                    if (Acesso.equals("Comercial")) {
                        ControladorCargo.getInstance().incluiCargo(nome, Integer.parseInt(lbCodigoCadastro.getText()), true, false);
                        try {
                            ControladorCargo.getInstance().setIntervaloInCargoByCodigo(Integer.parseInt(lbCodigoCadastro.getText()), "08:00",
                                    "12:00");
                            ControladorCargo.getInstance().setIntervaloInCargoByCodigo(Integer.parseInt(lbCodigoCadastro.getText()), "14:00",
                                    "18:00");
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    } else if (Acesso.equals("Sem acesso")) {
                        ControladorCargo.getInstance().incluiCargo(nome, Integer.parseInt(lbCodigoCadastro.getText()), false, false);
                    } else if (Acesso.equals("Gerencial")) {
                        ControladorCargo.getInstance().incluiCargo(nome, Integer.parseInt(lbCodigoCadastro.getText()), true, true);
                    } else if (Acesso.equals("Especial")) {

                        String HoraInicial = null;
                        String HoraFinal = null;
                        ControladorCargo.getInstance().incluiCargo(nome, Integer.parseInt(lbCodigoCadastro.getText()), true, false);
                        for (int index = 0; index < tbIntervalo.getRowCount(); index++) {
                            try {
                                HoraInicial = this.formatToHour(formatadorHora
                                        .parse((tbIntervalo.getValueAt(index, 0).toString().substring(0, 5))));
                                HoraFinal = this.formatToHour(
                                        formatadorHora.parse((tbIntervalo.getValueAt(index, 0).toString().substring(8))));
                            } catch (ParseException e1) {
                                e1.printStackTrace();
                            }
                            try {
                                ControladorCargo.getInstance().setIntervaloInCargoByCodigo(Integer.parseInt(lbCodigoCadastro.getText()),
                                        HoraInicial, HoraFinal);
                                ControladorCargo.getInstance().setIntervaloInCargoByCodigo(Integer.parseInt(lbCodigoCadastro.getText()),
                                        HoraInicial, HoraFinal);
                            } catch (NumberFormatException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            } catch (Exception e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Cadastro Cargo", 1);
                }
                if (!nome.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Cargo Cadastrado!", "Cargo", 1);
                }
            }
        }

        public String formatToHour(Date horarioInicial) {
            SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
            return formatadorHora.format(horarioInicial);

        }

    }
}
