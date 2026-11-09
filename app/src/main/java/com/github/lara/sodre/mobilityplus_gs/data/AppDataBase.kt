package com.github.lara.sodre.mobilityplus_gs.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [
        Local::class,
        PreferenciasAcessibilidade::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun localDao(): LocalDao

    abstract fun preferenciasDao(): PreferenciasDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {

            override fun migrate(
                database: SupportSQLiteDatabase
            ) {
                database.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS preferencias_acessibilidade (
                        id INTEGER NOT NULL,
                        fisica INTEGER NOT NULL,
                        visual INTEGER NOT NULL,
                        auditiva INTEGER NOT NULL,
                        intelectual INTEGER NOT NULL,
                        cadeiraRodas INTEGER NOT NULL,
                        bengala INTEGER NOT NULL,
                        mobilidadeReduzida INTEGER NOT NULL,
                        PRIMARY KEY(id)
                    )
                    """.trimIndent()
                )
            }
        }

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mobility_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}
