package com.andis.kigar.viewmodel

import androidx.lifecycle.ViewModel
import com.andis.kigar.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository
): ViewModel() {
    
}