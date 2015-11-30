/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DAOFactory;
import dao.CursoDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Curso;

/**
 *
 * @author 
 */
@ManagedBean(name = "CursoBean")
@ViewScoped
public class CursoBean implements Serializable {

    private Curso curso;
    private List<Curso> listaCursos;
    


    public CursoBean() {
        curso = new Curso();
        listaCursos = new ArrayList<>();
    
    }

    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Curso> getListaCursos() {
        CursoDAO dao = DAOFactory.criarCursoDAO();
        listaCursos = dao.listarTodos();
        return listaCursos;
    }

    public void setListaCursos(List<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        String msg = "";
        try {
            CursoDAO dao = DAOFactory.criarCursoDAO();
            dao.salvar(curso);
            msg = "ID: " + curso.getId()
                    + " | Nome: " + curso.getNome();
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso ao salvar!", msg);
            curso = new Curso();
        } catch (Exception e) {
            msg = e.getMessage();
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problemas ao salvar ", msg);
        } finally {
            context.addMessage(null, fm);
        }
    }

    public void excluir(Curso curso) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        String msg = "";
        try {
            CursoDAO dao = DAOFactory.criarCursoDAO();
            msg = "ID: " + curso.getId()
                 + " | Nome: " + curso.getNome();
            dao.excluir(curso);
           
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
    public void reset(){
        curso = new Curso();
    }
    public String redirecionarURL(){
        return "index?faces-redirect=true";
    }
    
    public String redirecionar(){
        return "index";
    }
}
