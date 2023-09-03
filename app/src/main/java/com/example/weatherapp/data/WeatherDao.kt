package com.example.weatherapp.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query(value = "SELECT * FROM fav_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query(value = "SELECT * FROM fav_tbl WHERE city =:city")
    suspend fun getFavById(city: String) : Favorite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite( favorite: Favorite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favorite: Favorite)

    @Query("DELETE FROM fav_tbl")
    suspend fun deleteAllFavourites()

    @Delete()
    suspend fun deleteFavourite(favorite: Favorite)
}
