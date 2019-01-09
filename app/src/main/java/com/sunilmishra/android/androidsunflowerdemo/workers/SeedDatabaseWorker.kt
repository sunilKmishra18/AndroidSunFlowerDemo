package com.sunilmishra.android.androidsunflowerdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.sunilmishra.android.androidsunflowerdemo.data.AppDatabase
import com.sunilmishra.android.androidsunflowerdemo.data.Plant
import com.sunilmishra.android.androidsunflowerdemo.utilities.PLANT_DATA_FILENAME

class SeedDatabaseWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }

    override fun doWork(): Result {
        val plantType = object : TypeToken<List<Plant>>() {}.type
        var jsonReader: JsonReader? = null

        return try {
            val inputStream = applicationContext.assets.open(PLANT_DATA_FILENAME)
            jsonReader = JsonReader(inputStream.reader())
            val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)
            val database = AppDatabase.getInstance(applicationContext)
            database.plantDao().insertAll(plantList)
            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        } finally {
            jsonReader?.close()
        }
    }
}
