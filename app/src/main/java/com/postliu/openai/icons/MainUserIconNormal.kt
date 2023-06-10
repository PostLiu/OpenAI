package com.postliu.openai.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.postliu.openai.Icons

public val Icons.MainUserIconNormal: ImageVector
    get() {
        if (_mainUserIconNormal != null) {
            return _mainUserIconNormal!!
        }
        _mainUserIconNormal = Builder(name = "MainUserIconNormal", defaultWidth = 28.0.dp,
                defaultHeight = 28.0.dp, viewportWidth = 28.0f, viewportHeight = 28.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(13.999f, 14.943f)
                arcTo(5.113f, 5.113f, 0.0f, true, false, 8.886f, 9.829f)
                arcTo(5.113f, 5.113f, 0.0f, false, false, 13.999f, 14.943f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF000000)),
                    strokeLineWidth = 1.3f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(10.307f, 16.906f)
                horizontalLineToRelative(7.386f)
                arcTo(4.35f, 4.35f, 0.0f, false, true, 22.043f, 21.256f)
                verticalLineToRelative(0.159f)
                arcToRelative(1.35f, 1.35f, 0.0f, false, true, -1.35f, 1.35f)
                lineTo(7.307f, 22.765f)
                arcTo(1.35f, 1.35f, 0.0f, false, true, 5.957f, 21.415f)
                lineTo(5.957f, 21.256f)
                arcTo(4.35f, 4.35f, 0.0f, false, true, 10.307f, 16.906f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFee463b)),
                    strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(15.764f, 10.496f)
                arcTo(2.047f, 2.047f, 0.0f, false, true, 12.237f, 10.496f)
            }
        }
        .build()
        return _mainUserIconNormal!!
    }

private var _mainUserIconNormal: ImageVector? = null
