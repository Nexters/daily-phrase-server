package com.nexters.dailyphrase.prize.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPrize is a Querydsl query type for Prize
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrize extends EntityPathBase<Prize> {

    private static final long serialVersionUID = 1783440584L;

    public static final QPrize prize = new QPrize("prize");

    public final com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity _super = new com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> requiredTicketCount = createNumber("requiredTicketCount", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPrize(String variable) {
        super(Prize.class, forVariable(variable));
    }

    public QPrize(Path<? extends Prize> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrize(PathMetadata metadata) {
        super(Prize.class, metadata);
    }

}

