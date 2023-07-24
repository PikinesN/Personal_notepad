package com.example.dnevnik.adpter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dnevnik.Create_Note
import com.example.dnevnik.R
import com.example.dnevnik.db.ListItem
import com.example.dnevnik.db.MyDbManager
import com.example.dnevnik.db.MyintentConstant
import com.example.dnevnik.preview

class MyAdapter(listMain:ArrayList<ListItem>, contextM: Context): RecyclerView.Adapter<MyAdapter.MyHolder>() {

    var listArray = listMain
    var context = contextM


    class MyHolder(itemView: View, contextV: Context) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val context = contextV

        fun setData(item:ListItem) {
            tvTitle.text = item.title
            itemView.setOnClickListener {
                val intent = Intent(context, preview::class.java).apply {


                    putExtra(MyintentConstant.I_TITLE_KEY, item.title)
                    putExtra(MyintentConstant.I_DISC_KEY, item.desc)

                }
                context.startActivity(intent)


            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyHolder(inflater.inflate(R.layout.rc_item_shablone,parent,false),context)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.setData(listArray[position])
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(listItems:List<ListItem>){
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }

    fun removeItem(pos: Int,dbManager: MyDbManager){

        dbManager.renoveItemFromDB(listArray[pos].id.toString())
        listArray.removeAt(pos)
        notifyItemRangeChanged(0,listArray.size)
        notifyItemRemoved(pos)

    }
}