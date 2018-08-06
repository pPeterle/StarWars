package com.peterle.pedro.starwars.ui.activity

import android.app.ProgressDialog
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.peterle.pedro.presentation.MainViewModel
import com.peterle.pedro.presentation.ViewState
import com.peterle.pedro.presentation.model.FilmView
import com.peterle.pedro.starwars.R
import com.peterle.pedro.starwars.di.ViewModelFactory
import com.peterle.pedro.starwars.ui.adapter.MainRecyclerAdapter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel

    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MainViewModel::class.java)

        viewModel.getViewState().observe(this, Observer {
            it?.let { handleStatus(it) }
        })

        dialog = ProgressDialog(this)
        dialog.setTitle("Loading")
    }

    private fun handleStatus(viewState: ViewState<List<FilmView>>) {
        when (viewState.status) {
            ViewState.Status.LOADING -> dialog.show()
            ViewState.Status.SUCCESS -> viewState.data?.let { handleSuccess(viewState.data!!) }
            ViewState.Status.ERROR -> viewState.error?.let { handleError(viewState.error!!) }
        }
    }

    private fun handleSuccess(data: List<FilmView>) {
        dialog.dismiss()

        data.map {
            val image = when (it.episodeId) {
                "4" -> R.drawable.a_new_hope
                "5" -> R.drawable.the_empire_strikes_back
                "6" -> R.drawable.return_of_the_jedi
                "1" -> R.drawable.the_phantom_menace
                "2" -> R.drawable.attack_of_the_clones
                "3" -> R.drawable.revenge_of_the_sith
                "7" -> R.drawable.the_force_awakens
                else -> 0
            }

            it.image = image
        }

        main_recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = MainRecyclerAdapter(data) {
                val intent = DetailsActivity.getStartIntent(this@MainActivity)
                intent.putExtra("id", it.episodeId)
                intent.putExtra("image", it.image)
                startActivity(intent)
            }
        }
    }

    private fun handleError(error: Throwable) {
        dialog.dismiss()
        error.printStackTrace()
        Toast.makeText(this, "Algum erro aconteceu, tente novamente", Toast.LENGTH_SHORT).show()
    }
}
