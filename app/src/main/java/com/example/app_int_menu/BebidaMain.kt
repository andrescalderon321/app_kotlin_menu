package com.example.app_int_menu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_int_menu.adapter.BebidaAdapter
import com.example.app_int_menu.databinding.BebidaMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BebidaMain : AppCompatActivity(), BebidaAdapter.OnItemClicked {

    lateinit var binding: BebidaMainBinding

    lateinit var adatador: BebidaAdapter

    var listaBebidas = arrayListOf<Bebida>()


    var bebida = Bebida(-1, "","","")

    var isEditando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BebidaMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvBebidas.layoutManager = LinearLayoutManager(this)
        setupRecyclerView()

        obtenerBebidas()

        binding.btnAddUpdatebebida.setOnClickListener {
            var isValido = validarCampos()
            if (isValido) {
                if (!isEditando) {
                    agregarProducto()
                } else {
                    actualizarBebida()
                }
            } else {
                Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun setupRecyclerView() {
        adatador = BebidaAdapter(this, listaBebidas)
        adatador.setOnClick(this@BebidaMain)
        binding.rvBebidas.adapter = adatador

    }

    fun validarCampos(): Boolean {
        return !(binding.etnombrebebida.text.isNullOrEmpty() || binding.etpreciobebida.text.isNullOrEmpty() || binding.etdescripcionbebida.text.isNullOrEmpty())
    }

    fun obtenerBebidas() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.obtenerBebidas()
            runOnUiThread {
                if (call.isSuccessful) {
                    listaBebidas = call.body()!!.listaBebidas

                    setupRecyclerView()
                } else {
                    Toast.makeText(this@BebidaMain, "ERROR CONSULTAR TODOS", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun agregarProducto() {

        this.bebida.id = -1
        this.bebida.name = binding.etnombrebebida.text.toString()
        this.bebida.descripcion = binding.etdescripcionbebida.text.toString()
        this.bebida.precio = binding.etpreciobebida.text.toString()




        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.agregarBebida(bebida)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@BebidaMain, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerBebidas()
                    limpiarCampos()
                    limpiarObjeto()

                } else {
                    Toast.makeText(this@BebidaMain, "ERROR ADD", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun actualizarBebida() {

        this.bebida.name = binding.etnombrebebida.text.toString()
        this.bebida.descripcion = binding.etdescripcionbebida.text.toString()
        this.bebida.precio = binding.etpreciobebida.text.toString()



        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.actualizarBebida(bebida.id,bebida)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@BebidaMain, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerBebidas()
                    limpiarCampos()
                    limpiarObjeto()

                    binding.btnAddUpdatebebida.setText("Agregar Producto")
                    binding.btnAddUpdatebebida.backgroundTintList = resources.getColorStateList(R.color.white)
                    isEditando = false
                }
            }
        }
    }

    fun limpiarCampos() {
        binding.etnombrebebida.setText("")
        binding.etdescripcionbebida.setText("")
        binding.etpreciobebida.setText("")

    }

    fun limpiarObjeto() {
        this.bebida.id = -1
        this.bebida.name = ""
        this.bebida.descripcion = ""
        this.bebida.precio = ""


    }

    override fun editarBebida(bebida: Bebida) {
        binding.etnombrebebida.setText(bebida.name)
        binding.etdescripcionbebida.setText(bebida.descripcion)
        binding.etpreciobebida.setText(bebida.precio)



        binding.btnAddUpdatebebida.setText("Actualizar Bebida")
        binding.btnAddUpdatebebida.backgroundTintList = resources.getColorStateList(R.color.white)
        this.bebida = bebida
        isEditando = true
    }

    override fun borrarBebida(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.borrarProducto(id)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@BebidaMain, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerBebidas()
                }
            }
        }
    }
}