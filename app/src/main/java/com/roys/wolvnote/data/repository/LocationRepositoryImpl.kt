package com.roys.wolvnote.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.roys.wolvnote.domain.repository.LocationRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val context: Context
): LocationRepository {
    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): Location? {
        return suspendCancellableCoroutine { continuation ->
            val client = LocationServices.getFusedLocationProviderClient(context)
            client.lastLocation
                .addOnSuccessListener { location ->
                    continuation.resume(location) { cause, _, _ -> }
                }
                .addOnFailureListener {
                    continuation.resume(null) { cause, _, _ -> }
                }
        }
    }
}