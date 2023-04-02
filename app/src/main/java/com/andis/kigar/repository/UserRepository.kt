package com.andis.kigar.repository

import com.andis.kigar.retrofit.EndPointAPI
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: EndPointAPI
) {
}