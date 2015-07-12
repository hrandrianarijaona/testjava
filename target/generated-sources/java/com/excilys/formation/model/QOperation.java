package com.excilys.formation.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOperation is a Querydsl query type for Operation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOperation extends EntityPathBase<Operation> {

    private static final long serialVersionUID = -1350339785L;

    public static final QOperation operation = new QOperation("operation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public final DateTimePath<org.joda.time.LocalDateTime> time = createDateTime("time", org.joda.time.LocalDateTime.class);

    public QOperation(String variable) {
        super(Operation.class, forVariable(variable));
    }

    public QOperation(Path<? extends Operation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOperation(PathMetadata metadata) {
        super(Operation.class, metadata);
    }

}

