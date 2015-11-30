/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Perfil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author teste
 */
public class PerfilDAO extends GenericDAO<Perfil> {
    // CRUD  - Retrive (Seleção)

    public Perfil getPorId(Integer id) {
        try {
            return (Perfil) getSessao().get(Perfil.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Perfil> listarTodos() {
        Criteria c = getSessao().createCriteria(Perfil.class);
        c.addOrder(Order.asc("tipo"));
        return (List<Perfil>) c.list();
    }
    
    public void salvar(Perfil obj) {
        Perfil existe = getPorId(obj.getId());
        try {
            if (existe != null) {
                this.atualizar(obj);
            } else {
                this.inserir(obj);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
