package com.postliu.openai.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
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

public val Icons.SearchIcon: ImageVector
    get() {
        if (_searchIcon != null) {
            return _searchIcon!!
        }
        _searchIcon = Builder(name = "SearchIcon", defaultWidth = 18.0.dp, defaultHeight = 18.0.dp,
                viewportWidth = 18.0f, viewportHeight = 18.0f).apply {
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF282828)),
                    strokeLineWidth = 1.2f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(9.0f, 9.0f)
                moveToRelative(-6.4f, 0.0f)
                arcToRelative(6.4f, 6.4f, 0.0f, true, true, 12.8f, 0.0f)
                arcToRelative(6.4f, 6.4f, 0.0f, true, true, -12.8f, 0.0f)
            }
            path(fill = SolidColor(Color(0x00000000)), stroke = SolidColor(Color(0xFF282828)),
                    strokeLineWidth = 1.2f, strokeLineCap = Round, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(14.5f, 12.5f)
                lineTo(16.5f, 14.5f)
            }
        }
        .build()
        return _searchIcon!!
    }

private var _searchIcon: ImageVector? = null
