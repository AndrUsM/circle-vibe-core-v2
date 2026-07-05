package com.circle_vibe_core.app.user.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.circle_vibe_core.app.shared.service.QueryLoader;
import com.circle_vibe_core.app.user.dto.UserByTokenParams;
import com.circle_vibe_core.app.user.dto.UserFilterParams;
import com.circle_vibe_core.app.user.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class UserQueryRepository implements IUserQueryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final QueryLoader queryLoader = new QueryLoader();

    @Override
    public User getByToken(UserByTokenParams params) {
        String jpql = queryLoader.load(
                "jpql/user/find-users.jpql"
        );

        return entityManager.createQuery(jpql, User.class)
            .setParameter("onlyActive", params.active())
            .setParameter("token", params.token())
            .getSingleResult();
    }

    @Override
    public User getById(Long id) {
        return this.entityManager.find(User.class, id);
    }

    @Override
    public User getByFilter(UserFilterParams params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> user = query.from(User.class);
        List<Predicate> predicates = this.buildUserFilterPredicates(builder, user, params);;

        query.select(user);

        if (!predicates.isEmpty()){
            query.where(predicates);
        }

        return entityManager.createQuery(query).getSingleResult();
    }

    @Override 
    public boolean existsByFilter(UserFilterParams params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<User> user = query.from(User.class);
        List<Predicate> predicates = this.buildUserFilterPredicates(builder, user, params);


        query.select(builder.count(user)).where(predicates);

        return entityManager.createQuery(query).getSingleResult() > 0;
    }

    @Override 
    public List<User> getAll(UserFilterParams params) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> user = query.from(User.class);
        List<Predicate> predicates = this.buildUserFilterPredicates(builder, user, params);

        if (!predicates.isEmpty()) {
            query.where(predicates);

            return entityManager.createQuery(query).getResultList();
        }

        return entityManager.createQuery(query).getResultList();
    }

    private List<Predicate> buildUserFilterPredicates(
        CriteriaBuilder builder,
        Root<User> user,
        UserFilterParams params
    ) {
        List<Predicate> predicates = new ArrayList<>();

        if (params.email() != null && !params.email().isBlank()) {
            predicates.add(
                builder.equal(user.get("email"), params.email())
            );
        }

        return predicates;
    }
}
