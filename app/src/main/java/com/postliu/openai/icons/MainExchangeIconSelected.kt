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

public val Icons.MainExchangeIconSelected: ImageVector
    get() {
        if (_mainExchangeIconSelected != null) {
            return _mainExchangeIconSelected!!
        }
        _mainExchangeIconSelected = Builder(
            name = "MainExchangeIconSelected", defaultWidth =
            28.0.dp, defaultHeight = 28.0.dp, viewportWidth = 28.0f, viewportHeight =
            28.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFf7f7f7)), stroke = null, fillAlpha = 0.0f, strokeAlpha
                = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(14.0f, 0.0f)
                lineTo(14.0f, 0.0f)
                arcTo(14.0f, 14.0f, 0.0f, false, true, 28.0f, 14.0f)
                lineTo(28.0f, 14.0f)
                arcTo(14.0f, 14.0f, 0.0f, false, true, 14.0f, 28.0f)
                lineTo(14.0f, 28.0f)
                arcTo(14.0f, 14.0f, 0.0f, false, true, 0.0f, 14.0f)
                lineTo(0.0f, 14.0f)
                arcTo(14.0f, 14.0f, 0.0f, false, true, 14.0f, 0.0f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFee463b)), stroke = SolidColor(Color(0xFFee463b)),
                strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(20.927f, 5.688f)
                lineTo(7.073f, 5.688f)
                arcTo(
                    1.385f, 1.385f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    x1 = 5.688f,
                    y1 = 7.073f
                )
                lineTo(5.688f, 20.927f)
                arcToRelative(
                    1.385f, 1.385f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = 1.385f,
                    dy1 = 1.385f
                )
                lineTo(20.927f, 22.312f)
                arcToRelative(
                    1.385f, 1.385f, 0.0f,
                    isMoreThanHalf = false,
                    isPositiveArc = false,
                    dx1 = 1.385f,
                    dy1 = -1.385f
                )
                lineTo(22.312f, 7.073f)
                arcTo(1.385f, 1.385f, 0.0f, false, false, 20.927f, 5.688f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)), stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(9.844f, 12.615f)
                horizontalLineToRelative(8.312f)
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)), stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(9.844f, 15.386f)
                horizontalLineToRelative(8.312f)
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)), stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(18.157f, 12.615f)
                lineTo(14.924f, 9.382f)
            }
            path(
                fill = SolidColor(Color(0xFFFFFFFF)), stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(13.077f, 18.619f)
                lineTo(9.844f, 15.386f)
            }
        }
            .build()
        return _mainExchangeIconSelected!!
    }

private var _mainExchangeIconSelected: ImageVector? = null
