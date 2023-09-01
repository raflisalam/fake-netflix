package com.raflisalam.fakeneflix.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.fakeneflix.common.Status
import com.raflisalam.fakeneflix.common.utils.ActorsIdStateFlow
import com.raflisalam.fakeneflix.common.utils.MoviesIdStateFlow
import com.raflisalam.fakeneflix.domain.model.actors.Actors
import com.raflisalam.fakeneflix.domain.model.actors.ActorsDetail
import com.raflisalam.fakeneflix.domain.model.movies.MovieDetails
import com.raflisalam.fakeneflix.domain.usecase.get_detail_actors.GetDetailActorsUseCase
import com.raflisalam.fakeneflix.domain.usecase.get_popular_actors.GetPopularActorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val actorsPopular_useCase: GetPopularActorsUseCase,
    private val getDetailActorsUseCase: GetDetailActorsUseCase
): ViewModel() {

    private val actorsIdStateFlow = ActorsIdStateFlow.getCurrentIdActors()

    private val _getActorsPopular = MutableLiveData<Status<List<Actors>>>()
    val getActorsPopular: LiveData<Status<List<Actors>>> get() = _getActorsPopular

    fun fetchActorsPopular() {
        viewModelScope.launch {
            _getActorsPopular.value = Status.Loading()
            try {
                actorsPopular_useCase.invoke(1).collect {
                    _getActorsPopular.value = it
                }
            } catch (e: Exception) {
                _getActorsPopular.value = Status.Error("Failed to fetch popular movie list")
            }
        }
    }

    private val _getActorsDetail = MutableLiveData<Status<ActorsDetail>>()
    val actorsDetail: LiveData<Status<ActorsDetail>> get() = _getActorsDetail

    fun fetchActorsDetail() {
        viewModelScope.launch {
            _getActorsDetail.value = Status.Loading()
            try {
                actorsIdStateFlow.collectLatest { actorsId ->
                    getDetailActorsUseCase.invoke(actorsId).collect {
                        _getActorsDetail.value = it
                    }
                }
            } catch (e: Exception) {
                _getActorsDetail.value = Status.Error("Failed to fetch now playing movie list")
            }
        }
    }


}