package com.example.kasindi.ui.view.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kasindi.repositori.RepositoriKasindi
import com.example.kasindi.ui.view.screen.DestinasiDetailTrans
import com.example.kasindi.ui.view.screen.DestinasiEditTrans
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditTransViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriKasindi: RepositoriKasindi
): ViewModel() {
    var transaksiUiState by mutableStateOf(UiStateTransaksi())

    private val transaksiId: Int = checkNotNull(savedStateHandle[DestinasiEditTrans.transIdArg])

    init {
        viewModelScope.launch {
            transaksiUiState = repositoriKasindi.getTransStream(transaksiId)
                .filterNotNull()
                .first()
                .toUiStateTransaksi(true)
        }
    }

    suspend fun updateTransaksi(){
        if (validasiInput(transaksiUiState.detailtransaksi)){
            repositoriKasindi.updateT(transaksiUiState.detailtransaksi.toTrans())
        }else{
            println("Data tidak valid")
        }
    }

    private fun validasiInput(uiState: Detailtransaksi = transaksiUiState.detailtransaksi): Boolean {
        return with(uiState) {
            keterangan.isNotBlank() && tipe.isNotBlank() && nominal>0
        }
    }

    fun updateUiState(detailtransaksi: Detailtransaksi){
        transaksiUiState =
            UiStateTransaksi(detailtransaksi = detailtransaksi, isEntryVAlid = validasiInput(detailtransaksi))
    }
}