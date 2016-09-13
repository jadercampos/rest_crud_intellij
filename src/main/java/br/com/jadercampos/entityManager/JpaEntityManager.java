package br.com.jadercampos.entityManager;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by jader on 12/09/2016.
 */
public class JpaEntityManager {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("crud_unit");
    private EntityManager em = factory.createEntityManager();

    public EntityManager getEntityManager() {
        return em;
    }
}
