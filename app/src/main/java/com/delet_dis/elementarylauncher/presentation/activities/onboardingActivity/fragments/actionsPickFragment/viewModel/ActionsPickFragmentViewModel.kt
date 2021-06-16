package com.delet_dis.elementarylauncher.presentation.activities.onboardingActivity.fragments.actionsPickFragment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.delet_dis.elementarylauncher.data.models.Card
import com.delet_dis.elementarylauncher.data.models.LayoutType
import com.delet_dis.elementarylauncher.domain.repositories.DatabaseRepository
import com.delet_dis.elementarylauncher.domain.repositories.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class ActionsPickFragmentViewModel @Inject constructor(
    application: Application,
    private val databaseRepository: DatabaseRepository
) : AndroidViewModel(application) {
    private val _databaseRecordingsLiveData = MutableLiveData<List<Card>>()
    val databaseRecordingsLiveData: LiveData<List<Card>>
        get() = _databaseRecordingsLiveData

    private val _isAvailableToEndFirstSetup = MutableLiveData(false)
    val isAvailableToEndFirstSetup: LiveData<Boolean>
        get() = _isAvailableToEndFirstSetup

    init {
        loadDatabaseRecordingsAsCards()
        loadDatabaseRecordingsAsEntitiesParent()
    }

    private fun loadDatabaseRecordingsAsCards() {
        viewModelScope.launch(Dispatchers.IO) {

            if (SharedPreferencesRepository(getApplication()).getLayoutType() == LayoutType.TWO_BY_TWO) {
                databaseRepository.deleteAtPosition(5)
                databaseRepository.deleteAtPosition(6)
            }

            databaseRepository.getAllDatabaseRecordingsAsCards(getApplication()).collect { list ->
                _databaseRecordingsLiveData.postValue(list)
            }
        }
    }

    private fun loadDatabaseRecordingsAsEntitiesParent() {
        viewModelScope.launch {
            databaseRepository.getAllDatabaseRecordingsAsEntitiesParentListFlow()
                .collect { list ->
                    if (list.isNotEmpty()) {
                        _isAvailableToEndFirstSetup.postValue(true)
                    } else {
                        _isAvailableToEndFirstSetup.postValue(false)
                    }
                }
        }
    }
}