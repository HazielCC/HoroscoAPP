package com.example.horoscoapp.data

import com.example.horoscoapp.domain.Repository

class RepositoryImpl : Repository {
    override suspend fun getHoroscope(sign: String): String {
        TODO("Not yet implemented")
    }
}