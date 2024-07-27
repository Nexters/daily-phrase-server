package com.nexters.dailyphrase.prize.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPrizeEntryCheck is a Querydsl query type for PrizeEntryCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrizeEntryCheck extends EntityPathBase<PrizeEntryCheck> {

    private static final long serialVersionUID = 704187038L;

    public static final QPrizeEntryCheck prizeEntryCheck = new QPrizeEntryCheck("prizeEntryCheck");

    public final com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity _super = new com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final NumberPath<Long> prizeId = createNumber("prizeId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPrizeEntryCheck(String variable) {
        super(PrizeEntryCheck.class, forVariable(variable));
    }

    public QPrizeEntryCheck(Path<? extends PrizeEntryCheck> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrizeEntryCheck(PathMetadata metadata) {
        super(PrizeEntryCheck.class, metadata);
    }

}

