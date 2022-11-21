package ies.luiscarrillodesotomayor.sw_jorgelb

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ies.luiscarrillodesotomayor.sw_jorgelb.Vistas.ListaPersonajesAdapter
import ies.luiscarrillodesotomayor.sw_jorgelb.databinding.ActivityMainBinding
import ies.luiscarrillodesotomayor.sw_jorgelb.CuadroDialogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.app.DownloadManager.Query
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // al no varair la lista de personajes se inicializa el recycler view
        binding.ListaPersonajes.layoutManager = LinearLayoutManager(this)
        val personajeAdapter = ListaPersonajesAdapter(emptyList())
        binding.ListaPersonajes.adapter = personajeAdapter


        // metodo para cargar los personajes a traves del evento click
        binding.Verlistado.setOnClickListener() {

            CoroutineScope(Dispatchers.IO).launch {
                val llamada = getRetrofit().create(APIService::class.java)
                    .getPersonajes("people/")
                val respuestaPersonaje = llamada.body()

                runOnUiThread(){
                    if (llamada.isSuccessful){
                        var resultado = respuestaPersonaje?.results ?: emptyList()
                        personajeAdapter.personajes = resultado
                        personajeAdapter.notifyDataSetChanged()
                        Toast.makeText(this@MainActivity, "Personajes cargados", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        // Evento para guardar el listado
        binding.BGuardarListado.setOnClickListener() {
            mostrarCuadroDialogo()
        }
    }

    // funcion para mostrar el cuadro de dialogo
    private fun mostrarCuadroDialogo() {
        // me creo un objeto de la clase CuadroDialogo
        val cuadroDialogo = CuadroDialogo()
        // muestro el cuadro de dialogo
        cuadroDialogo.show(supportFragmentManager, "Guardar Listado")
    }

    // funcion retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}