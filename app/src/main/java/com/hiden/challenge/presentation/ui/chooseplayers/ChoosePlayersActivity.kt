package com.hiden.challenge.presentation.ui.chooseplayers

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import com.google.android.material.chip.Chip
import com.hiden.challenge.R
import com.hiden.challenge.data.entity.PlayerResponse
import com.hiden.challenge.presentation.AppActivity
import com.hiden.challenge.presentation.common.adapter.PlayersAdapter
import com.hiden.challenge.presentation.common.ext.observe
import com.hiden.challenge.presentation.common.ext.withViewModel
import kotlinx.android.synthetic.main.activity_choose_players.*
import javax.inject.Inject

class ChoosePlayersActivity : AppActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var playersAdapter: PlayersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_players)
        observerViewModel()
        setupList()
        setupToolbar()

    }

    private fun setupList() {
        players_recycler.apply {
            adapter = playersAdapter
        }

        val tracker = SelectionTracker.Builder<Long>(
                "mySelection",
                players_recycler,
                PlayerItemKeyProvider(players_recycler),
                PlayerItemDetailsLookUp(players_recycler),
                StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
                SelectionPredicates.createSelectAnything()
        ).build()
        playersAdapter.tracker = tracker



        tracker.addObserver(
                object : SelectionTracker.SelectionObserver<Long>() {


                    override fun onSelectionChanged() {
                        selected_count.text = tracker.selection.size().toString() + "/" + "8"


                        val selectedItems = ArrayList<Pair<PlayerResponse, Long>>()

                        chip_container.removeAllViews()


                        tracker.selection.forEach {
                            val selectedItem = Pair<PlayerResponse, Long>(playersAdapter.items[it.toInt()], it)
                            selectedItems.add(selectedItem)
                        }


                        selectedItems.distinctBy { it.first.id }

                        selectedItems.forEach { item ->
                            val selectedItem = item.first
                            val chip = Chip(this@ChoosePlayersActivity)
                            chip.text = selectedItem.profile.firstName + " " + selectedItem.profile.lastName
                            chip.chipIcon = ContextCompat.getDrawable(this@ChoosePlayersActivity, R.drawable.ic_add_circle_gray_24dp)
                            chip.isCloseIconVisible = true
                            chip.closeIcon = ContextCompat.getDrawable(this@ChoosePlayersActivity, R.drawable.ic_remove_circle_blue_24dp)

                            chip.isClickable = true
                            chip.isCheckable = false
                            chip_container.addView(chip as View)
                            chip.setOnCloseIconClickListener {
                                chip_container.removeView(chip as View)
                                tracker.deselect(item.second)
                            }
                        }


                    }
                }
        )
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Choose Players"
            titleColor = resources.getColor(R.color.primary_dark_text_color)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    private fun observerViewModel() {
        withViewModel<ChoosePlayersViewModel>(viewModelFactory) {
            getPlayers()
            observe(playersList, ::displayPlayers)
        }
    }

    private fun displayPlayers(result: Result<List<PlayerResponse>>?) {
        if (result == null) return
        result.onSuccess { list ->
            playersAdapter.setItems(list)
            Log.v("PIA", "size p - " + list.size)
        }
    }


}