package com.example.effective_mobile_test_task.features.main.model

import com.example.effective_mobile_test_task.features.common.base.BaseAction

sealed interface AirTicketAction: BaseAction {
    data object NavigateToSearchScreen: AirTicketAction
}
