package com.example.pokemon.pokedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.batura.zerolibrary.widgets.DesignSystemDialog
import com.example.pokemon.R
import com.example.pokemon.TypeLoader
import com.example.pokemon.databinding.FragmentPokemonDetailBinding
import com.example.pokemon.loadImage
import com.example.pokemon.model.Pokemon
import com.example.pokemon.model.Stats
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi


class PokemonDetailFragment : Fragment() {

    private val viewModel: PokemonDetailViewModel by viewModels()
    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var id: String
    private lateinit var name: String
    private lateinit var listNames: List<String>
    private lateinit var listIds: List<String>

    @FlowPreview
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPokemonDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        saveArgument()
        updateUI()
        return binding.root
    }

    @FlowPreview
    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.chipGroup.children.forEach {
            changeOriginalShinyImg(it as Chip)
        }
        binding.imgChevronRight.setOnClickListener {
            changeToBackImage()
        }
        binding.imgChevronLeft.setOnClickListener {
            changeToFrontImage()
        }

        binding.chevronLeft.setOnClickListener {
            changeToDefaultImage()
            viewModel.previousName()
            viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
                updateTextsNextPrevious2(pokemon.name)
            }

        }
        binding.chevronRight.setOnClickListener {
            viewModel.nextName()
            viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
                updateTextsNextPrevious1(pokemon.name)
            }
            changeToDefaultImage()
        }
        binding.chevronLeftText.setOnClickListener {
            viewModel.previousName()
            viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
                updateTextsNextPrevious2(pokemon.name)
            }
            changeToDefaultImage()
        }
        binding.chevronRightText.setOnClickListener {
            viewModel.nextName()
            viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
                updateTextsNextPrevious1(pokemon.name)
            }
            changeToDefaultImage()
    }}

