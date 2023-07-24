package com.example.gitrepoviewer


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    private var repositories: List<Repository>? = null

    fun setRepositories(repositories: List<Repository>?) {
        this.repositories = repositories
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories?.get(position)
        repository?.let {
            holder.textViewRepoName.text = it.name
            holder.textViewRepoDescription.text = it.description
        }
    }

    override fun getItemCount(): Int {
        return repositories?.size ?: 0
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewRepoName: TextView = itemView.findViewById(R.id.textViewRepoName)
        val textViewRepoDescription: TextView = itemView.findViewById(R.id.textViewRepoDescription)
    }
}

