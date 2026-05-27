package com.example.gw_assesment.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gw_assesment.R

@Composable
fun CustomToolbar(
    @DrawableRes leadingIcon: Int = R.drawable.ic_back,
    text: String,
    @DrawableRes trailingIcon: Int? = null,
    onClick: ()-> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = Color.White),
                modifier = Modifier.size(24.dp).clickable{
                    onClick()
                }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
            )


            trailingIcon?.let {
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(trailingIcon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Color.White),
                    modifier = Modifier.size(24.dp)
                )
            }


        }
    }
}


@Composable
@Preview
fun PreviewCustomToolbar() {
    CustomToolbar(
        text = "Odoo Mobile",
        onClick = {}
    )
}