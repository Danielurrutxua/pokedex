package com.example.pokemon.pokelist

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.TypeLoader
import com.example.pokemon.databinding.PokemonListItemBinding
import com.example.pokemon.model.Pokemon
import java.util.*


class ItemAdapter(private val pokemonRw: RecyclerView, private val viewModel: ListviewViewModel) : ListAdapter<Pokemon, ItemAdapter.ItemViewHolder>(
    DiffCallback
) {

    class ItemViewHolder(
        var binding: PokemonListItemBinding,
        var context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {

            if (pokemon.favourite) {
                binding.favourite.visibility = View.VISIBLE
            }else binding.favourite.visibility = View.INVISIBLE

            val types = pokemon.types.map {
                Pokemon.firstLetterUpper(it)
            }
            val typeLoader = TypeLoader(context)

            cleanChip()
            when(types.size){
                1 -> typeLoader.loadType(types[0], binding.chip1)
                2 -> { typeLoader.loadType(types[0], binding.chip1)
                    typeLoader.loadType(types[1], binding.chip2)
                }
                else -> Log.d("ItemAdapter", "No Types")
            }

            binding.pokemon = pokemon
            binding.executePendingBindings()

        }

        @SuppressLint("ResourceType")
        fun cleanChip() {
            binding.chip2.chipBackgroundColor = getColorStateList(context, R.color.cardview)
            binding.chip2.chipIcon = null
            binding.chip2.text = ""
            binding.chip2.setTextColor(ContextCompat.getColor(context, R.color.cardview))
        }

    }

    override fun onCurrentListChanged(
        previousList: MutableList<Pokemon>,
        currentList: MutableList<Pokemon>
    ) {
        super.onCurrentListChanged(previousList, currentList)
        pokemonRw.scrollToPosition(0)


    }

    companion object DiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        return ItemViewHolder(
            PokemonListItemBinding.inflate(LayoutInflater.from(parent.context)),
            parent.context
        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)

        holder.itemView.setOnClickListener {
            /*
            val list = currentList.sortedBy {
                Pokemon.removeZeros(it.id).toInt()
            }
             */
            val list = currentList

            val id = pokemon.id.toString()
            val name = Pokemon.firstLetterUpper(pokemon.name)
            val listNames = list.map {
                Pokemon.firstLetterUpper(it.name)
            }.toTypedArray()
            val listIds = list.map{
                "#".plus(it.id).plus(" ").plus(it.name)
            }.toTypedArray()

            val action =
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailFragment(
                    id = id,
                    name = name,
                    list = listNames,
                    listIds = listIds
                )
            holder.itemView.findNavController().navigate(action)
            viewModel.loadFavourites = true

        }


    }




}


