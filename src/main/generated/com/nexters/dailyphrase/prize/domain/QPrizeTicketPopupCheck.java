package com.nexters.dailyphrase.prize.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPrizeTicketPopupCheck is a Querydsl query type for PrizeTicketPopupCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrizeTicketPopupCheck extends EntityPathBase<PrizeTicketPopupCheck> {

    private static final long serialVersionUID = -2068721456L;

    public static final QPrizeTicketPopupCheck prizeTicketPopupCheck = new QPrizeTicketPopupCheck("prizeTicketPopupCheck");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final EnumPath<com.nexters.dailyphrase.common.enums.PrizeTicketPopupType> type = createEnum("type", com.nexters.dailyphrase.common.enums.PrizeTicketPopupType.class);

    public QPrizeTicketPopupCheck(String variable) {
        super(PrizeTicketPopupCheck.class, forVariable(variable));
    }

    public QPrizeTicketPopupCheck(Path<? extends PrizeTicketPopupCheck> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrizeTicketPopupCheck(PathMetadata metadata) {
        super(PrizeTicketPopupCheck.class, metadata);
    }

}

