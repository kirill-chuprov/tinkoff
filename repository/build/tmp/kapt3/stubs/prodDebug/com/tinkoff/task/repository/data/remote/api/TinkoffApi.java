package com.tinkoff.task.repository.data.remote.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\tH\'\u00a8\u0006\n"}, d2 = {"Lcom/tinkoff/task/repository/data/remote/api/TinkoffApi;", "", "getDepositionPoints", "Lio/reactivex/Observable;", "Lcom/tinkoff/task/repository/data/remote/entity/dto/DepositePointsResponse;", "longitude", "", "latitude", "radius", "", "repository_prodDebug"})
public abstract interface TinkoffApi {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "v1/deposition_points")
    public abstract io.reactivex.Observable<com.tinkoff.task.repository.data.remote.entity.dto.DepositePointsResponse> getDepositionPoints(@retrofit2.http.Query(value = "longitude")
    double longitude, @retrofit2.http.Query(value = "latitude")
    double latitude, @retrofit2.http.Query(value = "radius")
    float radius);
}