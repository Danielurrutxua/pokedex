package com.example.pokemon

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip

class TypeLoader(private val context: Context) {

    @SuppressLint("SetTextI18n", "ResourceType")
     fun loadType(
        type: String,
        chip: Chip
    ) {
        chip.chipIconSize = 60F
        chip.iconEndPadding = 0F

        when (type) {
            "Fire" -> {
                chip.chipBackgroundColor = getColorStateList(context, R.color.type_fire_background)
                chip.chipIcon = ContextCompat.getDrawable(context, R.drawable.icon_fire_colored)
                chip.text = "Fire"
                chip.setTextColor(ContextCompat.getColor(context, R.color.type_fire))
            }

            "Poison" -> {
                chip.chipBackgroundColor = getColorStateList(context, R.color.veneno_light)
                chip.chipIcon = ContextCompat.getDrawable(context, R.drawable.icon_veneno_colored)
                chip.text = "Poison"
                chip.setTextColor(ContextCompat.getColor(context, R.color.veneno))
            }

            "Water" -> {
                chip.chipBackgroundColor = getColorStateList(context, R.color.water_light)
                chip.chipIcon = ContextCompat.getDrawable(context, R.drawable.icon_agua_colored)
                chip.text = "Water"
                chip.setTextColor(ContextCompat.getColor(context, R.color.water))
            }

            "Grass" -> {
                chip.chipBackgroundColor = getColorStateList(context, R.color.planta_light)
                chip.chipIcon = ContextCompat.getDrawable(context, R.drawable.icon_planta_colored)
                chip.text = "Grass"
                chip.setTextColor(ContextCompat.getColor(context, R.color.planta))
            }

            "Flying" -> {
                chip.chipBackgroundColor = getColorStateList(context, R.color.volador_light)
                chip.chipIcon = ContextCompat.getDrawable(context, R.drawable.icon_fly_colored)
                chip.text = "Flying"
                chip.setTextColor(ContextCompat.getColor(context, R.color.volador))
            }
            else -> {

                chip.chipIconSize = 30F
                chip.iconEndPadding = 12F

                chip.chipBackgroundColor = getColorStateList(context, R.color.light_blue)
                chip.chipIcon = ContextCompat.getDrawable(context, R.drawable.pokeball)
                chip.text = type
                chip.setTextColor(ContextCompat.getColor(context, R.color.text))
            }
        }
    }
}