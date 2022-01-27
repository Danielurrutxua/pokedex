package com.example.pokemon.pokelist

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentBottomsheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class BottomNavigationDrawerFragment(private val viewModel: ListviewViewModel) :
    BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomsheetBinding
    private var itemSelected: Int? = null
    private lateinit var menu: Menu

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBottomsheetBinding.inflate(inflater)
        menu = binding.navigationView.menu

        itemSelected = viewModel.menuItemSelected.value
        if (itemSelected != null) {
            checkSelectedItem()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Bottom Navigation Drawer menu item clicks
            itemClicked(menuItem)
            this.dismiss()

            true
        }
    }

    private fun itemClicked(menuItem: MenuItem) {
        for (i in 0..3) {
            if (menuItem.title == menu.getItem(i).title) {
                menu.getItem(i).icon = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.icon_arrow_check
                )
                itemSelected = i
                viewModel.changeListOrder(itemSelected!!)
            } else menu.getItem(i).icon = null
        }
    }

    private fun checkSelectedItem() {
        for (i in 0..3) {
            if (i == itemSelected) menu.getItem(itemSelected!!).icon =
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.icon_arrow_check
                )
            else menu.getItem(i).icon = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.menuItemSelected.value = itemSelected
    }


}
