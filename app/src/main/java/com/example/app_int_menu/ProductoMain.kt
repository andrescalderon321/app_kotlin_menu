package com.example.app_int_menu

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_int_menu.adapter.ProductoAdapter
import com.example.app_int_menu.databinding.ProductoMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductoMain : AppCompatActivity(), ProductoAdapter.OnItemClicked {

    lateinit var binding: ProductoMainBinding

    lateinit var adatador: ProductoAdapter

    var listaProductos = arrayListOf<Producto>()


    var producto = Producto(-1, "","","","","","")

    var isEditando = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ProductoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsuarios.layoutManager = LinearLayoutManager(this)
        setupRecyclerView()

        obtenerUsuarios()

        binding.btnAddUpdate.setOnClickListener {
            var isValido = validarCampos()
            if (isValido) {
                if (!isEditando) {
                    agregarProducto()
                } else {
                    actualizarProducto()
                }
            } else {
                Toast.makeText(this, "Se deben llenar los campos", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun setupRecyclerView() {
        adatador = ProductoAdapter(this, listaProductos)
        adatador.setOnClick(this@ProductoMain)
        binding.rvUsuarios.adapter = adatador

    }

    fun validarCampos(): Boolean {
        return !(binding.etnombreproducto.text.isNullOrEmpty() || binding.etprecio.text.isNullOrEmpty() || binding.etdescripcion.text.isNullOrEmpty() || binding.etdisponibilidad.text.isNullOrEmpty())
    }

    fun obtenerUsuarios() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.obtenerProductos()
            runOnUiThread {
                if (call.isSuccessful) {
                    listaProductos = call.body()!!.listaProductos
                    setupRecyclerView()
                } else {
                    Toast.makeText(this@ProductoMain, "ERROR CONSULTAR TODOS", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun agregarProducto() {

        this.producto.id = -1
        this.producto.name = binding.etnombreproducto.text.toString()
        this.producto.precio = binding.etprecio.text.toString()
        this.producto.descripcion = binding.etdescripcion.text.toString()
        this.producto.disponibilidad = binding.etdisponibilidad.text.toString()


        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.agregarProducto(producto)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@ProductoMain, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerUsuarios()
                    limpiarCampos()
                    limpiarObjeto()

                } else {
                    Toast.makeText(this@ProductoMain, "ERROR ADD", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun actualizarProducto() {

        this.producto.name = binding.etnombreproducto.text.toString()
        this.producto.precio = binding.etprecio.text.toString()
        this.producto.descripcion = binding.etdescripcion.text.toString()
        this.producto.disponibilidad= binding.etdisponibilidad.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.actualizarProducto(producto.id, producto)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@ProductoMain, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerUsuarios()
                    limpiarCampos()
                    limpiarObjeto()

                    binding.btnAddUpdate.setText("Agregar Producto")
                    binding.btnAddUpdate.backgroundTintList = resources.getColorStateList(R.color.white)
                    isEditando = false
                }
            }
        }
    }

    fun limpiarCampos() {
        binding.etnombreproducto.setText("")
        binding.etprecio.setText("")
        binding.etdescripcion.setText("")
        binding.etdisponibilidad.setText("")
    }

    fun limpiarObjeto() {
        this.producto.id = -1
        this.producto.name = ""
        this.producto.precio = ""
        this.producto.descripcion = ""
        this.producto.disponibilidad = ""
    }

    override fun editarUsuario(producto: Producto) {
        binding.etnombreproducto.setText(producto.name)
        binding.etprecio.setText(producto.precio)
        binding.etdescripcion.setText(producto.descripcion)
        binding.etdisponibilidad.setText(producto.disponibilidad)

        binding.btnAddUpdate.setText("Actualizar Usuario")
        binding.btnAddUpdate.backgroundTintList = resources.getColorStateList(R.color.white)
        this.producto = producto
        isEditando = true
    }

    override fun borrarUsuario(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.borrarProducto(id)
            runOnUiThread {
                if (call.isSuccessful) {
                    Toast.makeText(this@ProductoMain, call.body().toString(), Toast.LENGTH_LONG).show()
                    obtenerUsuarios()
                }
            }
        }
    }
}