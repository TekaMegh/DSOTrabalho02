/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.cargos;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author rak_w
 */
public class ControladorCargo implements IControladorCargo {

    private static ControladorCargo controladorCargo;
    private TelaCargo telaCargo;
    private TelaCadastroCargo telaCadastroCargo;
    private TelaAlteraCargo telaAlteraCargo;
    private TelaIntervalosDeAcesso telaIntervalosDeAcesso;
    private TelaCadastroIntervalosDeAcesso telaCadastroIntervalosDeAcesso;
    private MapeadorCargo mapCargo;
    private int numCodigo = 1;

    /**
     * Método construtor da classe Inicializa os atributos da classe
     */
    public ControladorCargo() {

        this.mapCargo = new MapeadorCargo();

        this.telaCargo = new TelaCargo();
        this.telaCadastroCargo = new TelaCadastroCargo();
        this.telaAlteraCargo = new TelaAlteraCargo();
        this.telaIntervalosDeAcesso = new TelaIntervalosDeAcesso();
        this.telaCadastroIntervalosDeAcesso = new TelaCadastroIntervalosDeAcesso();
    }

    /**
     *
     * @return IControladorCargo Retorna a unica instancia do ControladorCargo
     * como interface.
     */
    public static IControladorCargo getInstance() {
        if (controladorCargo == null) {
            controladorCargo = new ControladorCargo();
        }
        return controladorCargo;
    }

    @Override
    public void iniciaTelaPrincipal() {
        this.telaCargo.setVisible(false);
        ControladorPrincipal.getInstance().inicia();
    }

    @Override
    public void iniciaTelaCargo() {
        this.telaAlteraCargo.setVisible(false);
        this.telaCadastroCargo.setVisible(false);
        this.telaCadastroIntervalosDeAcesso.setVisible(false);
        this.telaIntervalosDeAcesso.setVisible(false);
        this.telaCargo.updateData();
        this.telaCargo.setVisible(true);
    }

    @Override
    public void iniciaTelaCadastroCargo() {
        this.telaCargo.setVisible(false);
        this.telaCadastroCargo.novoCodigo(this.getNumCodigo());
        this.telaCadastroCargo.setVisible(true);
    }

    @Override
    public void iniciaTelaAlteraCargo(Cargo cargo) {
        this.telaCargo.setVisible(false);
        if(!cargo.mayEnter()) {
            this.telaAlteraCargo.updateData(cargo.getNome(), cargo.getCodigo(), 0, this.getIntervalos(cargo.getIntervalos()));
        }
        if(cargo.isGerencial()){
            this.telaAlteraCargo.updateData(cargo.getNome(), cargo.getCodigo(), 2, this.getIntervalos(cargo.getIntervalos()));
        }
        if(cargo.mayEnter() && !cargo.isGerencial()) {
            this.telaAlteraCargo.updateData(cargo.getNome(), cargo.getCodigo(), 3, this.getIntervalos(cargo.getIntervalos()));
        }
        
        this.telaAlteraCargo.setVisible(true);
    }

    @Override
    public void iniciaTelaIntervalosDeAcesso() {
        this.telaIntervalosDeAcesso.setVisible(true);
    }

    @Override
    public void iniciaTelaCadastroIntervalosDeAcesso() {
        this.telaCadastroIntervalosDeAcesso.setVisible(true);
    }

    @Override
    public void configuraTelaCadastroCargo(ArrayList<String> intervalos) {
        this.telaCadastroCargo.updateData(intervalos);
    }

    @Override
    public void configuraTelaAlteraCargo(ArrayList<String> intervalos) {
        this.telaAlteraCargo.updateData(intervalos);
    }

    @Override
    public void configuraTelaIntervaloDeAcesso(Cargo cargo) {
        this.telaIntervalosDeAcesso.updateData(cargo);
    }

    @Override
    public Collection<Cargo> getListaCargos() {
        return this.mapCargo.getList();
    }

