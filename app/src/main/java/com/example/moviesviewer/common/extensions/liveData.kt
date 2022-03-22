package com.example.moviesviewer.common.extensions

import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.MutableLiveData

internal fun MutableLiveData<String>.mutableBinding(editText : EditText){
    editText.doAfterTextChanged {
        if(editText.text.toString()!=this.value){
            this.value=editText.text.toString()
        }
    }

    if (value != editText.text.toString()) {
        editText.setText(value)
    }
}
