/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.funcionarios;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;
import br.ufsc.ine5605.trabalho02.cargos.Cargo;
import br.ufsc.ine5605.trabalho02.cargos.ControladorCargo;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
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

	// as duas primeiras letras sao para identificar a variavel: lb = label bt =
	// botao
	public TelaFuncionario() {
		super("Tela de Funcionários");
		this.btManager = new GerenciadorBotoes();
		this.configuraTela();
	}

	private void configuraTela() {
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
        lbTexto = new JLabel("Funcionários cadastrados:");               
        container.add(lbTexto, constraint);
        
        //Configuracao JTable de Cargos
        tbFuncionarios = new JTable();
        tbFuncionarios.setPreferredScrollableViewportSize(new Dimension(200, 100));
        tbFuncionarios.setFillsViewportHeight(true);
        constraint.gridheight = 4;
        constraint.gridwidth = 9;
        constraint.gridx = 1;
        constraint.gridy = 2;
        scroll = new JScrollPane(tbFuncionarios);
        container.add(scroll, constraint);
        
        //Configuracao JButton Novo
        constraint.gridheight = 1;
        constraint.gridwidth = 3;
        constraint.gridx = 1;
        constraint.gridy = 6;
        btCadastroFuncionario = new JButton("Novo Cargo");               
        btCadastroFuncionario.addActionListener(btManager);
        container.add(btCadastroFuncionario, constraint);
        
        //Configuracao JButton Alterar
        constraint.gridx = 4;
        constraint.gridy = 6;
        btAlterarFuncionario = new JButton("Alterar Cargo");               
        btAlterarFuncionario.addActionListener(btManager);
        container.add(btAlterarFuncionario, constraint);
        
        //Configuracao JButton Remover
        constraint.gridx = 7;
        constraint.gridy = 6;
        btRemoverFuncionario = new JButton("Remover Cargo");               
        btRemoverFuncionario.addActionListener(btManager);
        container.add(btRemoverFuncionario, constraint);
        
        //Configuracao JButton Voltar
        constraint.gridx = 4;
        constraint.gridy = 7;
        btVoltarMenuPrincipal = new JButton("Voltar");               
        btVoltarMenuPrincipal.addActionListener(btManager);
        container.add(btVoltarMenuPrincipal, constraint);
        
        //constraint.insets = new Insets(5,5,5,5);
        setSize(450, 350);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public void updateDate(){
        DefaultTableModel modeltbFuncionarios = new DefaultTableModel();
        modeltbFuncionarios.addColumn("Matrícula");
        modeltbFuncionarios.addColumn("Nome");
        modeltbFuncionarios.addColumn("Data de Nascimento");
        modeltbFuncionarios.addColumn("Cargo");
        modeltbFuncionarios.addColumn("Salário");
        modeltbFuncionarios.addColumn("Telefone");
        
        for(Funcionario funcionario : ControladorFuncionario.getInstance().getListaFuncionario()) {
            modeltbFuncionarios.addRow(new Object[]{funcionario.getMatricula(), funcionario.getNome(), funcionario.getNascimento(),
            		funcionario.getCargo().getNome(), funcionario.getSalario(), funcionario.getTelefone()});
        }
         tbFuncionarios.setModel(modeltbFuncionarios);
         this.repaint();
    }
	private class GerenciadorBotoes implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(btCadastroFuncionario)) {
				ControladorCargo.getInstance().iniciaTelaCadastroCargo();
			} else if (e.getSource().equals(btAlterarFuncionario)) {
				ControladorCargo.getInstance().iniciaTelaCadastroCargo();
			} else if (e.getSource().equals(btRemoverFuncionario)) {
				JOptionPane.showMessageDialog(null, "O funcionário foi removido", "Funcionário", 1);
			} else if (e.getSource().equals(btVoltarMenuPrincipal)) {
				ControladorCargo.getInstance().iniciaTelaPrincipal();
			}
		}
	}
}
