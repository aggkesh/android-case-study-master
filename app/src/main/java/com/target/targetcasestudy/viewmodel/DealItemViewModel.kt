package com.target.targetcasestudy.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealDetailItem
import com.target.targetcasestudy.database.toDealDetailItem
import com.target.targetcasestudy.repo.DealRepo
import kotlinx.coroutines.launch

// Type alias for parameterized ScreenState for this view model
typealias DealItemScreenState = ScreenState<Int, Int, DealDetailItem>

class DealItemViewModel @ViewModelInject constructor(
    private val dealRepo: DealRepo,
    @Assisted private val state: SavedStateHandle
) : ViewModel() {
    private val dealId = state.get<Long>("deal_id") ?: 0L

    private val _screenState = MutableLiveData<DealItemScreenState>()

    val screenState: LiveData<DealItemScreenState>
        get() = _screenState

    init { 
        loadDealDetail()
    }

    fun loadDealDetail() {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading(R.string.progress_message_loading)
            _screenState.value = dealRepo.loadDeal(dealId)
                ?.let { ScreenState.Data(it.toDealDetailItem()) }
                ?: kotlin.run { ScreenState.Error(R.string.alert_message_something_went_wrong) }
        }
    }
}