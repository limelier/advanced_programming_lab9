package app;

import entity.Album;
import entity.Artist;
import repo.AlbumRepository;
import repo.ArtistRepository;
import util.PersistenceUtil;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.time.LocalTime;
import java.util.List;

public class AlbumManager {
    public static void main(String[] args) {
        String artistName = LocalTime.now().toString(); // unique name for each test
        putTest(artistName);
        pullTest(artistName);
        PersistenceUtil.close();
    }

    private static void pullTest(String artistName) {
        EntityManager em = PersistenceUtil.getEmInstance();
        ArtistRepository artistRepo = new ArtistRepository(em);
        AlbumRepository albumRepo = new AlbumRepository(em);

        List<Artist> artists = artistRepo.findByName(artistName);
        Artist artist = artists.get(0);
        System.out.println(artist);

        List<Album> albums = albumRepo.findByArtist(artist);
        System.out.println(albums);
    }

    private static void putTest(String artistName) {
        EntityManager em = PersistenceUtil.getEmInstance();
        em.getTransaction().begin();
        ArtistRepository artistRepo = new ArtistRepository(em);
        AlbumRepository albumRepo = new AlbumRepository(em);

        Artist artist = new Artist(artistName, "England");
        artistRepo.create(artist);
        albumRepo.create(new Album("Album 1", 2020, artist));
        albumRepo.create(new Album("Album 2", 2020, artist));
        em.getTransaction().commit();
    }
}
