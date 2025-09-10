package com.sergio.saneia.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
import org.osmdroid.tileprovider.MapTile
import org.osmdroid.tileprovider.tilesource.ITileSource

@Composable
fun SecondScreen() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            MapView(context).apply {
                setMultiTouchControls(true)

                // → Troca para satélite (Esri World Imagery)
                val esriSat = object : OnlineTileSourceBase(
                    "EsriWorldImagery",
                    0, 22, 256, ".jpg",
                    arrayOf("https://server.arcgisonline.com/ArcGIS/rest/services/World_Imagery/MapServer/tile/")
                ) {
                    override fun getTileURLString(pMapTileIndex: Long): String {
                        return baseUrl + getZoom(pMapTileIndex) + "/" +
                                getY(pMapTileIndex) + "/" +
                                getX(pMapTileIndex) + mImageFilenameEnding
                    }
                }
                setTileSource(esriSat as ITileSource)

                // Coordenadas da Rua Vicente Oliveira de Brito (Juazeiro do Norte - CE)
                val latitude = -7.2381185
                val longitude = -39.3065841

                controller.setZoom(18.0)
                controller.setCenter(GeoPoint(latitude, longitude))

                // Ponteiro padrão
                val marker = Marker(this)
                marker.position = GeoPoint(latitude, longitude)
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marker.title = "Rua Vicente Oliveira de Brito"
                overlays.add(marker)
            }
        }
    )
}
