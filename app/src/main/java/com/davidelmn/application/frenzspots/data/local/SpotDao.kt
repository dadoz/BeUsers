package com.davidelmn.application.frenzspots.data.local

import com.davidelmn.application.frenzspots.models.Spot


//@Dao
interface SpotDao {
//    @Insert
    fun insertAllBookmarks(bookmark: List<Spot>)

//    @Insert
    fun insertBookmark(bookmark: Spot)

//    @Update
    fun updateBookmark(bookmark: Spot)

//    @Delete
    fun deleteBookmark(bookmark: Spot)

//    @Query("SELECT * FROM mb_bookmark")
    fun getBookmarks(): MutableList<Spot>

//    @Query("SELECT * FROM mb_bookmark WHERE url=:id")
    fun findBookmarkById(id: String): Spot
}