package com.example.invex

import Models.mEntries
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class AdapterEntrada(private val itemClickListener: Entradas, private val aObjeto: ArrayList<mEntries>) : RecyclerView.Adapter<AdapterEntrada.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtCategoríaDes: TextView
        var txtDescripcionDes: TextView
        var txtApiDes: TextView
        var txtLinkDes: TextView
        var btnVisitarLink: Button

        init {
            txtCategoríaDes = itemView.findViewById(R.id.txtCategoríaDes)
            txtDescripcionDes = itemView.findViewById(R.id.txtDescripcionDes)
            txtApiDes = itemView.findViewById(R.id.txtApiDes)
            txtLinkDes = itemView.findViewById(R.id.txtLinkDes)
            btnVisitarLink = itemView.findViewById(R.id.btnVisitarLink)
        }
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        try {
            viewHolder.txtCategoríaDes.text = aObjeto?.get(i)?.Category .toString()
            viewHolder.txtDescripcionDes.text =  aObjeto?.get(i)?.Description.toString()
            viewHolder.txtApiDes.text = aObjeto?.get(i)?.API.toString()
            viewHolder.txtLinkDes.text = aObjeto?.get(i)?.Link.toString()
        }
        catch (ex:Exception)
        {
            var asf: String = ex.message.toString()
        }
    }

    override fun getItemCount(): Int {
        return aObjeto?.size!!
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AdapterEntrada.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_imagen_entradas, viewGroup, false)

        val itemholder = AdapterEntrada.ViewHolder(v)
        try {

            itemholder.btnVisitarLink.setOnClickListener{
                val position = itemholder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                    ?: return@setOnClickListener
                itemClickListener.onClickLink(aObjeto.get(position))
            }


            return itemholder

        }
        catch (ex: Exception)
        {
            var arf: String = ex.message.toString()
            return itemholder
        }

    }
}