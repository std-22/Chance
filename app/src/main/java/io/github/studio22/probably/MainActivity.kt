package io.github.studio22.probably

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }

    fun onClickCombinatorics(view: View) {
        val intent = Intent(this, CombinatoricsActivity::class.java)
        intent.putExtra("section_name", "Комбинаторика")
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    fun onCLickDistributions(view : View) {
        val intent = Intent(this, DistributionActivity::class.java)
        intent.putExtra("section_name", "Распределения")
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}