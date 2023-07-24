package com.example.gitrepoviewer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApiService {

    @GET("users/{user}/repos")
    fun getRepositories(
        @Path("user") user: String
    ): Call<List<Repository>> // Assuming Repository is the data class representing a repository
}
