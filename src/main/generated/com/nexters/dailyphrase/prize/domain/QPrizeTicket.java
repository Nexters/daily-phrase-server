package com.nexters.dailyphrase.prize.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPrizeTicket is a Querydsl query type for PrizeTicket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrizeTicket extends EntityPathBase<PrizeTicket> {

    private static final long serialVersionUID = 101565940L;

    public static final QPrizeTicket prizeTicket = new QPrizeTicket("prizeTicket");

    public final com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity _super = new com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity(this);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPrizeTicket(String variable) {
        super(PrizeTicket.class, forVariable(variable));
    }

    public QPrizeTicket(Path<? extends PrizeTicket> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrizeTicket(PathMetadata metadata) {
        super(PrizeTicket.class, metadata);
    }

}

