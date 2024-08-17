package com.nexters.dailyphrase.readhistory.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReadHistory is a Querydsl query type for ReadHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReadHistory extends EntityPathBase<ReadHistory> {

    private static final long serialVersionUID = 1968933288L;

    public static final QReadHistory readHistory = new QReadHistory("readHistory");

    public final com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity _super = new com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> phraseId = createNumber("phraseId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath userAgent = createString("userAgent");

    public QReadHistory(String variable) {
        super(ReadHistory.class, forVariable(variable));
    }

    public QReadHistory(Path<? extends ReadHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReadHistory(PathMetadata metadata) {
        super(ReadHistory.class, metadata);
    }

}

