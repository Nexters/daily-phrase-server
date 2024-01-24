package com.nexters.dailyphrase.phraseimage.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhraseImage is a Querydsl query type for PhraseImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhraseImage extends EntityPathBase<PhraseImage> {

    private static final long serialVersionUID = -1794942168L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhraseImage phraseImage = new QPhraseImage("phraseImage");

    public final com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity _super = new com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.nexters.dailyphrase.phrase.domain.QPhrase phrase;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath url = createString("url");

    public final StringPath uuid = createString("uuid");

    public QPhraseImage(String variable) {
        this(PhraseImage.class, forVariable(variable), INITS);
    }

    public QPhraseImage(Path<? extends PhraseImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhraseImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhraseImage(PathMetadata metadata, PathInits inits) {
        this(PhraseImage.class, metadata, inits);
    }

    public QPhraseImage(Class<? extends PhraseImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.phrase = inits.isInitialized("phrase") ? new com.nexters.dailyphrase.phrase.domain.QPhrase(forProperty("phrase"), inits.get("phrase")) : null;
    }

}

