package com.tinkoff.task.repository.data.repositoryImpl;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007H\u0016R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/tinkoff/task/repository/data/repositoryImpl/EntitiesRepositoryImpl;", "Lcom/tinkoff/task/repository/domain/repository/EntitiesRepository;", "localSource", "Lcom/tinkoff/task/repository/domain/datasource/EntityDataSource;", "remoteSource", "(Lcom/tinkoff/task/repository/domain/datasource/EntityDataSource;Lcom/tinkoff/task/repository/domain/datasource/EntityDataSource;)V", "entitiesObservable", "Lio/reactivex/Observable;", "", "Lcom/tinkoff/task/repository/domain/entity/Entity;", "observeEntities", "repository_prodDebug"})
public final class EntitiesRepositoryImpl implements com.tinkoff.task.repository.domain.repository.EntitiesRepository {
    private final io.reactivex.Observable<java.util.List<com.tinkoff.task.repository.domain.entity.Entity>> entitiesObservable = null;
    private final com.tinkoff.task.repository.domain.datasource.EntityDataSource localSource = null;
    private final com.tinkoff.task.repository.domain.datasource.EntityDataSource remoteSource = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Observable<java.util.List<com.tinkoff.task.repository.domain.entity.Entity>> observeEntities() {
        return null;
    }
    
    public EntitiesRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.tinkoff.task.repository.domain.datasource.EntityDataSource localSource, @org.jetbrains.annotations.NotNull()
    com.tinkoff.task.repository.domain.datasource.EntityDataSource remoteSource) {
        super();
    }
}