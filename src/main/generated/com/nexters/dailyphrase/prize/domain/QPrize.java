package com.nexters.dailyphrase.prize.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrize is a Querydsl query type for Prize
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrize extends EntityPathBase<Prize> {

    private static final long serialVersionUID = 1783440584L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrize prize = new QPrize("prize");

    public final com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity _super = new com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final QPrizeEvent event;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath manufacturer = createString("manufacturer");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> requiredTicketCount = createNumber("requiredTicketCount", Integer.class);

    public final StringPath shortName = createString("shortName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPrize(String variable) {
        this(Prize.class, forVariable(variable), INITS);
    }

    public QPrize(Path<? extends Prize> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrize(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrize(PathMetadata metadata, PathInits inits) {
        this(Prize.class, metadata, inits);
    }

    public QPrize(Class<? extends Prize> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QPrizeEvent(forProperty("event")) : null;
    }

}

