package com.igorj.core.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.igorj.core.R
import com.igorj.core.DarkGray
import com.igorj.core.LightGray
import com.igorj.core.TextWhite

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String = "",
    @DrawableRes trailingIconId: Int? = null,
    onTrailingIconClick: () -> Unit = {},
    isTrailingIconEnabled: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    onKeyboardDone: () -> Unit = {}
) {
    TextField(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .width(300.dp),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(100.dp),
        placeholder = {
            Text(
                text = hint,
                color = LightGray,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
            )
        },
        textStyle = TextStyle(
            color = TextWhite
        ),
        trailingIcon = {
            IconButton(
                onClick = onTrailingIconClick,
                enabled = isTrailingIconEnabled,
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = trailingIconId ?: R.drawable.ic_transparent
                    ),
                    contentDescription = hint,
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onAny = {
                onKeyboardDone()
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = TextWhite,
            disabledTextColor = TextWhite,
            backgroundColor = DarkGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        visualTransformation = visualTransformation,
        enabled = enabled
    )
}