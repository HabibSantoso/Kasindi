@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.kasindi.ui.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kasindi.R
import com.example.kasindi.navigasi.DestinasiNavigasi
import com.example.kasindi.ui.PenyediaViewModel
import com.example.kasindi.ui.TopAppBarKasindi
import com.example.kasindi.ui.view.viewmodel.AddTransViewModel
import com.example.kasindi.ui.view.viewmodel.Detailtransaksi
import com.example.kasindi.ui.view.viewmodel.UiStateTransaksi
import kotlinx.coroutines.launch

object DestinasiAddTran: DestinasiNavigasi {
    override val route = "item_addTran"
    override val titleRes = "tambah transaksi"
}

@Composable
fun AddTransScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddTransViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    //val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier/*.nestedScroll(scrollBehavior.nestedScrollConnection)*/,
        topBar = {
            TopAppBarKasindi(
                title = DestinasiAddTran.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack

                //scrollBehavior = scrollBehavior
            )
        }) {innerPadding ->
        TransaksiInputBody(
            uiStateTransaksi = viewModel.uiStateTransaksi,
            onTransValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveTransaksi()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun TransaksiInputBody(
    uiStateTransaksi: UiStateTransaksi,
    onTransValueChange: (Detailtransaksi) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ){
        FormInputTransaksi(
            detailtransaksi = uiStateTransaksi.detailtransaksi,
            onValueChange = onTransValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = uiStateTransaksi.isEntryVAlid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit")
        }
    }
}

@Composable
fun FormInputTransaksi(
    detailtransaksi: Detailtransaksi,
    modifier: Modifier = Modifier,
    onValueChange: (Detailtransaksi) -> Unit = {},
    enabled: Boolean = true
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ){
        OutlinedTextField(
            value = detailtransaksi.keterangan,
            onValueChange = {onValueChange(detailtransaksi.copy(keterangan = it )) },
            label =  { Text(stringResource(id = R.string.keterangan))},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true,

        )
        OutlinedTextField(
            value = detailtransaksi.tipe,
            onValueChange = {onValueChange(detailtransaksi.copy(tipe = it))},
            label = { Text(stringResource(id = R.string.tipe))},
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        OutlinedTextField(
            value = detailtransaksi.nominal.toString(),
            onValueChange = {onValueChange(detailtransaksi.copy(nominal = it.toInt()))},
            label = { Text(stringResource(id = R.string.nominal)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )


        if (enabled) {
            Text(
                text = stringResource(R.string.required_field),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            )
        }
        Divider(
            thickness = dimensionResource(R.dimen.padding_small),
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
        )
    }
}