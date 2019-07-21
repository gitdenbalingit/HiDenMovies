package com.hiden.challenge.presentation.ui.choosestage

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.hiden.challenge.R
import com.hiden.challenge.data.entity.StagesResponse
import com.hiden.challenge.presentation.AppActivity
import com.hiden.challenge.presentation.common.adapter.StagesAdapter
import com.hiden.challenge.presentation.common.ext.observe
import com.hiden.challenge.presentation.common.ext.withViewModel
import com.hiden.challenge.presentation.navigation.ChoosePlayersNavigator
import com.hiden.challenge.presentation.navigation.ChooseStageNavigator
import com.hiden.challenge.presentation.ui.chooseplayers.ChoosePlayersActivity
import kotlinx.android.synthetic.main.activity_choose_stage.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class ChooseStageActivity : AppActivity(), StagesAdapter.Delegate {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var stagesAdapter: StagesAdapter
    @Inject
    lateinit var choosePlayersNavigator: ChoosePlayersNavigator


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
        choosePlayersNavigator.navigate()
    }


}