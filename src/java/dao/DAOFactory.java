package dao;

/**
 *
 * @author 
 */
public class DAOFactory {
    
    public static CursoDAO criarCursoDAO(){
        CursoDAO dao = new CursoDAO();
        dao.setSessao(
           HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    public static TurmaDAO criarTurmaDAO(){
        TurmaDAO dao = new TurmaDAO();
        dao.setSessao(
           HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    public static AvaliacaoDAO criarAvaliacaoDAO(){
        AvaliacaoDAO dao = new AvaliacaoDAO();
        dao.setSessao(
           HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    public static PerfilDAO criarPerfilDAO(){
        PerfilDAO dao = new PerfilDAO();
        dao.setSessao(
           HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    public static PessoaDAO criarPessoaDAO(){
        PessoaDAO dao = new PessoaDAO();
        dao.setSessao(
           HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    public static MatriculaDAO criarMatriculaDAO(){
        MatriculaDAO dao = new MatriculaDAO();
        dao.setSessao(
           HibernateUtil.getSessionFactory().getCurrentSession());
        return dao;
    }
    
//    public static ProdutoDAO criarProdutoDAO(){
//        ProdutoDAO dao = new ProdutoDAO();
//        dao.setSessao(
//           HibernateUtil.getSessionFactory().getCurrentSession());
//        return dao;
//    }
}
