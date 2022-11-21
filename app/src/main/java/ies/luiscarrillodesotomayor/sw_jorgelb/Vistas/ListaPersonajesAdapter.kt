package ies.luiscarrillodesotomayor.sw_jorgelb.Vistas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ies.luiscarrillodesotomayor.sw_jorgelb.R
import ies.luiscarrillodesotomayor.sw_jorgelb.Personajes.Result

class ListaPersonajesAdapter(var personajes: List<Result>): RecyclerView.Adapter<ListaPersonajesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaPersonajesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_personaje, parent, false)
        return ListaPersonajesViewHolder(view)

    }

    override fun onBindViewHolder(holder: ListaPersonajesViewHolder, position: Int) {
        val personaje = personajes[position]
        holder.bind(personaje)
    }

    override fun getItemCount(): Int = personajes.size
}
