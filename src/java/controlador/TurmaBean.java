/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.CursoDAO;
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
import modelo.Curso;
import modelo.Turma;
import org.primefaces.context.RequestContext;

/**
 *
 * @author teste
 */
@ManagedBean(name = "TurmaBean", eager = true)
@ViewScoped
public class TurmaBean implements Serializable {

    private Turma turma;
    private List<Turma> listaTurmas;
    private List<Turma> filteredTurmas;
    public List<Curso> listaCursos;

    public TurmaBean() {
        turma = new Turma();

        listaTurmas = new ArrayList<>();
        listaCursos = new ArrayList<>();

    }

    public List<Turma> getFilteredTurmas() {
        return filteredTurmas;
    }

    public void setFilteredTurmas(List<Turma> filteredTurmas) {
        this.filteredTurmas = filteredTurmas;
    }

    public List<Curso> getListaCursos() {
        CursoDAO curso = DAOFactory.criarCursoDAO();
        listaCursos = curso.listarTodos();
        return listaCursos;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Turma> getListaTurmas() {
        TurmaDAO dao = DAOFactory.criarTurmaDAO();
        listaTurmas = dao.listarTodos();

        return listaTurmas;
    }

    public void setListaTurmas(List<Turma> listaTurmas) {
        this.listaTurmas = listaTurmas;
    }

    public void salvar() {
        System.out.println("teste");
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        String msg = "";

        try {
            TurmaDAO dao = DAOFactory.criarTurmaDAO();

            dao.salvar(turma);
            msg = "ID: " + turma.getId()
                    + " | Sala: " + turma.getSala() + " Curso: " + turma.getCurso().getNome();
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso ao salvar!", msg);
            turma = new Turma();

        } catch (Exception e) {
            msg = e.getMessage();
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problemas ao salvar ", msg);
        } finally {
            context.addMessage(null, fm);
        }
    }

    public void excluir(Turma turma) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        String msg = "";
        try {
            TurmaDAO dao = DAOFactory.criarTurmaDAO();
            msg = "ID: " + turma.getId()
                    + " | Sala: " + turma.getSala();
            dao.excluir(turma);

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
    public String redirecionarURL() {
        return "index?faces-redirect=true";
    }

    public String redirecionar() {
        return "index";
    }

    public void reset() {
        turma = new Turma();

    }

}
