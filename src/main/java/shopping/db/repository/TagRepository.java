package shopping.db.repository;

import shopping.db.entity.Tag;

import java.util.UUID;

import static shopping.test.DatabaseSessionManager.withEntityManager;

public class TagRepository extends SimpleCRUDRepository<UUID, Tag>{

    public TagRepository() {
        super(Tag.class);
    }

    public Tag findByName(String name){
        return withEntityManager(entityManager ->
                entityManager.createQuery("FROM Tag where name=?1", Tag.class)
                        .setParameter(1, name)
                        .getSingleResult());
    }
}
