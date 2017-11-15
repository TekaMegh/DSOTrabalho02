/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.trabalho02.acessos;

import br.ufsc.ine5605.trabalho02.ControladorPrincipal;
import br.ufsc.ine5605.trabalho02.cargos.IntervaloDeAcesso;
import br.ufsc.ine5605.trabalho02.funcionarios.Funcionario;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rak_w
 */
public class ControladorAcesso implements IControladorAcesso {
    
    private MapeadorAcesso mapAcessos = new MapeadorAcesso();
    private TelaEntrada telaEntrada;
    private TelaEntrada telaAcesso;
    
    private static ControladorAcesso controladorAcesso;

    public ControladorAcesso() {
        telaEntrada = new TelaEntrada();
        telaAcesso = new TelaEntrada();
    }

    
    
    /**
     * Retorna a instancia do controlador Acesso.
     *
     * @return Controlador Acesso
     */
    public static IControladorAcesso getInstance() {
        if (controladorAcesso == null) {
            controladorAcesso = new ControladorAcesso();
        }
        return controladorAcesso;
    }

    public void inicia() {
        
    }

    public String validaAcesso(int matricula, Date horaDeAcesso) {
        Funcionario funcionario = null;

        if (ControladorPrincipal.getInstance().hasFuncionarioByMatricula(matricula)) {
            funcionario = ControladorPrincipal.getInstance().getFuncionarioByMatricula(matricula);
        } else {
            return TipoAcesso.SEMMATRICULA.descricao();
        }

        if (funcionario.isBlocked()) {
            mapAcessos.put(new Acesso(TipoAcesso.ACESSOBLOQUEADO, matricula, horaDeAcesso));
            return TipoAcesso.ACESSOBLOQUEADO.descricao();

        } else if (!funcionario.getCargo().mayEnter()) {
            if (getAcessosByMatricula(funcionario.getMatricula()).size() == 2) {
                funcionario.setBlocked(true);
            }
            mapAcessos.put(new Acesso(TipoAcesso.NAOPOSSUIACESSO, matricula, horaDeAcesso));
            return TipoAcesso.NAOPOSSUIACESSO.descricao();

        } else if (funcionario.getCargo().isGerencial()) {

            return TipoAcesso.AUTORIZADO.descricao();

        } else if (validaHorario(funcionario, horaDeAcesso)) {

            return TipoAcesso.AUTORIZADO.descricao();

        } else {
            if (getAcessosByMatricula(funcionario.getMatricula()).size() == 2) {
                funcionario.setBlocked(true);
            }
            mapAcessos.put(new Acesso(TipoAcesso.HORARIONAOPERMITIDO, matricula, horaDeAcesso));
            return TipoAcesso.HORARIONAOPERMITIDO.descricao();
        }
    }

    /**
     * Recebe Tipo de Acesso e retorna todas as tentativas já registradas;
     *
     * @param tipo
     * @return lista de <Acesso>
     */
    public ArrayList<Acesso> getAcessosByTipo(TipoAcesso tipo) {
        ArrayList<Acesso> listaAcessosByTipo = new ArrayList();
        for (Acesso acesso : mapAcessos.getList() ) {
            if (acesso.getTipo() == tipo) {
                listaAcessosByTipo.add(acesso);
            }
        }
        return listaAcessosByTipo;
    }

    /**
     * Recebe matrícula de funcionario e retorna suas tentativas de acesso já
     * registradas;
     *
     * @param matricula
     * @return lista de <Acesso>
     */
    public ArrayList<Acesso> getAcessosByMatricula(int matricula) {
        ArrayList<Acesso> listaAcessosByMatricula = new ArrayList();
        for (Acesso acesso : mapAcessos.getList()) {
            if (acesso.getMatricula() == matricula) {
                listaAcessosByMatricula.add(acesso);
            }
        }
        return listaAcessosByMatricula;
    }

/**
     * Testa se horario recebido está entre os intervalos de acesso permitidos
     * de acordo com o cargo do funcionario recebido.
     *
     * @param funcionario
     * @param horaAtual
     * @return true caso o horário esteja dentro dos intervalos; false caso o
     * horário esteja fora dos intervalos
     *
     *
     */
    public boolean validaHorario(Funcionario funcionario, Date horaAtual) {

        Date now, inicio, fim;
        ArrayList<IntervaloDeAcesso> intervalos = funcionario.getCargo().getIntervalos();
        now = horaAtual;
        for (IntervaloDeAcesso interv : intervalos) {

            inicio = interv.getHorarioInicial();
            fim = interv.getHorarioFinal();

            if (now.getTime() <= fim.getTime() && now.getTime() >= inicio.getTime()) {
                return true;
            }

        }
        return false;
    }
}
