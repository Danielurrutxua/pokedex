package com.example.pokemon.pokelist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokemonListBinding
import com.example.pokemon.databinding.MyToolbarBinding
import com.google.android.material.chip.Chip
import kotlin.properties.Delegates

open class PokemonListFragment : Fragment() {

    private val viewModel: ListviewViewModel by viewModels()
    private lateinit var binding: FragmentPokemonListBinding
    private var checkBoxState by Delegates.notNull<Boolean>()
    private lateinit var toolbarBinding: MyToolbarBinding
    private var lastPosition = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        toolbarBinding = MyToolbarBinding.inflate(inflater)
        binding = FragmentPokemonListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        checkBoxState = false

        val recyclerView = binding.pokemonRw
        recyclerView.adapter = ItemAdapter(binding.pokemonRw, viewModel)

        viewModel.pokemonList.observe(viewLifecycleOwner, {
            binding.pokemonCount.text = it.size.toString().plus(" Pokémon")
        })

        initializeToolbar()

        return binding.root
    }


    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.needToAddFavValues()

        binding.scrollUp.setOnClickListener {
            binding.pokemonRw.smoothScrollToPosition(0)
            binding.scrollUp.visibility = View.GONE
        }

        binding.cardViewLayout.materialCheckBox.setOnClickListener {
            checkBoxClicked()
        }

        binding.cardViewLayout.bottomsheet.setOnClickListener {
            val bottomNavDrawerFragment = BottomNavigationDrawerFragment(viewModel)
            bottomNavDrawerFragment.show(
                (activity as AppCompatActivity).supportFragmentManager,
                bottomNavDrawerFragment.tag
            )
        }

        binding.cardViewLayout.chipGroup.children.forEach {
            (it as Chip).setOnCheckedChangeListener { _, _ ->
                handleChipSelection()
            }
        }

        binding.toolbarLayout.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.changeSearchText(newText)
                return true
            }

        })

        binding.pokemonRw.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val currentPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

                if (currentPosition >= lastPosition) binding.scrollUp.visibility = View.GONE
                else binding.scrollUp.visibility = View.VISIBLE
                lastPosition = currentPosition

                if (newState == 0){
                    binding.scrollUp.visibility = View.GONE
                    if(currentPosition == 0) binding.appBarLayout.setExpanded(true)
                }
            }
        })
    }

    private fun initializeToolbar() {
        val toolbar = toolbarBinding.toolbar
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
    }

    private fun handleChipSelection() {
        val checkedTypes = mutableListOf<Boolean>()
        binding.cardViewLayout.chipGroup.children.forEach { chip ->
            val chip1 = chip as Chip
            checkedTypes.add(chip1.isChecked)

        }
        viewModel.changeTypesFilter(checkedTypes)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    private fun checkBoxClicked() {

        if (!checkBoxState) {
            checkBoxChecked()
        } else {
            checkBoxNotChecked()
        }
    }

    private fun checkBoxChecked() {
        binding.cardViewLayout.materialCheckBox.background =
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.icon_selection_checkbox_on
            )
        viewModel.changeFavFilter(true)
        checkBoxState = true
    }

    private fun checkBoxNotChecked() {
        binding.cardViewLayout.materialCheckBox.background =
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.icon_selection_checkbox_off
            )
        viewModel.changeFavFilter(false)
        checkBoxState = false
    }
}