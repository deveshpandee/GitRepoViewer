package com.example.gitrepoviewer


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var repositoryAdapter: RepositoryAdapter
    private lateinit var editTextGitHubUsername: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "GitHub Repositories"

        // Set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerViewRepositories)
        recyclerView.layoutManager = LinearLayoutManager(this)
        repositoryAdapter = RepositoryAdapter()
        recyclerView.adapter = repositoryAdapter

        editTextGitHubUsername = findViewById(R.id.editTextGitHubUsername)
        val buttonRefresh: Button = findViewById(R.id.buttonRefresh)

        // Fetch repositories from the API and populate the RecyclerView
        buttonRefresh.setOnClickListener {
            fetchRepositories()
        }
    }

    private fun fetchRepositories() {
        val username = editTextGitHubUsername.text.toString().trim()
        val call: Call<List<Repository>> = GitHubApiClient.apiService.getRepositories(username)

        call.enqueue(object : Callback<List<Repository>> {
            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                if (response.isSuccessful) {
                    val repositories = response.body()
                    repositories?.let {
                        repositoryAdapter.setRepositories(it)
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                // Handle network errors or API call failure
            }
        })
    }

}
