package com.example.newsapp

import com.example.newsapp.ColorPicker.colors

object ColorPicker {
    val colors = arrayOf(
        "#FF6633", "#FFB399", "#FF33FF", "#FFFF99", "#00B3E6",
        "#E6B333", "#3366E6", "#999966", "#99FF99", "#B34D4D",
        "#80B300", "#809900", "#E6B3B3", "#6680B3", "#66991A",
        "#FF99E6", "#CCFF1A", "#FF1A66", "#E6331A", "#33FFCC",
        "#B2EBF2", "#80DEEA", "#4DD0E1", "#26C6DA", "#00BCD4",
        "#00ACC1", "#0097A7", "#00838F", "#006064", "#84FFFF",
    )
    var colorIndex = 1
    fun getColor(): String {
        return colors[colorIndex++ % colors.size]
    }
}