@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kasindi.ui.view.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kasindi.navigasi.DestinasiNavigasi
import com.example.kasindi.ui.PenyediaViewModel
import com.example.kasindi.ui.TopAppBarKasindi
import com.example.kasindi.ui.view.viewmodel.AddTransViewModel
import com.example.kasindi.ui.view.viewmodel.EditTransViewModel
import kotlinx.coroutines.launch

object DestinasiEditTrans : DestinasiNavigasi {
    override val route: String = "item_detailtrans"
    override val titleRes: String = "Edit Transaksi"
    const val transIdArg = "itemId"
    val routeWithArg = "$route/{$transIdArg}"
}

@Composable
fun EditTransScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditTransViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold (
        modifier = modifier,
        topBar = {
            TopAppBarKasindi(
                title = DestinasiAddTran.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }) {innerPadding ->
        TransaksiInputBody(
            uiStateTransaksi = viewModel.transaksiUiState,
            onTransValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateTransaksi()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)

        )
    }
}