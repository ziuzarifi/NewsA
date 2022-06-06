package ui

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.bottomNav.itemIconTintList = null
        navController = findNavController(R.id.fragmentContainerView)

        //change status bar text color to dark
        val window: Window = window
        val view: View = window.decorView
        WindowInsetsControllerCompat(window, view).isAppearanceLightStatusBars = true

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.categories -> navController?.navigate(R.id.categoriesFragment)

                R.id.sources -> navController?.navigate(R.id.sourcesFragment)

                R.id.bookmarks -> navController?.navigate(R.id.bookmarksFragment)

                R.id.account -> navController?.navigate(R.id.accountFragment)
            }
            true
        }
    }

    override fun onBackPressed() {
        when(navController?.currentDestination?.id){
            R.id.categoriesFragment, R.id.sourcesFragment, R.id.bookmarksFragment, R.id.accountFragment -> {
                AlertDialog.Builder(this)
                    .setTitle("Вы уверены,")
                    .setMessage("что хотите выйти?")
                    .setNegativeButton("Отмена", null)
                    .setPositiveButton("Да"){ dialogInterface, i ->
                        finish()
                    }
                    .create()
                    .show()
            }
        }
        super.onBackPressed()
    }

}