    @Override
    public ArrayList<String> getNomeCargos() {
        ArrayList<String> nomes = new ArrayList<>();
        for (Cargo cargo : this.mapCargo.getList()) {

            String nome = cargo.getNome();
            nomes.add(nome);
        }
        return nomes;
    }

    @Override
    public Cargo getCargoByNome(String nome) {
        for (Cargo cargo : this.mapCargo.getList()) {
            if (cargo.getNome().equals(nome)) {
                return cargo;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String getNumCodigo() {
        if (this.numCodigo == 1 && this.hasCodigo(numCodigo)) {
            this.numCodigo = this.mapCargo.getList().size();
        }
        do {
            if (this.hasCodigo(numCodigo)) {
                this.numCodigo++;
            }
        } while (this.hasCodigo(numCodigo));

        return String.valueOf(this.numCodigo);
    }

    /**
     *
     * @param codigo
     * @return boolean Verifica a existencia de um cargo com o codigo recebido
     * Retorna uma booleana indicando a existencia ou não do cargo
     */
    public boolean hasCodigo(int codigo) {
        for (Cargo cargo : getListaCargos()) {
            if (cargo.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Cargo getCargoByCodigo(int codigo) {
        for (Cargo cargo : this.mapCargo.getList()) {
            if (cargo.getCodigo() == codigo) {
                return cargo;
            }
        }
        return null;
    }

    /**
     *
     * @param nome
     * @param codigo
     * @param mayEnter
     * @param gerencial
     */
    @Override
    public void incluiCargo(String nome, int codigo, boolean mayEnter, boolean gerencial) {
        boolean codigoExiste;

        codigoExiste = this.hasCodigo(codigo);

        if (!codigoExiste) {
            Cargo cargo = new Cargo(codigo, nome, mayEnter, gerencial);
            this.numCodigo += 1;
            this.mapCargo.putCargo(cargo);
        }
        this.iniciaTelaCargo();
    }

    public void alteraCargoByCodigo(String nome, int codigo, boolean mayEnter, boolean gerencial) {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.setNome(nome);
        cargo.setMayEnter(mayEnter);
        cargo.setGerencial(gerencial);
    }

    @Override
    public void removeCargo(int codigo) {
        Cargo cargo = this.getCargoByCodigo(codigo);
        boolean hasFuncionario = ControladorPrincipal.getInstance().hasFuncionarioByCargo(cargo);
        if (!hasFuncionario) {
            this.mapCargo.removeCargo(codigo);
        }
        this.iniciaTelaCargo();
    }

    /**
     *
     * @param codigo
     * @param deHora
     * @param ateHora
     * @throws Exception
     */
    @Override
    public void setIntervaloInCargoByCodigo(int codigo, String deHora, String ateHora) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.addIntervalo(deHora, ateHora);
    }

    public void removeIntervalosByCodigo(int codigo, IntervaloDeAcesso intervalo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        cargo.removeIntervalo(intervalo);
    }

    public ArrayList<IntervaloDeAcesso> getIntervalosByCodigo(int codigo) throws Exception {
        Cargo cargo = this.getCargoByCodigo(codigo);
        return cargo.getIntervalos();
    }

    @Override
    public int parseInt(Object object) {
        return Integer.parseInt(object.toString());
    }

    @Override
    public ArrayList<String> getIntervalos(ArrayList<IntervaloDeAcesso> intervalosDeAcesso) {
        ArrayList<String> intervalos = new ArrayList<>();
        DateFormat formatador = new SimpleDateFormat("HH:mm");
        if (intervalosDeAcesso != null) {
            for (IntervaloDeAcesso intervalo : intervalosDeAcesso) {
                String horaInicial = formatador.format(intervalo.getHorarioInicial());
                String horaFinal = formatador.format(intervalo.getHorarioFinal());
                intervalos.add(horaInicial + " a " + horaFinal);
            }
        }
        return intervalos;
    }

}
