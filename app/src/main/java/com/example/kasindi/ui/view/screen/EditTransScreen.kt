package com.example.kasindi.ui.view.screen

import com.example.kasindi.navigasi.DestinasiNavigasi

object DestinasiEditTrans : DestinasiNavigasi {
    override val route: String = "item_detailtrans"
    override val titleRes: String = "Edit Transaksi"
    const val transIdArg = "itemId"
    val routeWithArg = "$route/{$transIdArg}"
}