//        binding.favButton.setOnClickListener {
//            val pokemon = viewModel.pokemon.value
//            if (pokemon?.fav != null) {
//                if (pokemon.fav) {
//                    createDialog()
//                } else {
//                    changeFavValue(true)
//                    Snackbar.make(
//                        it, "${pokemon.name} se ha añadido a Favoritos",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
//                }
//                isFavourite(pokemon.fav)
//            }
//
//        }
//
//        binding.toolbarDetail.backArrow.setOnClickListener {
//            it.findNavController().popBackStack()
//        }




    @FlowPreview
    @InternalCoroutinesApi
    private fun saveArgument() {
        id = requireArguments().getString("id").toString()
        name = requireArguments().getString("name").toString()
        listNames = requireArguments().getStringArray("list")!!.toList()
        listIds = requireArguments().getStringArray("list_ids")!!.toList()
        viewModel.saveArguments(id, listNames)

    }

    private fun updateUI() {
        viewModel.pokemon.observe(viewLifecycleOwner) { pokemon ->
            loadTextsNextPrevious()
            //isFavourite(pokemon.fav)
            pokemon.abilities?.let { loadAbilities(it) }
            loadStats(pokemon.stats)
            loadTypes(pokemon.types)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun isFavourite(isFav: Boolean) {
        if (isFav) {
            binding.favoritoImg.visibility = View.VISIBLE
            binding.favoritoText.visibility = View.VISIBLE
            binding.favButton.text = "Eliminar de favoritos"
            binding.favButton.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.icon_favourite_filled_white,
                0,
                0,
                0
            )
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.icon_favourite_filled_white
            )
        } else {
            binding.favoritoImg.visibility = View.INVISIBLE
            binding.favoritoText.visibility = View.INVISIBLE
            binding.favButton.text = "Añadir a favoritos"
            binding.favButton.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.icon_favourite,
                0,
                0,
                0
            )
        }
    }

    private fun changeOriginalShinyImg(chip: Chip) {
        (chip).setOnCheckedChangeListener { _, _ ->
            if (binding.original.isChecked) changeToOriginalImage()
            else changeToShinyImage()
        }
    }

    private fun changeToOriginalImage() {
        if (binding.imgChevronRight.isVisible) {
            loadImage(binding.image, viewModel.imgUrls[0])
        } else loadImage(binding.image, viewModel.imgUrls[1])
    }

    private fun changeToShinyImage() {
        if (binding.imgChevronRight.isVisible) {
            loadImage(binding.image, viewModel.imgUrls[2])
        } else loadImage(binding.image, viewModel.imgUrls[3])
    }

    private fun changeToBackImage() {
        binding.imgChevronRight.visibility = View.INVISIBLE
        binding.imgChevronLeft.visibility = View.VISIBLE
        if (binding.original.isChecked) {
            loadImage(binding.image, viewModel.imgUrls[1])
        } else loadImage(binding.image, viewModel.imgUrls[3])
    }

    private fun changeToFrontImage() {
        binding.imgChevronRight.visibility = View.VISIBLE
        binding.imgChevronLeft.visibility = View.INVISIBLE
        if (binding.original.isChecked) {
            loadImage(binding.image, viewModel.imgUrls[0])
        } else loadImage(binding.image, viewModel.imgUrls[2])
    }

    private fun changeToDefaultImage() {
        binding.imgChevronRight.visibility = View.VISIBLE
        binding.imgChevronLeft.visibility = View.INVISIBLE
        val chip = binding.chipGroup[0] as Chip
        chip.isChecked = true

    }

    @SuppressLint("SetTextI18n")
    private fun loadAbilities(abilities: List<String>) {
        when (abilities.size) {
            0 -> binding.habilidad1.text = "No abilities found"
            1 -> loadOneAbility(abilities[0])
            2 -> loadTwoAbilities(abilities)
            3 -> loadThreeAbilities(abilities)
            else -> loadFourAbilities(abilities)
        }
    }

    private fun loadOneAbility(ability: String) {
        binding.habilidad1.text = "·".plus(ability)

    }

    private fun loadTwoAbilities(abilities: List<String>) {
        loadOneAbility(abilities[0])
        binding.habilidad2.text = "·".plus(abilities[1])

    }

    private fun loadThreeAbilities(abilities: List<String>) {
        loadTwoAbilities(abilities)
        binding.habilidad3.text = "·".plus(abilities[2])
        binding.habilidad3.visibility = View.VISIBLE

        val layoutParams = binding.constraint.layoutParams as FrameLayout.LayoutParams
        layoutParams.bottomMargin = 130
        binding.constraint.layoutParams = layoutParams
    }

    private fun loadFourAbilities(abilities: List<String>) {
        loadThreeAbilities(abilities)
        binding.habilidad3.text = "·".plus(abilities[3])
    }

    private fun loadStats(stats: Stats) {
        binding.progress0.progress = stats.hp
        binding.progress1.progress = stats.attack
        binding.progress2.progress = stats.defense
        binding.progress3.progress = stats.special_attack
        binding.progress4.progress = stats.speed
        binding.progress5.progress = stats.special_defense
    }

    private fun loadTypes(types: List<String>) {
        val types1 = types.map {
            Pokemon.firstLetterUpper(it)
        }
        val typeLoader = TypeLoader(requireContext())
        when (types.size) {
            1 -> typeLoader.loadType(types1[0], binding.chip1)
            2 -> {
                typeLoader.loadType(types1[0], binding.chip1)
                typeLoader.loadType(types1[1], binding.chip2)
            }
            else -> Log.d("ItemAdapter", "No Types")
        }
    }

    private fun loadTextsNextPrevious() {
        val position = listNames.indexOf(Pokemon.firstLetterUpper(name))

        if (position > 0) binding.chevronLeftText.text = listIds[position - 1]
        else {
            binding.chevronLeftText.text = ""
            binding.chevronLeft.visibility = View.INVISIBLE
        }
        if (position < listIds.size - 1) binding.chevronRightText.text =
            listIds[position + 1]
        else {
            binding.chevronRightText.text = ""
            binding.chevronRight.visibility = View.INVISIBLE
        }
    }

    private fun updateTextsNextPrevious1(name: String) {
        val position = listNames.indexOf(Pokemon.firstLetterUpper(name))
        binding.chevronLeft.visibility = View.VISIBLE
        if (position < listIds.lastIndex) {
            binding.chevronRightText.text = listIds[position + 1]
        } else {
            binding.chevronRightText.text = ""
            binding.chevronRight.visibility = View.INVISIBLE
        }
        if (position == 0) binding.chevronLeftText.text = listIds[position]
        else binding.chevronLeftText.text = listIds[position - 1]

    }

    private fun updateTextsNextPrevious2(name: String) {
        val position = listNames.indexOf(Pokemon.firstLetterUpper(name))
        binding.chevronRight.visibility = View.VISIBLE
        if (position > 0) {
            binding.chevronLeftText.text = listIds[position - 1]
        } else {
            binding.chevronLeftText.text = ""
            binding.chevronLeft.visibility = View.INVISIBLE
        }
        if (position == listIds.lastIndex) binding.chevronRightText.text = listIds[position]
        else binding.chevronRightText.text = listIds[position + 1]

    }

    private fun createDialog() {
        val fragment = DesignSystemDialog(
            R.layout.container_dialog,
            R.style.DialogTheme
        )
        val test = DesignSystemDialog.DesignSystemComponents(
            description = "¿Quieres eliminar a ${viewModel.pokemon.value!!.name} de tus favoritos?",
            leftButtonText = "Cancelar",
            rightButtonText = "Eliminar"
        )

        fragment.setDesignComponents(test)
        fragment.show(requireActivity().supportFragmentManager, "")

        fragment.setListener(object : DesignSystemDialog.DesignSystemDialogListener {
            override fun onRightButtonClick(dialogName: String) {
                super.onRightButtonClick(dialogName)
                changeFavValue(!viewModel.pokemon.value!!.favourite)
                Snackbar.make(
                    binding.container,
                    "${viewModel.pokemon.value!!.name} se ha eliminado de Favoritos",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun changeFavValue(value: Boolean) {
        viewModel.changeFavValue(value)
    }
}