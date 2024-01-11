package com.example.kasindi.ui.view.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kasindi.model.Transaksi
import com.example.kasindi.navigasi.DestinasiNavigasi

object DestinasiHome: DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "transaksi"
}



@Composable
fun DataTransaksi(
    transaksi: Transaksi,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = transaksi.keterangan,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.weight(1f))
            Text(text = transaksi.nominal.toString(),
                style = MaterialTheme.typography.titleMedium)
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null,
            )
        }

    }
}