package io.github.studio22.probably

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import io.github.studio22.probably.combinatorics_module.CombinatoricsActivity
import io.github.studio22.probably.distributions_module.DistributionActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)
    }

    fun onClickCombinatorics(view: View) {
        val intent = Intent(this, CombinatoricsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun onCLickDistributions(view : View) {
        val intent = Intent(this, DistributionActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

}