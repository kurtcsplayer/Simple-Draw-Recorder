package com.simplemobiletools.draw.rec.helpers

import android.content.Context
import android.graphics.Color
import com.simplemobiletools.commons.helpers.BaseConfig
import com.simplemobiletools.draw.rec.R

class Config(context: Context) : BaseConfig(context) {
    companion object {
        fun newInstance(context: Context) = Config(context)
    }

    var showBrushSize: Boolean
        get() = prefs.getBoolean(SHOW_BRUSH_SIZE, true)
        set(showBrushSize) = prefs.edit().putBoolean(SHOW_BRUSH_SIZE, showBrushSize).apply()

    var brushColor: Int
        get() = prefs.getInt(BRUSH_COLOR, context.resources.getColor(R.color.color_primary))
        set(color) = prefs.edit().putInt(BRUSH_COLOR, color).apply()

    var brushSize: Float
        get() = prefs.getFloat(BRUSH_SIZE, 40f)
        set(brushSize) = prefs.edit().putFloat(BRUSH_SIZE, brushSize).apply()

    var canvasBackgroundColor: Int
        get() = prefs.getInt(CANVAS_BACKGROUND_COLOR, Color.WHITE)
        set(canvasBackgroundColor) = prefs.edit().putInt(CANVAS_BACKGROUND_COLOR, canvasBackgroundColor).apply()

    var lastImgSaveFolder: String
        get() = prefs.getString(LAST_SAVE_IMG_FOLDER, "")!!
        set(lastSaveFolder) = prefs.edit().putString(LAST_SAVE_IMG_FOLDER, lastSaveFolder).apply()

    var lastImgSaveExtension: String
        get() = prefs.getString(LAST_SAVE_IMG_EXTENSION, "")!!
        set(lastSaveExtension) = prefs.edit().putString(LAST_SAVE_IMG_EXTENSION, lastSaveExtension).apply()

    var lastRecSaveFolder: String
        get() = prefs.getString(LAST_SAVE_REC_FOLDER, "")!!
        set(lastSaveFolder) = prefs.edit().putString(LAST_SAVE_REC_FOLDER, lastSaveFolder).apply()

    var lastRecSaveExtension: String
        get() = prefs.getString(LAST_SAVE_REC_EXTENSION, "")!!
        set(lastSaveFolder) = prefs.edit().putString(LAST_SAVE_REC_EXTENSION, lastSaveFolder).apply()

    var allowZoomingCanvas: Boolean
        get() = prefs.getBoolean(ALLOW_ZOOMING_CANVAS, true)
        set(allowZoomingCanvas) = prefs.edit().putBoolean(ALLOW_ZOOMING_CANVAS, allowZoomingCanvas).apply()

    var forcePortraitMode: Boolean
        get() = prefs.getBoolean(FORCE_PORTRAIT_MODE, false)
        set(forcePortraitMode) = prefs.edit().putBoolean(FORCE_PORTRAIT_MODE, forcePortraitMode).apply()

    var onlyPenForDrawing: Boolean
        get() = prefs.getBoolean(ONLY_PEN_FOR_DRAWING, false)
        set(onlyPenForDrawing) = prefs.edit().putBoolean(ONLY_PEN_FOR_DRAWING, onlyPenForDrawing).apply()
}
