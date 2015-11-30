package dao;

import org.hibernate.Session;

/**
 *
 * @author 
 */
public abstract class GenericDAO<T> {
    
    private Session sessao;

    public GenericDAO() {
    }

    public GenericDAO(Session sessao) {
        this.sessao = sessao;
    }

    public Session getSessao() {
        return sessao;
    }

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }
    
    // CRUD - Create (Inserir)
    public void inserir(T obj){
        try{
            this.sessao.save(obj);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    // CRUD - Update (Atualizar)
    public void atualizar(T obj){
        try{
            this.sessao.merge(obj);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    // CRUD - Delete (Excluir)
    public void excluir(T obj){
        try{
            this.sessao.delete(obj);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    
}
