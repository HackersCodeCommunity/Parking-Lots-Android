package io.parkinglots.android

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import io.parkinglots.android.ui.theme.ParklotsTheme
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CreateMap() {
    //lat and lng
    val lat = 0.0
    val lng = 0.0

    val multiplePermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(
                44.810058,
                20.4617586
            ), 17f
        )
    }

    LaunchedEffect(Unit) {
        multiplePermissionState.launchMultiplePermissionRequest()
    }

    ParklotsTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            Text(
                text = "Welcome to the MapsApp!",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            PermissionsRequired(
                multiplePermissionsState = multiplePermissionState,
                permissionsNotGrantedContent = { /* ... */ },
                permissionsNotAvailableContent = { /* ... */ }
            ) {
                GoogleMap(
                    cameraPositionState = cameraPositionState,
                    modifier = Modifier.weight(1f),
                    properties = MapProperties(isMyLocationEnabled = true),
                    uiSettings = MapUiSettings(compassEnabled = true)
                ) {
                    Polyline(
                        points = listOf(
                            LatLng(44.811058, 20.4617586),
                            LatLng(44.811058, 20.4627586),
                            LatLng(44.810058, 20.4627586),
                            LatLng(44.809058, 20.4627586),
                            LatLng(44.809058, 20.4617586)
                        )
                    )
                }
            }
        }
    }
}
