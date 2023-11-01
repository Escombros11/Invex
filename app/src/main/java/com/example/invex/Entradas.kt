package com.example.invex

import IActions.IApi
import Models.mEntrada
import Models.mEntries
import Utilerias.RequestManager
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import core.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Entradas : AppCompatActivity() {
    private val aListaEntrada: ArrayList<mEntries> = arrayListOf()
    private var progressIndicator: ConstraintLayout? = null
    private var recyclerEntrada : RecyclerView? = null
    private var swipeRefreshEntrada: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entradas)
        recyclerEntrada = findViewById<RecyclerView>(R.id.recyclerEntrada)
        progressIndicator = findViewById(R.id.progressIndicator)
        swipeRefreshEntrada = findViewById<View>(R.id.swipeRefreshEntrada) as SwipeRefreshLayout

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        swipeRefreshEntrada?.setOnRefreshListener {
            swipeRefreshEntrada?.setColorSchemeResources(
                R.color.fondoazul,
                R.color.naranja,
                R.color.azul
            )
            fn_ConsultaEntradas(0)
        }


        fn_ConsultaEntradas(1)

    }
    private fun fn_ConsultaEntradas(aSwipe: Int)
    {
        this.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE )
        if (aSwipe == 1)
            progressIndicator!!.visibility = View.VISIBLE


        val requestManager = RequestManager(Constants.BASE_URL_API)
        requestManager.create(IApi::class.java)
            .ConsultaPais()
            .enqueue(object : Callback<mEntrada?> {
                override fun onResponse(
                    call: Call<mEntrada?>,
                    response: Response<mEntrada?>
                ) {
                    try {
                        aListaEntrada.clear()
                        aListaEntrada.addAll(response.body()!!.entries)
                        val adapter = AdapterEntrada(this@Entradas, aListaEntrada)
                        recyclerEntrada!!.setHasFixedSize(true)
                        val layoutManager = LinearLayoutManager(this@Entradas)
                        recyclerEntrada!!.layoutManager = layoutManager
                        recyclerEntrada!!.adapter = adapter
                        this@Entradas?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

                        if (aSwipe == 1)
                            progressIndicator!!.visibility = View.GONE
                        else
                            swipeRefreshEntrada?.isRefreshing = false

                    } catch (e: Exception) {
                        window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                        if (aSwipe == 1)
                            progressIndicator!!.visibility = View.GONE
                        else
                            swipeRefreshEntrada?.isRefreshing = false

                        var adf:String=  e.message.toString()
                    }
                }

                override fun onFailure(call: Call<mEntrada?>, t: Throwable) {
                    this@Entradas?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    if (aSwipe == 1)
                        progressIndicator!!.visibility = View.GONE
                    else
                        swipeRefreshEntrada?.isRefreshing = false

                    var adf:String=  t.message.toString()
                }
            })

    }

    fun onClickLink(Entrada: mEntries)
    {
        val mDialog = entradalink(Entrada.Link.toString())
        val fragmentManager: FragmentManager = supportFragmentManager
        mDialog.show(fragmentManager, "Entrada Link")
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}