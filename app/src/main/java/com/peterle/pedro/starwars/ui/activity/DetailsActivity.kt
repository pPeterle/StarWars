package com.peterle.pedro.starwars.ui.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.peterle.pedro.presentation.DetailsViewModel
import com.peterle.pedro.presentation.ViewState
import com.peterle.pedro.presentation.model.FilmDetailsView
import com.peterle.pedro.starwars.R
import com.peterle.pedro.starwars.di.ViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DetailsViewModel
    private lateinit var dialog: ProgressDialog

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, DetailsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        AndroidInjection.inject(this)

        val id = intent.extras.getString("id")

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)

        viewModel.getViewState().observe(this, Observer {
            it?.let { handleStatus(it) }
        })

        viewModel.fetchData(id)

        dialog = ProgressDialog(this)
        dialog.setTitle("Loading")

    }

    private fun handleStatus(viewState: ViewState<FilmDetailsView>) {
        when (viewState.status) {
            ViewState.Status.LOADING -> dialog.show()
            ViewState.Status.SUCCESS -> viewState.data?.let { handleSuccess(viewState.data!!)}
            ViewState.Status.ERROR -> viewState.error?.let { handleError(viewState.error!!) }
        }
    }

    private fun handleSuccess(data: FilmDetailsView) {
        dialog.dismiss()
        details_title.text = data.title
        details_releaseDate.text = data.releaseDate
        details_people.text = data.characters.joinToString { it.name }
        details.text = data.planets.joinToString { it.name }
    }

    private fun handleError(error: Throwable) {
        dialog.dismiss()
        error.printStackTrace()
        Toast.makeText(this, "Algum erro aconteceu, tente novamente", Toast.LENGTH_SHORT).show()
    }
}
