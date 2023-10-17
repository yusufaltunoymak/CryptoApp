package com.example.cryptoapp.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.databinding.RecyclerRowBinding
import com.example.cryptoapp.model.home.Data


class HomeRecyclerAdapter(private var coinList: List<Data>) : RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {


    class ViewHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
        private var onItemClickListener: (Data) -> Unit = {}

        fun bind(coin : Data) {
            binding.apply {
                tvRowTitle.text = coin.name
                tvRowSymbol.text = coin.symbol

            }
            binding.root.setOnClickListener {
                onItemClickListener.invoke(coin)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent, false))

    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = coinList[position]
        holder.bind(coin)

    }
}