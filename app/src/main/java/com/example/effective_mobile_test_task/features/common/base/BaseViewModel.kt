package com.example.effective_mobile_test_task.features.common.base

import android.content.Intent
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<State: BaseState, Action: BaseAction, Intent: BaseIntent>: ViewModel() {
    protected val _state by lazy { MutableStateFlow(createInitState())}
    protected val state: State get() = _state.value
    val screenState: StateFlow<State>  = _state.asStateFlow()

    protected val _action = MutableSharedFlow<Action>(replay = 1)
    val action get() = _action.asSharedFlow()

    private val _intent = MutableSharedFlow<Intent>()
    protected val intent = _intent.asSharedFlow()

    abstract fun createInitState():State

    abstract fun handleIntent(intent: Intent)

    protected fun sendAction(action: Action){
        _action.tryEmit(action)
    }



}