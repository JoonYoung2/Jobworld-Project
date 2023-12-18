package com.jobworld.project.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompany is a Querydsl query type for Company
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompany extends EntityPathBase<Company> {

    private static final long serialVersionUID = -307635116L;

    public static final QCompany company = new QCompany("company");

    public final StringPath brandImg = createString("brandImg");

    public final StringPath businessType = createString("businessType");

    public final NumberPath<Integer> emplNum = createNumber("emplNum", Integer.class);

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath pw = createString("pw");

    public final ListPath<Recruit, QRecruit> recruit = this.<Recruit, QRecruit>createList("recruit", Recruit.class, QRecruit.class, PathInits.DIRECT2);

    public final StringPath site = createString("site");

    public final StringPath size = createString("size");

    public QCompany(String variable) {
        super(Company.class, forVariable(variable));
    }

    public QCompany(Path<? extends Company> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCompany(PathMetadata metadata) {
        super(Company.class, metadata);
    }

}

