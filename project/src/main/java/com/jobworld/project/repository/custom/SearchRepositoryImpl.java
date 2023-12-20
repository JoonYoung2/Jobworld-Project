package com.jobworld.project.repository.custom;

import com.jobworld.project.domain.QRecruit;
import com.jobworld.project.domain.Recruit;
import com.jobworld.project.repository.SearchRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepositoryImpl implements SearchRepositoryCustom {
    private final EntityManager em;

    @Override
    public List<Recruit> getRecruitSearchInfo(String userSearch) {
        JPQLQueryFactory queryFactory = new JPAQueryFactory(em);
        QRecruit recruit = QRecruit.recruit;
        List<Recruit> query = queryFactory.selectFrom(recruit)
                .where(userContainsTitle(recruit, userSearch))
                .fetch();
        return query;
    }

    @Override
    public List<Recruit> getRecruitSearchInfo(String companySearch, String compId) {
        JPQLQueryFactory queryFactory = new JPAQueryFactory(em);
        QRecruit recruit = QRecruit.recruit;

        List<Recruit> list = queryFactory.selectFrom(recruit)
                .where(companyContainsTitleAndCompanyId(recruit, companySearch, compId))
                .fetch();

        return list;
    }

    private BooleanExpression userContainsTitle(QRecruit recruit, String search) {
        if (search == null || search.isEmpty()) {
            return null;
        }
        BooleanExpression titleExpression = recruit.title.like("%" + search + "%");
        return titleExpression;
    }

    private BooleanExpression companyContainsTitleAndCompanyId(QRecruit recruit, String search, String companyId) {
        if (search == null || search.isEmpty()) {
            return null;
        }
        BooleanExpression titleExpression = recruit.title.like("%" + search + "%");
        BooleanExpression companyIdExpression = recruit.company.id.like("%" + companyId + "%");
        return titleExpression.and(companyIdExpression);
    }
}
