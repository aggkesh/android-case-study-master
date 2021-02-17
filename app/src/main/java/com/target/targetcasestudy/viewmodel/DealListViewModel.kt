package com.target.targetcasestudy.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.target.targetcasestudy.R
import com.target.targetcasestudy.database.toDealItem
import com.target.targetcasestudy.repo.DealRepo
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

// Type alias for parameterized ScreenState for this view model
typealias DealListScreenState = ScreenState<Int, Int, Unit>

class DealListViewModel @ViewModelInject constructor(private val dealRepo: DealRepo) : ViewModel() {

    private val _screenState = MutableLiveData<DealListScreenState>()

    val screenState: LiveData<DealListScreenState>
        get() = _screenState

    val deals = dealRepo.loadDeals().map { deals -> deals.map { it.toDealItem() } }.asLiveData()

    init {
        loadDeals()
    }

    fun loadDeals() {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading(R.string.progress_message_loading)
            _screenState.value = try {
                dealRepo.fetchAndSaveDeals()
                ScreenState.Data(Unit)
            } catch (ex: Exception) {
                ScreenState.Error(R.string.alert_message_something_went_wrong)
            }
        }
    }

}