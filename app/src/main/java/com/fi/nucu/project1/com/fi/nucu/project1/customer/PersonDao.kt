package com.fi.nucu.project1.com.fi.nucu.project1.customer

import androidx.room.*

@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    fun getAll(): List<Person>

    @Insert
    fun insert(person: Person)

    @Update
    fun update(person: Person)

    @Delete
    fun delete(person: Person)
}