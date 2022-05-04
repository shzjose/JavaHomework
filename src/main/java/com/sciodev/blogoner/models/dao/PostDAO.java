package com.sciodev.blogoner.models.dao;

import com.sciodev.blogoner.models.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("PostDAOJPA")
public class PostDAO implements IPostDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Post> findAll() {
        return entityManager.createQuery("from Post").getResultList();
    }

    @Transactional
    @Override
    public void save(Post post) {
        entityManager.merge(post);
    }

    @Transactional
    @Override
    public Post findOne(Long id) {
        return entityManager.find(Post.class, id);
    }

    @Transactional
    @Override
    public void deletePost(Long id) {
        entityManager.remove(findOne(id));
    }
}
