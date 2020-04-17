package com.test.core

/**
 * Livedata data type to prevent multiple use
 */
class HandleOnceLiveEvent<T>(private val content: T) {
    internal var hasBeenHandled = false

    /**
     * Returns the content and prevents its use again.
     */
    val contentIfNotHandled: T?
        get() {
            return if (hasBeenHandled) {
                null
            } else {
                hasBeenHandled = true
                this.content
            }
        }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T {
        return this.content
    }
}