package com.hiden.challenge.presentation.ui.choosegame

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.hiden.challenge.R
import com.hiden.challenge.data.entity.GameResultResponse
import com.hiden.challenge.presentation.AppActivity
import com.hiden.challenge.presentation.common.adapter.GamesAdapter
import com.hiden.challenge.presentation.common.ext.observe
import com.hiden.challenge.presentation.common.ext.withViewModel
import com.hiden.challenge.presentation.navigation.ChooseStageNavigator
import com.hiden.challenge.presentation.ui.choosestage.ChooseStageActivity
import kotlinx.android.synthetic.main.activity_choose_game.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject


class ChooseGameActivity : AppActivity(), GamesAdapter.Delegate {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject lateinit var gamesAdapter: GamesAdapter
    @Inject lateinit var chooseStageNavigator: ChooseStageNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_game)
        setupToolbar()
        setupList()
        observerViewModel()

    }

    private fun setupList(){
        games_recycler.apply {
            adapter = gamesAdapter
        }
    }

    private fun observerViewModel(){
        withViewModel<ChooseGameViewModel>(viewModelFactory) {
            getGames()
            observe(gamesList, ::displayGames)
        }
    }

    private fun displayGames(result: Result<List<GameResultResponse>>?){
        if(result == null) return
        result.onSuccess { list ->
            Log.v("PIA","size = "+list.size)
            gamesAdapter.setItems(list)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Choose Game"
            titleColor = resources.getColor(R.color.primary_dark_text_color)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onGameItemClicked(gameResultResponse: GameResultResponse) {
        chooseStageNavigator.navigate()
    }


}