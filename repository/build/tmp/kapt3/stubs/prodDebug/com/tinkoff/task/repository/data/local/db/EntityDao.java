package com.tinkoff.task.repository.data.local.db;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u001c\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\u0006\u0010\b\u001a\u00020\u0003H\'J\u001c\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00052\u0006\u0010\b\u001a\u00020\nH\'J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005H\'J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\'J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\'\u00a8\u0006\u0011"}, d2 = {"Lcom/tinkoff/task/repository/data/local/db/EntityDao;", "", "deleteAll", "", "find", "Lio/reactivex/Flowable;", "", "Lcom/tinkoff/task/repository/data/local/entity/EntityL;", "id", "findById", "", "getAll", "insert", "", "entity", "insertAll", "entityList", "repository_prodDebug"})
public abstract interface EntityDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM EntityL ORDER BY dbId ASC")
    public abstract io.reactivex.Flowable<java.util.List<com.tinkoff.task.repository.data.local.entity.EntityL>> getAll();
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract long insert(@org.jetbrains.annotations.NotNull()
    com.tinkoff.task.repository.data.local.entity.EntityL entity);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.util.List<java.lang.Long> insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.tinkoff.task.repository.data.local.entity.EntityL> entityList);
    
    @androidx.room.Query(value = "DELETE FROM EntityL")
    public abstract int deleteAll();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM EntityL WHERE id = :id")
    public abstract io.reactivex.Flowable<java.util.List<com.tinkoff.task.repository.data.local.entity.EntityL>> find(int id);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM EntityL WHERE id LIKE \'%\' || :id || \'%\'")
    public abstract io.reactivex.Flowable<java.util.List<com.tinkoff.task.repository.data.local.entity.EntityL>> findById(@org.jetbrains.annotations.NotNull()
    java.lang.String id);
}