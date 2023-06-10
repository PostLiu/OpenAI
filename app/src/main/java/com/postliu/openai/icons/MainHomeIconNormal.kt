package com.postliu.openai.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.postliu.openai.Icons

public val Icons.MainHomeIconNormal: ImageVector
    get() {
        if (_mainHomeIconNormal != null) {
            return _mainHomeIconNormal!!
        }
        _mainHomeIconNormal = Builder(name = "MainHomeIconNormal", defaultWidth = 28.0.dp,
                defaultHeight = 28.0.dp, viewportWidth = 28.0f, viewportHeight = 28.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                    strokeLineWidth = 0.0f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(11.111f, 22.7f)
                lineTo(11.111f, 17.5f)
                curveTo(11.111f, 16.782f, 11.694f, 16.2f, 12.411f, 16.2f)
                lineTo(15.589f, 16.2f)
                curveTo(16.306f, 16.2f, 16.889f, 16.782f, 16.889f, 17.5f)
                lineTo(16.889f, 22.7f)
                lineTo(21.7f, 22.699f)
                lineTo(21.7f, 11.166f)
                lineTo(14.0f, 5.348f)
                lineTo(6.3f, 11.167f)
                lineTo(6.3f, 22.7f)
                lineTo(11.111f, 22.7f)
                moveTo(16.647f, 24.0f)
                curveTo(16.366f, 24.0f, 16.097f, 23.894f, 15.898f, 23.706f)
                curveTo(15.7f, 23.519f, 15.589f, 23.264f, 15.589f, 23.0f)
                lineTo(15.589f, 17.5f)
                lineTo(12.411f, 17.5f)
                lineTo(12.411f, 23.0f)
                curveTo(12.411f, 23.552f, 11.938f, 24.0f, 11.353f, 24.0f)
                lineTo(6.059f, 24.0f)
                curveTo(5.474f, 24.0f, 5.0f, 23.552f, 5.0f, 23.0f)
                lineTo(5.0f, 11.0f)
                curveTo(5.0f, 10.696f, 5.147f, 10.409f, 5.398f, 10.219f)
                lineTo(13.339f, 4.219f)
                curveTo(13.725f, 3.927f, 14.275f, 3.927f, 14.661f, 4.219f)
                lineTo(22.603f, 10.219f)
                curveTo(22.854f, 10.409f, 23.0f, 10.696f, 23.0f, 11.0f)
                lineTo(23.0f, 22.999f)
                curveTo(23.0f, 23.552f, 22.526f, 23.999f, 21.942f, 23.999f)
                lineTo(16.647f, 24.0f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFee463b)),
                    strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(17.214f, 12.655f)
                lineTo(10.786f, 12.655f)
            }
        }
        .build()
        return _mainHomeIconNormal!!
    }

private var _mainHomeIconNormal: ImageVector? = null
