/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.funcionarios;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carcaroff
 */
public class TelaFuncionario extends JFrame {
	private JLabel lbTexto;
	private JTable tbFuncionarios;
	private JButton btCadastroFuncionario;
	private JButton btRemoverFuncionario;
	private JButton btAlterarFuncionario;
	private JButton btVoltarMenuPrincipal;
	private GerenciadorBotoes btManager;
	private JScrollPane scroll;

	
	public TelaFuncionario() {
		super("Tela de Funcionarios");
		this.btManager = new GerenciadorBotoes();
		this.configuraTela();
	}

	private void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.fill = GridBagConstraints.BOTH;
        constraint.insets = new Insets(10, 10, 10, 10);
                
        //Configuracao JLabel
        constraint.gridheight = 1;
        constraint.gridwidth = 3;
        constraint.gridx = 1;
        constraint.gridy = 1;
        lbTexto = new JLabel("Funcionarios cadastrados:");               
        container.add(lbTexto, constraint);
        
        //Configuracao JTable de Funcionarios
        tbFuncionarios = new JTable();
        tbFuncionarios.setPreferredScrollableViewportSize(new Dimension(500, 200));
        tbFuncionarios.setFillsViewportHeight(true);
        constraint.gridheight = 4;
        constraint.gridwidth = 15;
        constraint.gridx = 1;
        constraint.gridy = 2;
        scroll = new JScrollPane(tbFuncionarios);
        container.add(scroll, constraint);
        
        //Configuracao JButton Novo
        constraint.gridheight = 1;
        constraint.gridwidth = 3;
        constraint.gridx = 1;
        constraint.gridy = 6;
        btCadastroFuncionario = new JButton("Novo Funcionario");               
        btCadastroFuncionario.addActionListener(btManager);
        container.add(btCadastroFuncionario, constraint);
        
        //Configuracao JButton Alterar
        constraint.gridx = 4;
        constraint.gridy = 6;
        btAlterarFuncionario = new JButton("Alterar Funcionario");               
        btAlterarFuncionario.addActionListener(btManager);
        container.add(btAlterarFuncionario, constraint);
        
        //Configuracao JButton Remover
        constraint.gridx = 7;
        constraint.gridy = 6;
        btRemoverFuncionario = new JButton("Remover Funcionario");               
        btRemoverFuncionario.addActionListener(btManager);
        container.add(btRemoverFuncionario, constraint);
        
        //Configuracao JButton Voltar
        constraint.gridx = 4;
        constraint.gridy = 7;
        btVoltarMenuPrincipal = new JButton("Voltar");               
        btVoltarMenuPrincipal.addActionListener(btManager);
        container.add(btVoltarMenuPrincipal, constraint);
        
        setSize(700, 600);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void updateData(){
        DefaultTableModel modeltbFuncionarios = new DefaultTableModel();
        modeltbFuncionarios.addColumn("Matr�cula");
        modeltbFuncionarios.addColumn("Nome");
        modeltbFuncionarios.addColumn("Data de Nascimento");
        modeltbFuncionarios.addColumn("Cargo");
        modeltbFuncionarios.addColumn("Sal�rio");
        modeltbFuncionarios.addColumn("Telefone");
        
        for(Funcionario funcionario : ControladorFuncionario.getInstance().getListaFuncionario()) {
            modeltbFuncionarios.addRow(new Object[]{funcionario.getMatricula(), funcionario.getNome(), formatDate(funcionario.getNascimento()),
            		funcionario.getCargo().getNome(), funcionario.getSalario(), funcionario.getTelefone()});
        }
         tbFuncionarios.setModel(modeltbFuncionarios);
         this.repaint();
    }
    
    private String formatDate(Date date) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		return formatador.format(date);
	}
	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btCadastroFuncionario)) {
				ControladorFuncionario.getInstance().iniciaTelaCadastroFuncionario();
			} else if (e.getSource().equals(btAlterarFuncionario)) {
				ArrayList<Object> values = valuesAtSelectedRow();
				ControladorFuncionario.getInstance().iniciaTelaAlteraFuncionario(values);
			} else if (e.getSource().equals(btRemoverFuncionario)) {
				Integer matricula = getSelectedMatricula();
				try {
					ControladorFuncionario.getInstance().removeFuncionario(matricula);
				} catch (Exception exc) {
					
				}
			} else if (e.getSource().equals(btVoltarMenuPrincipal)) {
				ControladorFuncionario.getInstance().iniciaTelaPrincipal();
			}
		}

		private Integer getSelectedMatricula() {
			int selectedRow = tbFuncionarios.getSelectedRow();
			Integer matricula = (Integer)tbFuncionarios.getValueAt(selectedRow, 0);
			return matricula;
		}
		
		private ArrayList<Object> valuesAtSelectedRow(){
			ArrayList<Object> values = new ArrayList<>();
			int selectedRow = tbFuncionarios.getSelectedRow();
			for (int i = 0; i < 6; i++) {
				values.add(tbFuncionarios.getValueAt(selectedRow, i));
			}
			return values;
		}
	}
}
