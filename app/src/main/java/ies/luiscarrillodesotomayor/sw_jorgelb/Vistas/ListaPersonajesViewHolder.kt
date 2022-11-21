package ies.luiscarrillodesotomayor.sw_jorgelb.Vistas

import android.content.ClipData.Item
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ies.luiscarrillodesotomayor.sw_jorgelb.Personajes.Result
import ies.luiscarrillodesotomayor.sw_jorgelb.databinding.ItemPersonajeBinding

class ListaPersonajesViewHolder(view: View): RecyclerView.ViewHolder(view)
{

    val binding = ItemPersonajeBinding.bind(view)
    fun bind(personaje: Result) {
        binding.NombrePersonaje.text = personaje.name
        binding.NumeroPeliculas.text = personaje.films.size.toString()
        binding.AlturaPersonaje.text = personaje.height


    }
}
