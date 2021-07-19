package io.github.studio22.probably.distributions_module

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.studio22.probably.R
import io.github.studio22.probably.distributions_module.view.SpecificDistributionActivity

class DistributionAdapter : RecyclerView.Adapter<DistributionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //var plot: ImageView = view.findViewById(R.id.distribution_plot)
        var distributionName: TextView = itemView.findViewById(R.id.distribution_name)
        var formulae: ImageView = itemView.findViewById(R.id.distribution_formulae)

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, SpecificDistributionActivity::class.java)
                intent.putExtra("distribution_name", distributionName.text)
                itemView.context.startActivity(intent)
            }
        }
    }

    var titles = arrayOf(
        "Биноминальное", "Геометрическое", "Гипергеометрическое",
        "Пуассона", "Равномерное", "Экспоненциальное"
    )
    private var formulas = arrayOf(
        R.drawable.binomial_formulae,
        R.drawable.binomial_formulae,
        R.drawable.binomial_formulae,
        R.drawable.binomial_formulae,
        R.drawable.binomial_formulae,
        R.drawable.binomial_formulae,
    )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.distribution_fragment, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.distributionName.text = titles[position]
        holder.formulae.setImageResource(formulas[position])
        //holder.plot.setImageResource(plots[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

}