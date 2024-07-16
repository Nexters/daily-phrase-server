package com.nexters.dailyphrase.prize.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPrizeEvent is a Querydsl query type for PrizeEvent
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrizeEvent extends EntityPathBase<PrizeEvent> {

    private static final long serialVersionUID = -980018510L;

    public static final QPrizeEvent prizeEvent = new QPrizeEvent("prizeEvent");

    public final com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity _super = new com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> endAt = createDateTime("endAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> startAt = createDateTime("startAt", java.time.LocalDateTime.class);

    public final EnumPath<com.nexters.dailyphrase.common.enums.PrizeEventStatus> status = createEnum("status", com.nexters.dailyphrase.common.enums.PrizeEventStatus.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final DateTimePath<java.time.LocalDateTime> winnerAnnouncementAt = createDateTime("winnerAnnouncementAt", java.time.LocalDateTime.class);

    public QPrizeEvent(String variable) {
        super(PrizeEvent.class, forVariable(variable));
    }

    public QPrizeEvent(Path<? extends PrizeEvent> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrizeEvent(PathMetadata metadata) {
        super(PrizeEvent.class, metadata);
    }

}

