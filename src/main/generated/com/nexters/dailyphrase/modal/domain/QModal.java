package com.nexters.dailyphrase.modal.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QModal is a Querydsl query type for Modal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QModal extends EntityPathBase<Modal> {

    private static final long serialVersionUID = 691533992L;

    public static final QModal modal = new QModal("modal");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final StringPath leftButtonText = createString("leftButtonText");

    public final StringPath rightButtonText = createString("rightButtonText");

    public final EnumPath<com.nexters.dailyphrase.common.enums.ModalType> type = createEnum("type", com.nexters.dailyphrase.common.enums.ModalType.class);

    public QModal(String variable) {
        super(Modal.class, forVariable(variable));
    }

    public QModal(Path<? extends Modal> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModal(PathMetadata metadata) {
        super(Modal.class, metadata);
    }

}

