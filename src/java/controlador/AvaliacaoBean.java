/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AvaliacaoDAO;
import dao.DAOFactory;
import dao.TurmaDAO;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Avaliacao;
import modelo.Turma;

/**
 *
 * @author teste
 */
@ManagedBean(name = "AvaliacaoBean")
@ViewScoped
public class AvaliacaoBean implements Serializable {

    private Avaliacao avaliacao;
    private List<Avaliacao> listaAvaliacoes;
    private List<Turma> listaTurmas;
    private List<Avaliacao> filteredAval;

    public AvaliacaoBean() {
        avaliacao = new Avaliacao();
        listaAvaliacoes = new ArrayList<>();
        listaTurmas = new ArrayList<>();
    }

    public List<Avaliacao> getFilteredAval() {
        return filteredAval;
    }

    public void setFilteredAval(List<Avaliacao> filteredAval) {
        this.filteredAval = filteredAval;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Avaliacao> getListaAvaliacoes() {
        AvaliacaoDAO dao = DAOFactory.criarAvaliacaoDAO();
        listaAvaliacoes = dao.listarTodos();
        return listaAvaliacoes;
    }

    public List<Turma> getListaTurmas() {
        TurmaDAO dao = DAOFactory.criarTurmaDAO();
        listaTurmas = dao.listarTodos();
        return listaTurmas;
    }
    public void setListaAvaliacoes(List<Avaliacao> listaAvaliacoes) {
        this.listaAvaliacoes = listaAvaliacoes;
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        String msg = "";
        try {
            AvaliacaoDAO dao = DAOFactory.criarAvaliacaoDAO();
            dao.salvar(avaliacao);
            msg = "ID: " + avaliacao.getId()
                    + " | Tipo: " + avaliacao.getTipo();
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso ao salvar!", msg);
            avaliacao = new Avaliacao();
        } catch (Exception e) {
            msg = e.getMessage();
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problemas ao salvar ", msg);
        } finally {
            context.addMessage(null, fm);
        }
    }

    public void excluir(Avaliacao avaliacao) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        String msg = "";
        try {
            AvaliacaoDAO dao = DAOFactory.criarAvaliacaoDAO();
            msg = "ID: " + avaliacao.getId()
                 + " | Tipo: " + avaliacao.getTipo();
            dao.excluir(avaliacao);
           
            fm = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Sucesso ao excluir!", msg);
        } catch (Exception e) {
            msg = e.getMessage();
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problemas ao excluir ", msg);
        } finally {
            context.addMessage(null, fm);
        }
    }
    public String format(Date hora) {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm");
        return DATE_FORMAT.format(hora);
    }
    public String redirecionarURL(){
        return "index?faces-redirect=true";
    }
    
    public String redirecionar(){
        return "index";
    }  
    public void reset(){
        avaliacao = new Avaliacao();
    }
}
