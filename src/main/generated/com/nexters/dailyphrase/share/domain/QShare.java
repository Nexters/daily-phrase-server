package com.nexters.dailyphrase.share.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShare is a Querydsl query type for Share
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShare extends EntityPathBase<Share> {

    private static final long serialVersionUID = -1239713304L;

    public static final QShare share = new QShare("share");

    public final com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity _super = new com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> phraseId = createNumber("phraseId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QShare(String variable) {
        super(Share.class, forVariable(variable));
    }

    public QShare(Path<? extends Share> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShare(PathMetadata metadata) {
        super(Share.class, metadata);
    }

}

