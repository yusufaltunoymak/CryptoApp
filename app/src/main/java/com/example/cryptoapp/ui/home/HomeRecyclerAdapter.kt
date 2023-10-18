package com.example.cryptoapp.ui.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.databinding.RecyclerRowBinding
import com.example.cryptoapp.model.home.Data
import com.example.cryptoapp.util.loadImage


class HomeRecyclerAdapter(private var coinList: List<Data>) : RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {


    class ViewHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
        private var onItemClickListener: (Data) -> Unit = {}

        fun bind(coin : Data) {
            binding.apply {
                tvRowTitle.text = coin.name
                tvRowSymbol.text = coin.symbol
                val imageUrl = "${coin.id}.png"
                //https://s2.coinmarketcap.com/static/img/coins/64x64/$coinImage.png
                ivRowImage.loadImage(imageUrl)
                tvRowValue.text = coin.quote?.uSD?.price.toString()


            }
            binding.root.setOnClickListener {
                onItemClickListener.invoke(coin)
                if(coin.symbol != null) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(coin.symbol)
                    Navigation.findNavController(binding.root).navigate(action)
                }
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