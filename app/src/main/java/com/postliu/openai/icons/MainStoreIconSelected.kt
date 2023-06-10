package com.postliu.openai.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.postliu.openai.Icons

public val Icons.MainStoreIconSelected: ImageVector
    get() {
        if (_mainStoreIconSelected != null) {
            return _mainStoreIconSelected!!
        }
        _mainStoreIconSelected = Builder(
            name = "MainStoreIconSelected", defaultWidth = 28.0.dp,
            defaultHeight = 28.0.dp, viewportWidth = 28.0f, viewportHeight = 28.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFee463b)), stroke = SolidColor(Color(0xFFee463b)),
                strokeLineWidth = 1.3f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(20.546f, 7.84f)
                reflectiveCurveToRelative(3.657f, -0.123f, 3.657f, 3.3f)
                reflectiveCurveToRelative(-3.657f, 3.738f, -3.657f, 3.738f)
            }
            path(
                fill = SolidColor(Color(0xFFee463b)), stroke = SolidColor(Color(0xFFee463b)),
                strokeLineWidth = 1.3f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(6.338f, 17.163f)
                arcTo(6.549f, 6.549f, 0.0f, false, true, 4.496f, 15.177f)
                arcToRelative(4.9f, 4.9f, 0.0f, false, true, -0.7f, -2.5f)
                lineTo(3.796f, 6.634f)
                arcTo(1.022f, 1.022f, 0.0f, false, true, 4.242f, 5.809f)
                arcTo(1.442f, 1.442f, 0.0f, false, true, 5.083f, 5.549f)
                lineTo(19.396f, 5.549f)
                arcToRelative(1.442f, 1.442f, 0.0f, false, true, 0.841f, 0.26f)
                arcToRelative(1.022f, 1.022f, 0.0f, false, true, 0.446f, 0.825f)
                lineTo(20.683f, 12.677f)
                arcToRelative(4.9f, 4.9f, 0.0f, false, true, -0.7f, 2.5f)
                arcToRelative(6.554f, 6.554f, 0.0f, false, true, -1.846f, 1.986f)
                arcToRelative(10.115f, 10.115f, 0.0f, false, true, -5.9f, 1.777f)
                arcTo(10.115f, 10.115f, 0.0f, false, true, 6.338f, 17.163f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFee463b)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(5.153f, 21.862f)
                lineTo(19.815f, 21.862f)
                arcTo(0.73f, 0.73f, 0.0f, false, true, 20.545f, 22.592f)
                lineTo(20.545f, 22.593f)
                arcTo(0.73f, 0.73f, 0.0f, false, true, 19.815f, 23.323f)
                lineTo(5.153f, 23.323f)
                arcTo(0.73f, 0.73f, 0.0f, false, true, 4.423f, 22.593f)
                lineTo(4.423f, 22.592f)
                arcTo(0.73f, 0.73f, 0.0f, false, true, 5.153f, 21.862f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)), stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(12.485f, 14.053f)
                lineTo(12.485f, 8.829f)
            }
        }
            .build()
        return _mainStoreIconSelected!!
    }

private var _mainStoreIconSelected: ImageVector? = null
