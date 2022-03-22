package com.sgma.prod.adapters.compositeAdapter

/**
Items with DiifUtils
 */
interface DelegateItem  {

    fun itemId() : Any

    fun itemContent() : Any

    fun payload(other: Any): Payloadable = Payloadable.None

    interface Payloadable {
        object None: Payloadable
    }

}