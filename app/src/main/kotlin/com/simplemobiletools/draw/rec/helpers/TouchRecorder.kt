package com.simplemobiletools.draw.rec.helpers

import android.content.Context
import com.simplemobiletools.draw.rec.R
import java.io.*
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*


/**
 * Helper to manage files to record touch info
 */
class TouchRecorder {
    companion object {
        private const val FILE_NAME_PREFIX = "paintLog_"
        private const val FILE_NAME_EXTENSION = ".txt"
        private var file: File? = null
        private var fileOutputStream: OutputStream? = null
        private var bufferedWriter: BufferedWriter? = null

        /** Creates new file and open output stream for it */
        fun newFile(context: Context): String {
            fileOutputStream?.close()
            val simpleDf = SimpleDateFormat("dd_MM_yyyy_HH_mm_ss")
            val fileName = FILE_NAME_PREFIX + simpleDf.format(Date()) + FILE_NAME_EXTENSION
            val filePath = context.getExternalFilesDir(null)
            val appFolderName = getAppName(context)
            val appFolder = File(filePath, appFolderName)
            if (!appFolder.exists()) {
                appFolder.mkdirs();
            }
            // Create new file
            val newFile = File("$filePath/$appFolderName", fileName)
            fileOutputStream = FileOutputStream(newFile)
            return newFile.path
        }

        fun write(data: String) {
            if (bufferedWriter == null) {
                bufferedWriter =
                    fileOutputStream?.writer(Charset.defaultCharset())?.buffered(DEFAULT_BUFFER_SIZE)
            }
            try {
                bufferedWriter?.write(data)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        fun closeFile() {
            bufferedWriter?.close()
            bufferedWriter = null
            fileOutputStream?.flush()
            fileOutputStream?.close()
        }

        @Throws(NoSuchFileException::class, FileAlreadyExistsException::class, IOException::class)
        fun moveFile(from: String, destination: String) {
            File(from).let {
                it.copyTo(File(destination))
                it.delete()
            }
        }

        private fun getAppName(context: Context): String {
            return context.getString(R.string.app_launcher_name)
        }
    }
}
