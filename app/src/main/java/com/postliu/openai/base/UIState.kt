package com.postliu.openai.base

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Throw(val throwable: Throwable) : UIState<Nothing>()
    data class Failed(val message: String) : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()

    companion object {

        inline val <reified T> UIState<T>.successData get() = if (this is Success) data else null

        inline val <reified T> UIState<T>.failedMessage get() = if (this is Failed) message else ""

        inline val <reified T> UIState<T>.throwableMessage get() = if (this is Throw) throwable else RuntimeException()

        inline fun <T, R> UIState<T>.map(crossinline block: (T) -> R): UIState<R> {
            return when (this) {
                is Loading -> Loading
                is Throw -> Throw(throwable)
                is Failed -> Failed(message)
                is Success -> Success(block(data))
            }
        }

        inline fun <reified T> UIState<T>.doStart(block: () -> Unit) = apply {
            if (this is Loading) {
                block.invoke()
            }
        }

        inline fun <reified T> UIState<T>.doCatch(block: (Throwable) -> Unit) = apply {
            if (this is Throw) {
                block.invoke(throwable)
            }
        }

        inline fun <reified T> UIState<T>.doFailed(block: (String) -> Unit) = apply {
            if (this is Failed) {
                block.invoke(message)
            }
        }

        inline fun <reified T> UIState<T>.doSuccess(block: (T) -> Unit) = apply {
            if (this is Success) {
                block.invoke(data)
            }
        }
    }
}