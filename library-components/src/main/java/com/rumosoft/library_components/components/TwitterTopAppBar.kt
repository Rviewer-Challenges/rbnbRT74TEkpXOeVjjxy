package com.rumosoft.library_components.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.R
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun TwitterTopAppBar(
    title: @Composable (() -> Unit)? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        title = title ?: {},
        navigationIcon = navigationIcon,
        elevation = 1.dp,
    )
}

@Composable
fun TwitterTitle() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.twitter_logo),
            contentDescription = stringResource(id = R.string.twitter),
            tint = TwitterMirroringTheme.colors.secondary,
            modifier = Modifier
                .size(24.dp),
        )
    }
}

@Composable
fun BackNavigationButton(
    onBackClick: () -> Unit = {},
) {
    IconButton(onClick = onBackClick) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.back),
        )
    }
}