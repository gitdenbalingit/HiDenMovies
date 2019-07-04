package com.hiden.movies.presentation.ui.genericview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.hiden.movies.R
import com.hiden.movies.presentation.util.use
import kotlinx.android.synthetic.main.generic_action_view.view.*
import org.jetbrains.anko.imageResource

class GenericActionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(
    context, attrs,
    defStyleAttr
) {

    private var block: (() -> Unit?)? = null
    private var secondaryBlock: (() -> Unit?)? = null

    init {
        inflate(context, R.layout.generic_action_view, this)

        context.obtainStyledAttributes(attrs, R.styleable.GenericActionView, defStyleAttr,0)
            .use {
                it.getString(R.styleable.GenericActionView_emptyTitle)?.let {
                    view_title.text = it
                }

                it.getResourceId(R.styleable.GenericActionView_emptyDrawable, 0)?.let {
                    view_icon.imageResource = it
                }

                it.getString(R.styleable.GenericActionView_emptyMessage)?.let {
                    view_description.text = it
                }

                it.getString(R.styleable.GenericActionView_emptyActionMessage)?. let {
                    view_button_action.text = it
                }

                it.getString(R.styleable.GenericActionView_emptySecondaryActionMessage)?. let {
                    view_secondary_action.text = it
                }
            }


        view_button_action.setOnClickListener {
            this.block?.invoke()
        }

        view_secondary_action.setOnClickListener {
            this.secondaryBlock?.invoke()
        }
    }

    fun removeActionButton(){
        view_button_action.visibility = View.GONE
    }


    fun setAction(block: () -> Unit) {
        this.block = block
    }

    fun setSecondaryAction(block: () -> Unit){
        this.secondaryBlock = block
    }

    fun setTitle(title: String) {
        view_title.text = title
    }

    fun setDescription(description: String) {
        view_description.text = description
    }

    fun setActionMessage(actionMessage: String) {
        view_button_action.text = actionMessage
    }

    fun setSecondaryActionMessage(actionMessage: String){
        view_secondary_action.text = actionMessage
    }

}