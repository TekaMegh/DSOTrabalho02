/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author rak_w
 */
public class TelaPrincipal extends JFrame{
    
    private JButton btEntrar;
    private JButton btAcesso;
    private JButton btCargo;
    private JButton btFunc;
    private GerenciadorBotoes btManager;
    
    public TelaPrincipal() {
        super("Menu Principal");
        btManager = new GerenciadorBotoes();
        this.menuPrincipal();
    }

    private void menuPrincipal() {
        
        Container container = getContentPane();
        container.setLayout(new GridLayout(4, 0));
        
        //Configuracao botao de entrada
        btEntrar = new JButton("Entrar");  
        btEntrar.addActionListener(btManager);
        container.add(btEntrar);
        
        //Configuracao botao de acessos
        btAcesso = new JButton("Menu de Acesso");  
        btAcesso.addActionListener(btManager);
        container.add(btAcesso);
        
        //Configuracao botao de cargos
        btCargo = new JButton("Menu de Cargos");
        btCargo.addActionListener(btManager);
        container.add(btCargo);
        
        //Configuracao botao de funcionarios
        btFunc = new JButton("Menu de Funcionarios");
        btFunc.addActionListener(btManager);
        container.add(btFunc);
        
        setSize(300, 200);        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    private class GerenciadorBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(btEntrar)) {
            	ControladorPrincipal.getInstance().iniciaEntrada();
            } else if(e.getSource().equals(btAcesso)) {
            	ControladorPrincipal.getInstance().iniciaAcesso();
            } else if(e.getSource().equals(btCargo)) {
                ControladorPrincipal.getInstance().iniciaCargo();
            } else if(e.getSource().equals(btFunc)) {
            	ControladorPrincipal.getInstance().iniciaFuncionario();
                
            }
        }
        
    }
}
