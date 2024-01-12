package com.example.kasindi.ui.view.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kasindi.model.Transaksi
import com.example.kasindi.repositori.RepositoriKasindi
import com.example.kasindi.ui.view.screen.DestinasiDetailTrans
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailTransViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriKasindi: RepositoriKasindi
): ViewModel() {
    private val transaksiId: Int = checkNotNull(savedStateHandle[DestinasiDetailTrans.transIdArg])
    val uiState: StateFlow<TransaksiDetailUiState> =
        repositoriKasindi.getTransStream(transaksiId)
            .filterNotNull()
            .map {
                TransaksiDetailUiState(detailtransaksi = it.toDetailTransaksi())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MIllIS),
                initialValue = TransaksiDetailUiState()
            )
    suspend fun deleteTrans(){
        repositoriKasindi.deleteT(uiState.value.detailtransaksi.toTrans())
    }
    companion object {
        private const val TIMEOUT_MIllIS = 5_000L
    }
}

data class TransaksiDetailUiState(
    val outofStoke: Boolean = true,
    val detailtransaksi: Detailtransaksi = Detailtransaksi()
)