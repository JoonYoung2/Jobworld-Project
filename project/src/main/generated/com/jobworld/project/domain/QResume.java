package com.jobworld.project.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResume is a Querydsl query type for Resume
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResume extends EntityPathBase<Resume> {

    private static final long serialVersionUID = -1390653066L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResume resume = new QResume("resume");

    public final ListPath<Apply, QApply> applyList = this.<Apply, QApply>createList("applyList", Apply.class, QApply.class, PathInits.DIRECT2);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath img = createString("img");

    public final QMember member;

    public final StringPath title = createString("title");

    public QResume(String variable) {
        this(Resume.class, forVariable(variable), INITS);
    }

    public QResume(Path<? extends Resume> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResume(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResume(PathMetadata metadata, PathInits inits) {
        this(Resume.class, metadata, inits);
    }

    public QResume(Class<? extends Resume> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

