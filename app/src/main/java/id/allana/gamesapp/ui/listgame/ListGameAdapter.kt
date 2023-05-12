package id.allana.gamesapp.ui.listgame

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.allana.gamesapp.data.model.ResultsItem
import id.allana.gamesapp.databinding.ItemGameBinding

class ListGameAdapter: RecyclerView.Adapter<ListGameAdapter.ListGameViewHolder>() {

    private var listGame: MutableList<ResultsItem?> = mutableListOf()

    fun setListItems(listGame: List<ResultsItem?>) {
        clearListItems()
        addListItems(listGame)
    }

    private fun clearListItems() {
        this.listGame.clear()
    }

    private fun addListItems(listItem: List<ResultsItem?>) {
        this.listGame.addAll(listItem)
    }


    class ListGameViewHolder(private val binding: ItemGameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: ResultsItem) {
            with(item) {
                binding.tvTitleGame.text = this.name
                binding.tvReleaseDateGame.text = this.released
                binding.tvRatingGame.text = this.rating.toString()

                Glide.with(itemView)
                    .load(this.backgroundImage)
                    .into(binding.ivPosterGame)

                itemView.setOnClickListener {
                    val actionToDetail = ListGameFragmentDirections.actionListGameFragmentToDetailGameFragment(this.id!!)
                    it.findNavController().navigate(actionToDetail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListGameViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListGameViewHolder(binding)
    }

    override fun getItemCount(): Int = listGame.size

    override fun onBindViewHolder(holder: ListGameViewHolder, position: Int) {
        listGame[position]?.let { holder.bindView(it) }
    }
}