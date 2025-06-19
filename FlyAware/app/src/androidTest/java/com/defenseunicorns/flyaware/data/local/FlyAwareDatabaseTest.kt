package com.defenseunicorns.flyaware.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class FlyAwareDatabaseTest {

    private lateinit var database: FlyAwareDatabase
    private lateinit var dao: IcaoDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FlyAwareDatabase::class.java
        ).build()
        dao = database.icaoDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndRetrieveIcao() = runBlocking {
        val entity = IcaoEntity("KSFO")
        dao.insert(entity)

        val result = dao.getAll()
        assertThat(result).containsExactly(entity).inOrder()
    }

    @Test
    fun insertAndDeleteIcao() = runBlocking {
        val entity = IcaoEntity("KPHX")
        dao.insert(entity)
        dao.delete(entity)

        val result = dao.getAll()
        assertThat(result).isEmpty()
    }

    @Test
    fun insertMultipleIcao() = runBlocking {
        val entity1 = IcaoEntity("KPHX")
        val entity2 = IcaoEntity("KLAX")
        val entity3 = IcaoEntity("KSFO")

        dao.insert(entity1)
        dao.insert(entity2)
        dao.insert(entity3)

        val result = dao.getAll()
        assertThat(result).containsExactly(entity1, entity2, entity3).inOrder()
    }
}