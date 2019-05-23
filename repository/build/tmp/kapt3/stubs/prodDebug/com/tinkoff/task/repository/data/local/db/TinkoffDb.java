package com.tinkoff.task.repository.data.local.db;

import java.lang.System;

@androidx.room.TypeConverters(value = {com.tinkoff.task.repository.data.local.db.DbTypeConverters.class})
@androidx.room.Database(entities = {com.tinkoff.task.repository.data.local.entity.DepositePointL.class, com.tinkoff.task.repository.data.local.entity.PartnerL.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&\u00a8\u0006\u0007"}, d2 = {"Lcom/tinkoff/task/repository/data/local/db/TinkoffDb;", "Landroidx/room/RoomDatabase;", "()V", "depositePointDao", "Lcom/tinkoff/task/repository/data/local/db/DepositePointDao;", "partnerDao", "Lcom/tinkoff/task/repository/data/local/db/PartnerDao;", "repository_prodDebug"})
public abstract class TinkoffDb extends androidx.room.RoomDatabase {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tinkoff.task.repository.data.local.db.DepositePointDao depositePointDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.tinkoff.task.repository.data.local.db.PartnerDao partnerDao();
    
    public TinkoffDb() {
        super();
    }
}