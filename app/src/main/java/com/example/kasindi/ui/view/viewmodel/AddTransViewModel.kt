package com.example.kasindi.ui.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kasindi.model.Transaksi
import com.example.kasindi.repositori.RepositoriKasindi

class AddTransScreen(private val repositoriKasindi: RepositoriKasindi) : ViewModel() {

    var uiStateTransaksi by mutableStateOf(UiStateTransaksi())
        private set

    private fun validasiInput(uiState: Detailtransaksi = uiStateTransaksi.detailtransaksi): Boolean {
        return with(uiState) {
            keterangan.isNotBlank() && tipe.isNotBlank() && nominal>0
        }
    }
}
data class Detailtransaksi(
    val tid : Int = 0,
    val keterangan : String = "",
    val tipe : String = "",
    val nominal : Int = 0
)

data class UiStateTransaksi(
    val detailtransaksi: Detailtransaksi = Detailtransaksi(),
    val isEntryVAlid: Boolean = false
)

fun Detailtransaksi.toTrans(): Transaksi = Transaksi(
    tid = tid,
    keterangan = keterangan,
    tipe =  tipe,
    nominal = nominal
)

fun Transaksi.toUiStateTransaksi(isEntryVAlid: Boolean = false): UiStateTransaksi = UiStateTransaksi(
    detailtransaksi = this.toDetailTransaksi(),
    isEntryVAlid = isEntryVAlid
)

fun Transaksi.toDetailTransaksi(): Detailtransaksi = Detailtransaksi(
    tid = tid,
    keterangan = keterangan,
    tipe =  tipe,
    nominal = nominal
)