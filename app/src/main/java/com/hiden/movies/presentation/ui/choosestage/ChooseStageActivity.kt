package com.hiden.movies.presentation.ui.choosestage

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.hiden.movies.R
import com.hiden.movies.data.entity.StagesResponse
import com.hiden.movies.presentation.AppActivity
import com.hiden.movies.presentation.common.adapter.StagesAdapter
import com.hiden.movies.presentation.common.ext.observe
import com.hiden.movies.presentation.common.ext.withViewModel
import kotlinx.android.synthetic.main.activity_choose_stage.*
import javax.inject.Inject

class ChooseStageActivity : AppActivity(), StagesAdapter.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var stagesAdapter: StagesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_stage)
        setupToolbar()
        setupList()
        observerViewModel()

    }

    private fun setupList(){
        stages_recycler.apply {
            adapter = stagesAdapter
        }
    }

    private fun observerViewModel(){
        withViewModel<ChooseStageViewModel>(viewModelFactory) {
            getStages()
            observe(stagesList, ::displayStages)
        }
    }

    private fun displayStages(result: Result<List<StagesResponse>>?){
        if(result == null) return
        result.onSuccess { list ->
            Log.v("PIA","size = "+list.size)
            stagesAdapter.setItems(list)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Choose Stage"
            titleColor = resources.getColor(R.color.primary_dark_text_color)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onStageItemClicked(stagesResponse: StagesResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}