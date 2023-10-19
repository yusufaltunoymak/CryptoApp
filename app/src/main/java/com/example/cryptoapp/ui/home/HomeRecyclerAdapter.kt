package com.example.cryptoapp.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.RecyclerRowBinding
import com.example.cryptoapp.model.home.Data
import com.example.cryptoapp.util.loadImage


class HomeRecyclerAdapter
    : PagingDataAdapter<Data, HomeRecyclerAdapter.ViewHolder>(CryptoDiffCallBack()) {

    class ViewHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(coin: Data) {
            binding.apply {
                tvRowTitle.text = coin.name
                tvRowSymbol.text = coin.symbol
                val imageUrl = "${coin.id}.png"
                // https://s2.coinmarketcap.com/static/img/coins/64x64/$coinImage.png
                ivRowImage.loadImage(imageUrl)
                val price = coin.quote?.uSD?.price
                val formattedPrice = itemView.context.getString(R.string.inputDollarText, price.toString())
                tvRowValue.text = formattedPrice
            }
            binding.root.setOnClickListener {
                if (coin.symbol != null) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(coin.symbol)
                    Navigation.findNavController(binding.root).navigate(action)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = getItem(position)
        if (coin != null) {
            holder.bind(coin)
        }
    }

    class CryptoDiffCallBack : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

