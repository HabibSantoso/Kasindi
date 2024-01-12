@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.kasindi.ui.view.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kasindi.R
import com.example.kasindi.model.Transaksi
import com.example.kasindi.navigasi.DestinasiNavigasi
import com.example.kasindi.ui.PenyediaViewModel
import com.example.kasindi.ui.TopAppBarKasindi
import com.example.kasindi.ui.view.viewmodel.HomeViewModel

object DestinasiHome: DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "transaksi"
}

@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onDetailClick: (Int) -> Unit = {}
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarKasindi(
                title = DestinasiHome.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick =navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entry_Transaksi)
                )
            }
        },
    ){
            innerPadding ->
        val uiStateTransaksi by viewModel.homeUiState.collectAsState()
        BodyHome(
            itemTransaksi = uiStateTransaksi.listTrans,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onTransaksiClick = onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemTransaksi: List<Transaksi>,
    modifier: Modifier = Modifier,
    onTransaksiClick: (Int) -> Unit
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        if (itemTransaksi.isEmpty()) {
            Text(
                text = stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListTransakasi(
                itemTransaksi = itemTransaksi,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onTransaksiClick(it.tid) }
            )
        }
    }
}

@Composable
fun ListTransakasi(
    itemTransaksi: List<Transaksi>,
    modifier: Modifier = Modifier,
    onItemClick: (Transaksi) -> Unit
){
    LazyColumn(modifier = modifier){
        items(items = itemTransaksi, key = {it.tid}){
            trans ->
            DataTransaksi(transaksi = trans,
                modifier.padding(dimensionResource(id = R.dimen.padding_small)).clickable { onItemClick(trans) })
        }
    }
}

@Composable
fun DataTransaksi(
    transaksi: Transaksi,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier.height(70.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
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