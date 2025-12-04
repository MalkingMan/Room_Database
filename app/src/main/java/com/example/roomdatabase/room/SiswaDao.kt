package com.example.roomdatabase.room

import android.app.Application
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomdatabase.repositori.ContainerApp
import com.example.roomdatabase.repositori.ContainerDataApp
import kotlinx.coroutines.flow.Flow

@Dao
interface SiswaDao
{
    @Query("SELECT * FROM tblSiswa ORDER BY nama ASC")
    fun getAllSiswa(): Flow<List<Siswa>>

    @Query (value = "SELECT * FROM tblSiswa WHERE id = :id")
    fun getSiswa(id: Int): Flow<Siswa>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(siswa: Siswa)

    @Update
    suspend fun update(siswa: Siswa)

    @Delete
    suspend fun delete(siswa: Siswa)
}

class AplikasiSiswa : Application() {

    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}