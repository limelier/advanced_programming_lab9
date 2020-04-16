package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    static private EntityManagerFactory emfInstance;
    static private EntityManager emInstance;

    public static EntityManagerFactory getEmfInstance() {
        if (emfInstance == null) {
            emfInstance = Persistence.createEntityManagerFactory("MusicAlbumsPU");
        }

        return emfInstance;
    }

    public static EntityManager getEmInstance() {
        if (emInstance == null) {
            emInstance = getEmfInstance().createEntityManager();
        }

        return emInstance;
    }

    public static void close() {
        emInstance.close();
        emfInstance.close();
    }
}
