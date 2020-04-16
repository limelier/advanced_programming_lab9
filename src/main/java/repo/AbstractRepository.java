package repo;

import entity.Artist;
import util.PersistenceUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class AbstractRepository<T> {
    EntityManager em;
    Class<T> cls;

    public AbstractRepository(EntityManager em, Class<T> cls) {
        this.em = em;
        this.cls = cls;
    }

    public void create(T object) {
        em.persist(object);
    }

    public T findById(int id) {
        return em.find(cls, id);
    }

    public List<T> findByName(String name) {
        // not using hibernate sessions makes this painful
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cbQuery = cb.createQuery(cls);
        Root<T> root = cbQuery.from(cls);
        ParameterExpression<String> parameter = cb.parameter(String.class);
        cbQuery.select(root).where(cb.equal(root.get("name"), parameter));

        TypedQuery<T> query = em.createQuery(cbQuery);
        query.setParameter(parameter, name);
        return query.getResultList();
    }
}
