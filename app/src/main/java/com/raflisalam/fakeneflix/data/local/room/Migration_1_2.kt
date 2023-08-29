package com.raflisalam.fakeneflix.data.local.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE watchlist_movies_new (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "title TEXT NOT NULL, " +
                "poster_url TEXT NOT NULL, " +
                "description TEXT NOT NULL, " +
                "release_date TEXT NOT NULL, " +
                "rating REAL NOT NULL)")

        // Hapus tabel lama
        database.execSQL("DROP TABLE favorite_movies")

        // Ubah nama tabel baru menjadi nama tabel yang lama
        database.execSQL("ALTER TABLE watchlist_movies_new RENAME TO watchlist_movies")
    }
}