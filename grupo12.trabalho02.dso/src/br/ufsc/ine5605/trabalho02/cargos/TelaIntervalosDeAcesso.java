package br.ufsc.ine5605.trabalho02.cargos;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TelaIntervalosDeAcesso extends JFrame {

    private GerenciadorBotoes gerenciadorBotoes;
    private JTable tbItens;
    private JScrollPane spBaseTabela;
    private JButton btVoltar;
    private JLabel lbIntervalos;
    private Cargo cargo;

    public TelaIntervalosDeAcesso() {
        super("IntervalosDeAcesso");
        this.gerenciadorBotoes = new GerenciadorBotoes();
        this.configuraTela();
    }

    public void configuraTela() {
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();

        // configurando JTable
        tbItens = new JTable();
        tbItens.setPreferredScrollableViewportSize(new Dimension(200, 100));
        tbItens.setFillsViewportHeight(true);
        constraint.gridheight = 4;
        constraint.gridwidth = 9;
        constraint.gridx = 0;
        constraint.gridy = 1;
        spBaseTabela = new JScrollPane(tbItens);
        container.add(spBaseTabela, constraint);

        // configurando botao Voltar
        constraint.gridx = 1;
        constraint.gridy = 5;
        btVoltar = new JButton("Voltar");
        btVoltar.addActionListener(gerenciadorBotoes);
        container.add(btVoltar, constraint);

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void updateData(Cargo cargo) {
        DefaultTableModel modelTbItens = new DefaultTableModel();
        modelTbItens.addColumn("Intervalos de Acesso");

        if (cargo.isGerencial()) {
            modelTbItens.addRow(new Object[]{"Acesso Livre"});

        } else if (!cargo.mayEnter()) {
            modelTbItens.addRow(new Object[]{"Sem Acesso"});
        } else {
            for (IntervaloDeAcesso intervalo : cargo.getIntervalos()) {
                String horario = formatToHour(intervalo.getHorarioInicial()) + " a "
                        + formatToHour(intervalo.getHorarioFinal());
                modelTbItens.addRow(new Object[]{horario});

            }

        }

        tbItens.setModel(modelTbItens);

        this.repaint();

    }

    public String formatToHour(Date horarioInicial) {
        SimpleDateFormat formatadorHora = new SimpleDateFormat("HH:mm");
        return formatadorHora.format(horarioInicial);

    }

    private class GerenciadorBotoes implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btVoltar)) {
                ControladorCargo.getInstance().iniciaTelaCargo();
            }
        }
    }

}
