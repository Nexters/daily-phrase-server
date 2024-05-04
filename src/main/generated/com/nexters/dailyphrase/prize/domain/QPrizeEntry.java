package com.nexters.dailyphrase.prize.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrizeEntry is a Querydsl query type for PrizeEntry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrizeEntry extends EntityPathBase<PrizeEntry> {

    private static final long serialVersionUID = -980242294L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrizeEntry prizeEntry = new QPrizeEntry("prizeEntry");

    public final com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity _super = new com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QPrize gift;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPrizeEntry(String variable) {
        this(PrizeEntry.class, forVariable(variable), INITS);
    }

    public QPrizeEntry(Path<? extends PrizeEntry> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrizeEntry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrizeEntry(PathMetadata metadata, PathInits inits) {
        this(PrizeEntry.class, metadata, inits);
    }

    public QPrizeEntry(Class<? extends PrizeEntry> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.gift = inits.isInitialized("gift") ? new QPrize(forProperty("gift"), inits.get("gift")) : null;
    }

}

