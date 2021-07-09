package com.davidelmn.application.frenzspots.data.local


import android.content.Context
import com.davidelmn.application.frenzspots.models.Spot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow

class SpotDataSourceLocal(var context: Context?) {

//    private val database : AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "db-bookmarks")
//        .addMigrations(MIGRATION_1_2)
//        .allowMainThreadQueries()   //Allows room to do operation on main thread
//        .build()

    fun getSpotList(): Flow<MutableList<Spot>> {
        return flow {
            val list = mutableListOf(
                Spot("Davide", "Corso Racconing 134", "", "Torino", 0.0, 0.0),
                Spot("Francesco", "Corso Ferraris 127", "", "Neive", 0.0, 0.0),
                Spot("Pasquale", "Strada Provinciale 127", "", "Asti", 0.0, 0.0)
            )

            emit(list)
        }.conflate()

//        return database.bookmarkDao.getBookmarks()
    }

//    fun insertBookmark(bookmark: Bookmark) {
//        database.bookmarkDao.insertBookmark(bookmark)
//    }

//    fun updateBookmark(bookmark: Bookmark) {
//        database.bookmarkDao.updateBookmark(bookmark)
//    }

//    fun findBookmarkById(id: String): Bookmark = database.bookmarkDao.findBookmarkById(id)

//    fun deleteBookmark(bookmark: Bookmark) {
//        database.bookmarkDao.deleteBookmark(bookmark)
//    }
}