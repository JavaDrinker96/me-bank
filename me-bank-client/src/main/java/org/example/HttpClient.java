package org.example;

import retrofit2.http.*;

import java.util.concurrent.CompletableFuture;

public interface HttpClient {

    @POST("/account")
    @Headers("accept: application/json")
    CompletableFuture<BankAccount> create(@Query("balance") Long balance);

    @GET("/balance/{id}")
    @Headers("accept: application/json")
    CompletableFuture<Long> getBalance(@Path("id") Long id);

    @PUT("/balance")
    @Headers("accept: application/json")
    CompletableFuture<Long> changeBalance(@Body ChangingDto dto);

}