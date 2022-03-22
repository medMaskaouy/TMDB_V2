package com.sgma.prod.adapters.compositeAdapter

/**
 * In case we don't want to use DiffUtils we implement this interface
 */
interface DisplayableItem : DelegateItem {
    override fun itemId() = Unit
    override fun itemContent() = Unit
}