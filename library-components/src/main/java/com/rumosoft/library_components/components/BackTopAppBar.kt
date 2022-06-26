package com.rumosoft.library_components.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rumosoft.library_components.R
import com.rumosoft.library_components.presentation.theme.TwitterMirroringTheme

@Composable
fun BackTopAppBar() {
    TopAppBar(
        navigationIcon = {

        },
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.twitter_logo),
                    contentDescription = stringResource(id = R.string.twitter),
                    tint = TwitterMirroringTheme.colors.secondary,
                    modifier = Modifier
                        .size(24.dp),
                )
            }
        },
        elevation = 1.dp,
    )
}