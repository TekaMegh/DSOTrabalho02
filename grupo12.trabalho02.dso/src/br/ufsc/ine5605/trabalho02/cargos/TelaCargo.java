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
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
    private JScrollPane spCargos;
    private GerenciadorTelaCargo gerenciadorTelaCargo;
    
    public TelaCargo() {
        super("Menu de Cargos");
        this.gerenciadorTelaCargo = new GerenciadorTelaCargo();
        this.configuraTelaCargo();        
    }

    private void configuraTelaCargo() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets(5,5,5,5);
                
        //Configuracao JLabel
        constraint.gridheight = 1;
        constraint.gridwidth = 3;
        constraint.gridx = 1;
        constraint.gridy = 1;
        lbTexto = new JLabel("Cargos cadastrados:");               
        container.add(lbTexto, constraint);
        
        //Configuracao JTable de Cargos
        tbCargos = new JTable();
        tbCargos.setPreferredScrollableViewportSize(new Dimension(200, 100));
        tbCargos.setFillsViewportHeight(true);
        constraint.gridheight = 4;
        constraint.gridwidth = 9;
        constraint.gridx = 1;
        constraint.gridy = 2;
        spCargos = new JScrollPane(tbCargos);
        container.add(spCargos, constraint);
                
        //Configuracao JButton Novo
        constraint.gridheight = 1;
        constraint.gridwidth = 3;
        constraint.gridx = 1;
        constraint.gridy = 6;
        btNovo = new JButton("Novo Cargo");               
        btNovo.addActionListener(gerenciadorTelaCargo);
        container.add(btNovo, constraint);
        
        //Configuracao JButton Alterar
        constraint.gridx = 4;
        constraint.gridy = 6;
        btAlterar = new JButton("Alterar Cargo");               
        btAlterar.addActionListener(gerenciadorTelaCargo);
        container.add(btAlterar, constraint);
        
        //Configuracao JButton Remover
        constraint.gridx = 7;
        constraint.gridy = 6;
        btRemover = new JButton("Remover Cargo");               
        btRemover.addActionListener(gerenciadorTelaCargo);
        container.add(btRemover, constraint);
        
        //Configuracao JButton Voltar
        constraint.gridx = 4;
        constraint.gridy = 7;
        btVoltar = new JButton("Voltar");               
        btVoltar.addActionListener(gerenciadorTelaCargo);
        container.add(btVoltar, constraint);
                
        setSize(450, 350);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void updateDate(){
        DefaultTableModel modelTbCargos = new DefaultTableModel();
        modelTbCargos.addColumn("Codigo");
        modelTbCargos.addColumn("Nome");
        modelTbCargos.addColumn("Intervalos de Acesso");
          
        for(Cargo cargo : ControladorCargo.getInstance().getListaCargos()) {
            if(cargo.getIntervalos()==null && !cargo.isGerencial()) {
                modelTbCargos.addRow(new Object[]{cargo.getCodigo(), cargo.getNome(),"Sem Acesso"});
            } else if(cargo.getIntervalos()==null && cargo.isGerencial()) {
                modelTbCargos.addRow(new Object[]{cargo.getCodigo(), cargo.getNome(),"Acesso livre"});
            } else {
                int cont = 0;
                for(IntervaloDeAcesso intervalo : cargo.getIntervalos()) {
                    String horaInicial = formatToDate(intervalo.getHorarioInicial());
                    String horaFinal = formatToDate(intervalo.getHorarioFinal());
                    String horario = horaInicial + " a " + horaFinal;
                    if(cont==0) {
                        modelTbCargos.addRow(new Object[]{cargo.getCodigo(), cargo.getNome(), horario});
                    } else {
                        modelTbCargos.addRow(new Object[]{"", "", horario});
                    }
                    cont++;
                }
            }
            
        }
        
        tbCargos.setModel(modelTbCargos);
        
        tbCargos.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbCargos.getColumnModel().getColumn(1).setPreferredWidth(60);
        tbCargos.getColumnModel().getColumn(2).setPreferredWidth(110);
        
        this.repaint();
    }

    private String formatToDate(Date horarioInicial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class GerenciadorTelaCargo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(btNovo)) {
               ControladorCargo.getInstance().iniciaTelaCadastroCargo();
            } else if(e.getSource().equals(btAlterar)) {
               ControladorCargo.getInstance().iniciaTelaCadastroCargo();
            } else if(e.getSource().equals(btRemover)) {
                Object valueAt = tbCargos.getValueAt(tbCargos.getSelectedRow(), 0);
                ControladorCargo.getInstance().removeCargo(Integer.parseInt(valueAt.toString()));                
                JOptionPane.showMessageDialog(null, "O cargo foi removido", "Cargo", 1);
            } else if(e.getSource().equals(btVoltar)) {
                ControladorCargo.getInstance().iniciaTelaPrincipal();
            }
        }
    }
}
