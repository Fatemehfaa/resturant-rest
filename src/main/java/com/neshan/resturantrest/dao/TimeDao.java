package com.neshan.resturantrest.dao;

import com.neshan.resturantrest.model.Time;
import com.neshan.resturantrest.request.TimeSearchRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimeDao {
    EntityManager em;

    public List<Time> findAllBySimpleQuery(String methodName, String className) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Time> cq = cb.createQuery(Time.class);

        Root<Time> root = cq.from(Time.class);

        Predicate methodNamePredicate = cb.like(root.get("methodName"), "%" + methodName + "%");
        Predicate classNamePredicate = cb.like(root.get("className"), "%" + className + "%");

        Predicate methodNameOrClassNamePredicate = cb.or(methodNamePredicate, classNamePredicate);

        cq.where(methodNameOrClassNamePredicate);

        TypedQuery<Time> query = em.createQuery(cq);

        return query.getResultList();
    }

    public List<Time> findAllByCriteria(TimeSearchRequest request) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Time> cq = cb.createQuery(Time.class);
        List<Predicate> predicates = new ArrayList<>();

        Root<Time> root = cq.from(Time.class);
        if (request.getMethodName() != null) {
            Predicate methodNamePredicate = cb.like(root.get("methodName"), "%" + request.getClassName() + "%");
            predicates.add(methodNamePredicate);
        }
        if (request.getClassName() != null) {
            Predicate classNamePredicate = cb.like(root.get("className"), "%" + request.getClassName() + "%");
            predicates.add(classNamePredicate);
        }

        cq.where(
                cb.or(predicates.toArray(new Predicate[0]))
        );

        TypedQuery<Time> query = em.createQuery(cq);
        return query.getResultList();
    }

}