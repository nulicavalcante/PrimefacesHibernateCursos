/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DAOFactory;
import dao.MatriculaDAO;
import dao.TurmaDAO;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Matricula;
import modelo.Perfil;
import modelo.Pessoa;
import modelo.Turma;

/**
 *
 * @author teste
 */
@ManagedBean(name = "MatriculaBean")
@ViewScoped
public class MatriculaBean implements Serializable {
   
    private Matricula matricula;
    private List<Matricula> listaMatriculas;
    private List<Matricula> filteredMatriculas;
    private List<Turma> listaTurmas;
    
    /**
     * Creates a new instance of MatriculaBean
     */
    public MatriculaBean() {
      
      
        matricula = new Matricula();
        
        listaMatriculas = new ArrayList<>();
        listaTurmas = new ArrayList<>();
    }
    public List<Turma> getListaTurmas() {
        TurmaDAO dao = DAOFactory.criarTurmaDAO();
        listaTurmas = dao.listarTodos();
        return listaTurmas;
    }

    public List<Matricula> getFilteredMatriculas() {
        return filteredMatriculas;
    }

    public void setFilteredMatriculas(List<Matricula> filteredMatriculas) {
        this.filteredMatriculas = filteredMatriculas;
    }


    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public List<Matricula> getListaMatriculas() {
         MatriculaDAO dao = DAOFactory.criarMatriculaDAO();
        
         System.out.println("matricula peessoa " + matricula.getPessoa().getId() );
       
        listaMatriculas = dao.listarTodosPorAluno(matricula.getPessoa());
        return listaMatriculas;
    }

    public void setListaMatriculas(List<Matricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }
    
        public void salvar(Pessoa pessoa) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        String msg = "";
        try {
            MatriculaDAO dao = DAOFactory.criarMatriculaDAO();
            Perfil perfil = new Perfil();
            perfil.setId(1);
            perfil.setTipo("ALUNO");
            matricula.setPerfil(perfil);
            matricula.setPessoa(pessoa);
            matricula.setStatus(1);
            LocalDate hoje = LocalDate.now();
           
           
            dao.salvar(matricula);
            msg = "ID: " + matricula.getId()
                    + " | Tipo: " + matricula.getTurma().getSala();
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Sucesso ao salvar!", msg);
            matricula = new Matricula();
        } catch (Exception e) {
            msg = e.getMessage();
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problemas ao salvar ", msg);
        } finally {
            context.addMessage(null, fm);
        }
    }
            public void excluir(Matricula matricula) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        String msg = "";
        try {
            MatriculaDAO dao = DAOFactory.criarMatriculaDAO();
            msg = "ID: " + matricula.getId()
                 + " | Tipo: " + matricula.getTurma().getSala();
            dao.excluir(matricula);
           
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
  public String formatDate(Date hoje) {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
        return DATE_FORMAT.format(hoje);
    }
public void reset(){
    this.matricula = new Matricula();
}
}
