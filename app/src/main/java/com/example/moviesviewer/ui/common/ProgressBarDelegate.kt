package com.example.moviesviewer.ui.common


import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.moviesviewer.R
 import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class ProgressBarDelegate(
        val context: Fragment

) : ReadOnlyProperty<Fragment, ProgressBar> {

    private var progressBar: ProgressBar? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): ProgressBar {
        if (progressBar != null) {
            return progressBar as ProgressBar
        }
        val inflater = context.layoutInflater
        val view = inflater.inflate(R.layout.progress_dialog_view, null)
        val set = ConstraintSet()
        progressBar = view.findViewById(R.id.pbar)
        (context.view as ConstraintLayout).addView(view)

        set.constrainHeight(view.getId(),
                ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(view.getId(),
                ConstraintSet.WRAP_CONTENT);

        set.connect(view.getId(), ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        set.connect(view.getId(), ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        set.connect(view.getId(), ConstraintSet.TOP,
                ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        set.connect(view.getId(), ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        set.applyTo(context.view as ConstraintLayout)

        return progressBar!!
    }
}
