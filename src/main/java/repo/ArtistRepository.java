package repo;

import entity.Artist;

import javax.persistence.EntityManager;

public class ArtistRepository extends AbstractRepository<Artist> {
    public ArtistRepository(EntityManager em) {
        super(em, Artist.class);
    }
}
