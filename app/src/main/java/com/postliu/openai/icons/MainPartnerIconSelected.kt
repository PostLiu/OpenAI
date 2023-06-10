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

public val Icons.MainPartnerIconSelected: ImageVector
    get() {
        if (_mainPartnerIconSelected != null) {
            return _mainPartnerIconSelected!!
        }
        _mainPartnerIconSelected = Builder(name = "MainPartnerIconSelected", defaultWidth = 28.0.dp,
                defaultHeight = 28.0.dp, viewportWidth = 28.0f, viewportHeight = 28.0f).apply {
            path(fill = SolidColor(Color(0xFFf7f7f7)), stroke = null, fillAlpha = 0.0f, strokeAlpha
                    = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
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
            path(fill = SolidColor(Color(0xFFee463b)), stroke = SolidColor(Color(0xFFee463b)),
                    strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(11.785f, 13.187f)
                arcTo(4.059f, 4.059f, 0.0f, true, false, 7.726f, 9.127f)
                arcTo(4.059f, 4.059f, 0.0f, false, false, 11.785f, 13.187f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFee463b)),
                    strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(17.813f, 6.47f)
                arcToRelative(3.1f, 3.1f, 0.0f, false, true, 0.0f, 5.315f)
            }
            path(fill = SolidColor(Color(0xFFee463b)), stroke = SolidColor(Color(0xFFee463b)),
                    strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(5.142f, 21.44f)
                lineTo(5.142f, 21.971f)
                lineTo(18.43f, 21.971f)
                verticalLineToRelative(-0.532f)
                curveToRelative(0.0f, -1.984f, 0.0f, -2.977f, -0.386f, -3.735f)
                arcToRelative(3.544f, 3.544f, 0.0f, false, false, -1.549f, -1.549f)
                curveTo(15.737f, 15.771f, 14.742f, 15.771f, 12.76f, 15.771f)
                lineTo(10.811f, 15.771f)
                curveToRelative(-1.984f, 0.0f, -2.977f, 0.0f, -3.735f, 0.386f)
                arcToRelative(3.543f, 3.543f, 0.0f, false, false, -1.549f, 1.549f)
                curveTo(5.142f, 18.464f, 5.142f, 19.456f, 5.142f, 21.44f)
                close()
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFFee463b)),
                    strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(22.859f, 21.972f)
                verticalLineToRelative(-0.532f)
                curveToRelative(0.0f, -1.984f, 0.0f, -2.977f, -0.386f, -3.735f)
                arcToRelative(3.543f, 3.543f, 0.0f, false, false, -1.549f, -1.548f)
            }
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = SolidColor(Color(0xFFFFFFFF)),
                    strokeLineWidth = 1.3f, strokeLineCap = Round, strokeLineJoin =
                    StrokeJoin.Companion.Round, strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(9.889f, 19.065f)
                horizontalLineToRelative(3.793f)
            }
        }
        .build()
        return _mainPartnerIconSelected!!
    }

private var _mainPartnerIconSelected: ImageVector? = null
