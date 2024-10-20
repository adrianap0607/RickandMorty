package com.example.laboratorio9.presentation.mainFlow.location.list
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.laboratorio9.presentation.room.LocationDao

class LocationListViewModelFactory(
    private val locationDao: LocationDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationListViewModel::class.java)) {
            return LocationListViewModel(locationDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
