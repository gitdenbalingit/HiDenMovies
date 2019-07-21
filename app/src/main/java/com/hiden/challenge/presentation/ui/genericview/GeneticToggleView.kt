package com.hiden.challenge.presentation.ui.genericview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.StringRes
import com.hiden.challenge.R
import com.hiden.challenge.presentation.util.use
import kotlinx.android.synthetic.main.generic_toggle_view.view.*

class GeneticToggleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(
    context, attrs,
    defStyleAttr
), View.OnClickListener {

    var isChecked: Boolean
        get() = toggle_button.isChecked
        set(value) {
            toggle_button.isChecked = value
        }
    private var toggleViewListener: ToggleViewListener? = null


    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.generic_toggle_view, this)
        toggle_button.setOnClickListener(this)

        context.obtainStyledAttributes(attrs, R.styleable.GenericToggleView, defStyleAttr, 0)
            .use {
                it.getString(R.styleable.GenericToggleView_toggleTitle)?.let {
                    toggle_title.text = it
                }
                it.getString(R.styleable.GenericToggleView_toggleDesc)?.let {
                    toggle_desc.text = it
                }
            }
    }

    fun setToggleViewListener(toggleViewListener: ToggleViewListener) {
        this.toggleViewListener = toggleViewListener
    }

    fun setToggleTitleText(@StringRes stringId: Int) {
        toggle_title.text = resources.getString(stringId)
    }

    fun setToggleDescText(@StringRes stringId: Int) {
        toggle_desc.text = resources.getString(stringId)

    }

    fun bind(checked: Boolean?) {
        isChecked = checked == true
    }


    override fun onClick(v: View?) {
        toggleViewListener?.onChanged()
    }

    interface ToggleViewListener {
        fun onChanged()
    }

}