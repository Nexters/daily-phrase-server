package com.nexters.dailyphrase.phrase.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhrase is a Querydsl query type for Phrase
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPhrase extends EntityPathBase<Phrase> {

    private static final long serialVersionUID = 88636914L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhrase phrase = new QPhrase("phrase");

    public final com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity _super = new com.nexters.dailyphrase.common.domain.QBaseDateTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isReserved = createBoolean("isReserved");

    public final com.nexters.dailyphrase.phraseimage.domain.QPhraseImage phraseImage;

    public final DatePath<java.time.LocalDate> publishDate = createDate("publishDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final NumberPath<Integer> viewCount = createNumber("viewCount", Integer.class);

    public QPhrase(String variable) {
        this(Phrase.class, forVariable(variable), INITS);
    }

    public QPhrase(Path<? extends Phrase> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhrase(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhrase(PathMetadata metadata, PathInits inits) {
        this(Phrase.class, metadata, inits);
    }

    public QPhrase(Class<? extends Phrase> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.phraseImage = inits.isInitialized("phraseImage") ? new com.nexters.dailyphrase.phraseimage.domain.QPhraseImage(forProperty("phraseImage"), inits.get("phraseImage")) : null;
    }

}

