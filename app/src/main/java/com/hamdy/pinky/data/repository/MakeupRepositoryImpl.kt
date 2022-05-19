package com.hamdy.pinky.data.repository

import com.hamdy.pinky.common.Constants
import com.hamdy.pinky.data.remote.MakeupApi
import com.hamdy.pinky.domain.model.Product
import com.hamdy.pinky.domain.repository.MakeupRepository
import javax.inject.Inject

class MakeupRepositoryImpl @Inject constructor(
    private val api: MakeupApi
) : MakeupRepository {


    override suspend fun getProducts(): List<Product> {
        return api.getProducts()
    }


}