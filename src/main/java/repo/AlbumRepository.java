package repo;

import entity.Album;
import entity.Artist;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class AlbumRepository extends AbstractRepository<Album> {
    public AlbumRepository(EntityManager em) {
        super(em, Album.class);
    }

    public List<Album> findByArtist(Artist artist) {
        TypedQuery<Album> query = em.createNamedQuery("Album.findByArtist", Album.class);
        query.setParameter("artist", artist);
        return query.getResultList();
    }
}
