package com.hashitoapps.data_layer.repository.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature

object HttpClientFactory {
    fun build(): HttpClient {
        return HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
        }
    }
}