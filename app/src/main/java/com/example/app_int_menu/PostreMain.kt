package com.example.app_int_menu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_int_menu.adapter.PostreAdapter
import com.example.app_int_menu.databinding.PostresMainBinding
import com.example.app_int_menu.PostreResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostreMain : AppCompatActivity(), PostreAdapter.OnItemClicked {

    lateinit var binding: PostresMainBinding

    lateinit var adatador: PostreAdapter

    var listaPostres= arrayListOf<Postre>()


    var postre = Postre(-1, "","","")

    var isEditando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = PostresMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvpostres.layoutManager = LinearLayoutManager(this)
        setupRecyclerView()

        obtenerPostres()

        binding.btnAddUpdatepostre.setOnClickListener {
            var isValido = validarCampos()
            if (isValido) {
                if (!isEditando) {
                    agregarPostre()
                } else {
                    actualizarPostre()
                }
            } else {
                Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun setupRecyclerView() {
        adatador = PostreAdapter(this, listaPostres)
        adatador.setOnClick(this@PostreMain)
        binding.rvpostres.adapter = adatador

    }

    fun validarCampos(): Boolean {
        return !(binding.etnombrepostre.text.isNullOrEmpty() || binding.etpreciopostre.text.isNullOrEmpty() || binding.etdescripcionpostre.text.isNullOrEmpty())
    }

    fun obtenerPostres() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.obtenerPostres()
            runOnUiThread {
                if (call.isSuccessful) {
                    listaPostres = call.body()!!.listaPostres
                    setupRecyclerView()
                } else {
                    Toast.makeText(this@PostreMain, "ERROR CONSULTAR TODOS", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun agregarPostre() {

        this.postre.id = -1
        this.postre.name = binding.etnombrepostre.text.toString()
        this.postre.precio = binding.etpreciopostre.text.toString()
        this.postre.descripcion = binding.etdescripcionpostre.text.toString()



        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.agregarPostres(postre)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@PostreMain, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerPostres()
                    limpiarCampos()
                    limpiarObjeto()

                } else {
                    Toast.makeText(this@PostreMain, "ERROR ADD", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun actualizarPostre() {

        this.postre.name = binding.etnombrepostre.text.toString()
        this.postre.precio = binding.etpreciopostre.text.toString()
        this.postre.descripcion = binding.etdescripcionpostre.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.actualizarPostre(postre.id, postre)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@PostreMain, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerPostres()
                    limpiarCampos()
                    limpiarObjeto()

                    binding.btnAddUpdatepostre.setText("Agregar Postre")
                    binding.btnAddUpdatepostre.backgroundTintList = resources.getColorStateList(R.color.white)
                    isEditando = false
                }
            }
        }
    }

    fun limpiarCampos() {
        binding.etnombrepostre.setText("")
        binding.etpreciopostre.setText("")
        binding.etdescripcionpostre.setText("")
    }

    fun limpiarObjeto() {
        this.postre.id = -1
        this.postre.name = ""
        this.postre.precio = ""
        this.postre.descripcion = ""

    }

    override fun editarPostre(postre: Postre) {
        binding.etnombrepostre.setText(postre.name)
        binding.etpreciopostre.setText(postre.precio)
        binding.etdescripcionpostre.setText(postre.descripcion)


        binding.btnAddUpdatepostre.setText("Actualizar Postre")
        binding.btnAddUpdatepostre.backgroundTintList = resources.getColorStateList(R.color.white)
        this.postre = postre
        isEditando = true
    }

    override fun borrarPostre(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.borrarPostre(id)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@PostreMain, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerPostres()
                }
            }
        }
    }
}