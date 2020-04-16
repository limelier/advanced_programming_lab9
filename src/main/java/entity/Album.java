package entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Album.findByArtist", query = "select a from Album a where a.artist = :artist")
@Table(name = "albums")
public class Album {
    @Id
    @SequenceGenerator(name = "albums_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "albums_id_seq")
    private int id;

    private String name;

    @Column(name = "release_year")
    private int releaseYear;

    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    @ManyToOne
    private Artist artist;

    public Album(String name, int releaseYear, Artist artist) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseYear=" + releaseYear +
                ", artistId=" + artist.getId() +
                '}';
    }

    // boilerplate //

    public Album() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
