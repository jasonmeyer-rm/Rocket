package com.app.rocket.feature.games.ui

import androidx.lifecycle.*
import com.app.rocket.feature.games.data.models.Game
import com.app.rocket.feature.games.repository.GamesRepository
import com.app.rocket.utils.ApiResult
import kotlinx.coroutines.Dispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(private val gamesRepository: GamesRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean>
        get() = _isError

    private val _gamesResponse = MutableLiveData<ApiResult<List<Game>>>()
    val gamesResponse: LiveData<ApiResult<List<Game>>>
        get() = _gamesResponse

    init {
        getGames()
    }

    private fun getGames() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            gamesRepository.getGames().let { resp ->
                    _isLoading.postValue(false)
                    if (resp.results.isNotEmpty()) {
                        _gamesResponse.postValue(ApiResult.success(resp.results))
                    } else {
                        _isError.postValue(true)
                    }
                }
        }
    }
}

