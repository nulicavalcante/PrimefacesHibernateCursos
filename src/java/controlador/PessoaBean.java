/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.DAOFactory;
import dao.PessoaDAO;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Pessoa;

/**
 *
 * @author aluno
 */
@ManagedBean(name = "PessoaBean")
@SessionScoped
public class PessoaBean implements Serializable {

    private Pessoa pessoa;
    private Pessoa usuarioLogado;

    /**
     * Creates a new instance of PessoaBean
     */
    public PessoaBean() {
        pessoa = new Pessoa();
        usuarioLogado = new Pessoa();

    }
    //True se usuário está logado e false caso contrário 
    private boolean loggedIn;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public Pessoa getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Pessoa usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        String msg = "";
        try {
            PessoaDAO dao = DAOFactory.criarPessoaDAO();
            Pessoa usuarioEncontrado = dao.getPorEmail(pessoa.getEmail());
            if (usuarioEncontrado == null) {
                dao.salvar(pessoa);
                msg = "ID: " + pessoa.getEmail() + " | Nome: " + pessoa.getNome();
                fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Sucesso ao salvar!", msg);
                loggedIn = true;
                usuarioLogado = pessoa;
                pessoa = new Pessoa();
                return "/aluno/crudMatricula";
            } else {
                msg = "ERRO, já cadastrado: " + pessoa.getEmail() + "! ";
                fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Login", msg);
                return "index";
            }
        } catch (Exception e) {
            msg = e.getMessage();
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problemas ao salvar ", msg);
            return "index";
        } finally {
            context.addMessage(null, fm);
        }

    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        String msg = "";
        try {
            PessoaDAO dao = DAOFactory.criarPessoaDAO();
            Pessoa usuarioEncontrado = dao.login(pessoa.getEmail(), pessoa.getPwd());
            if (usuarioEncontrado != null) {
                //caso tenha retornado um usuario, setamos a variável loggedIn como true e guardamos o usuario encontrado na variável usuarioLogado. Depois de tudo, mandamos o usuário 
                //para a página index.xhtml 
                loggedIn = true;
                usuarioLogado = usuarioEncontrado;

                msg = "Bem-vindo, " + usuarioEncontrado.getNome() + "! ";

                fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Login", msg);
                pessoa = new Pessoa();
                goToMatricula();
            } else {
                msg = "ERRO," + pessoa.getEmail() + "! ";
                fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Login", msg);
                System.out.println("Passei");

            }
        } catch (Exception e) {
            msg = e.getMessage();
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problemas ao logar ", msg);

        } finally {
            context.addMessage(null, fm);
        }

    }
//Realiza o logout do usuário logado 

    public void logout() throws IOException {
//Setamos a variável usuarioLogado como nulo, ou seja, limpamos 
//os dados do usuário que estava logado e depois setamos a variável 
//loggedIn como false para sinalizar que o usuário não está mais logado 
        usuarioLogado = null;
        loggedIn = false;
//Mostramos um mensagem ao usuário e redirecionamos ele para a página de login 
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage fm = new FacesMessage();
        fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout", "Logout realizado com sucesso !");
        context.addMessage(null, fm);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Cursos/faces/index.xhtml");
    }

    public void goToLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Cursos/faces/login.xhtml");
    }

    private void goToMatricula() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("aluno/crudMatricula.xhtml");
    }

}
