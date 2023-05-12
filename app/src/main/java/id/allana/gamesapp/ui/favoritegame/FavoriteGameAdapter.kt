package id.allana.gamesapp.ui.favoritegame

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.allana.gamesapp.data.model.FavoriteGameEntity
import id.allana.gamesapp.databinding.ItemGameBinding

class FavoriteGameAdapter: RecyclerView.Adapter<FavoriteGameAdapter.FavoriteGameViewHolder>() {

    private var listFavoriteGame: MutableList<FavoriteGameEntity> = mutableListOf()


    fun setListItems(listFavoriteGame: List<FavoriteGameEntity>) {
        clearListItems()
        addListItems(listFavoriteGame)
    }

    private fun clearListItems() {
        this.listFavoriteGame.clear()
    }

    private fun addListItems(listFavoriteGame: List<FavoriteGameEntity>) {
        this.listFavoriteGame.addAll(listFavoriteGame)
    }

    class FavoriteGameViewHolder(private val binding: ItemGameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(item: FavoriteGameEntity) {
            with(item) {
                binding.tvTitleGame.text = this.name
                binding.tvReleaseDateGame.text = this.released
                binding.tvRatingGame.text = this.rating.toString()

                Glide.with(itemView)
                    .load(this.backgroundImage)
                    .into(binding.ivPosterGame)

                itemView.setOnClickListener {
                    val actionToDetail = FavoriteGameFragmentDirections.actionFavoriteGameFragmentToDetailGameFragment(this.id)
                    it.findNavController().navigate(actionToDetail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteGameViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteGameViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listFavoriteGame.size
    }

    override fun onBindViewHolder(holder: FavoriteGameViewHolder, position: Int) {
        holder.bindView(listFavoriteGame[position])
    }
}