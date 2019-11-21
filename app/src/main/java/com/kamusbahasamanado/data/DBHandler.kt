package com.kamusbahasamanado.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.kamusbahasamanado.model.*
import com.kamusbahasamanado.model.ArtiKata
import java.io.File
import java.io.FileOutputStream

// -----------------------------------------------------------------------------
// Kelas DBHandler, memuat semua data suatu kata dalam Bahasa Manado,
//   yang mewakili satu entry dalam Kamus Bahasa Manado
class DBHandler(private val dbContext:Context) : SQLiteOpenHelper(dbContext, DATABASE_NAME,null, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        // We don't need to create Table and Database, because it's already
        //   added in as asset, then transferred, so we disable this method
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // We don't need to create Table and Database, because it's already
        //   added in as asset, then transferred, so we disable this method
    }

    fun installDatabaseFromAssets() {
        val inputStream = dbContext.assets.open("$DATABASE_NAME")

        val outputFile = File(dbContext.getDatabasePath(DATABASE_NAME).path)
        val outputStream = FileOutputStream(outputFile)

        inputStream.copyTo(outputStream)
        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }

    // ------------------------------------------------------------------------------
    // INPUT KATA ke dalam Kamus ----------------------------------------------------
    fun createKata(artiKata: ArtiKata): Boolean {
        //Create and/or open a database that will be used for reading and writing.
        val db = this.writableDatabase

        // Prepare row entry
        val values = ContentValues()
        values.put(KATA     , artiKata.Kata.toString()     )
        values.put(ARTI     , artiKata.Arti.toString()     )
        values.put(CONTOH   , artiKata.Contoh.toString()   )
        values.put(CREDIT   , artiKata.Credit.toString()   )
        values.put(LINK1    , artiKata.Link1.toString()    )
        values.put(LINK2    , artiKata.Link2.toString()    )

        // Insert row entry, and close database
        val dbStatus = db.insert(DATABASE_TABLE, null, values)
        db.close()

        return (Integer.parseInt("$dbStatus") != -1)
    }

    // ------------------------------------------------------------------------------
    // UPDATE KATA dari dalam Kamus -------------------------------------------------
    fun updateKata(artiKata: ArtiKata): Boolean {
        //Create and/or open a database that will be used for reading and writing.
        val db = this.writableDatabase

        // Prepare row entry
        val values = ContentValues()
        values.put(KATA     , artiKata.Kata.toString()     )
        values.put(ARTI     , artiKata.Arti.toString()     )
        values.put(CONTOH   , artiKata.Contoh.toString()   )
        values.put(CREDIT   , artiKata.Credit.toString()   )
        values.put(LINK1    , artiKata.Link1.toString()    )
        values.put(LINK2    , artiKata.Link2.toString()    )

        // Insert row entry, and close database
        val dbStatus = db.update(DATABASE_TABLE, values, "$KATA='" + artiKata.Kata+ "'", null)
        db.close()

        return (Integer.parseInt("$dbStatus") != -1)
    }

    // ------------------------------------------------------------------------------
    // HAPUS KATA dari dalam Kamus --------------------------------------------------
    fun deleteKata(entryKata: String): Boolean {
        //Create and/or open a database that will be used for reading and writing.
        val db = this.writableDatabase

        // Insert row entry, and close database
        val dbStatus = db.delete(DATABASE_TABLE,"$KATA='" + entryKata + "'",null)
        db.close()

        return (Integer.parseInt("$dbStatus") != -1)
    }

    // ------------------------------------------------------------------------------
    // BACA KATA dari dalam Kamus : BEBERAPA KATA MIRIP -----------------------------
    fun cariKataPersis(kata:String): ArrayList<ArtiKata>{
        val listArtiKata : ArrayList<ArtiKata> = ArrayList()
        val db = this.readableDatabase
        val selectKataQuery = "SELECT * FROM $DATABASE_TABLE WHERE $KATA='" + kata + "'"
        val cursor = db.rawQuery(selectKataQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val entryKata = ArtiKata()

                    entryKata.Kata      = cursor.getString(0)
                    entryKata.Arti      = cursor.getString(1)
                    entryKata.Contoh    = cursor.getString(2)
                    entryKata.Credit    = cursor.getString(3)
                    entryKata.Link1     = cursor.getString(4)
                    entryKata.Link2     = cursor.getString(5)

                    listArtiKata.add(entryKata)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return listArtiKata
    }

    // ------------------------------------------------------------------------------
    // BACA KATA dari dalam Kamus : 1 KATA PERSIS -----------------------------------
    fun cariKataAwalan(kata:String): ArrayList<ArtiKata>{
        val listArtiKata : ArrayList<ArtiKata> = ArrayList()
        val db = this.readableDatabase
        val selectKataQuery = "SELECT * FROM $DATABASE_TABLE WHERE $KATA LIKE '" + kata + "%' ORDER BY $KATA ASC"
        val cursor = db.rawQuery(selectKataQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val entryKata = ArtiKata()

                    entryKata.Kata      = cursor.getString(0)
                    entryKata.Arti      = cursor.getString(1)
                    entryKata.Contoh    = cursor.getString(2)
                    entryKata.Credit    = cursor.getString(3)
                    entryKata.Link1     = cursor.getString(4)
                    entryKata.Link2     = cursor.getString(5)

                    listArtiKata.add(entryKata)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return listArtiKata
    }

    // ------------------------------------------------------------------------------
    // BACA KATA dari dalam Kamus : BEBERAPA KATA MIRIP -----------------------------
    fun cariKataMirip(kata:String): ArrayList<ArtiKata>{
        val listArtiKata : ArrayList<ArtiKata> = ArrayList()
        val db = this.readableDatabase
        val selectKataQuery = "SELECT * FROM $DATABASE_TABLE WHERE $KATA LIKE '%" + kata + "%' ORDER BY $KATA ASC"
        val cursor = db.rawQuery(selectKataQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val entryKata = ArtiKata()

                    entryKata.Kata      = cursor.getString(0)
                    entryKata.Arti      = cursor.getString(1)
                    entryKata.Contoh    = cursor.getString(2)
                    entryKata.Credit    = cursor.getString(3)
                    entryKata.Link1     = cursor.getString(4)
                    entryKata.Link2     = cursor.getString(5)

                    listArtiKata.add(entryKata)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return listArtiKata
    }

    // ------------------------------------------------------------------------------
    // BACA KATA dari dalam Kamus : Berdasarkan CREDIT ------------------------------
    fun cariKataByKontrib(inKontrib:String?): ArrayList<ArtiKata>{
        val listArtiKata : ArrayList<ArtiKata> = ArrayList()
        val db = this.readableDatabase
        val selectKataQuery = "SELECT * FROM $DATABASE_TABLE WHERE $CREDIT='" + inKontrib + "'"
        val cursor = db.rawQuery(selectKataQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val entryKata = ArtiKata()

                    entryKata.Kata      = cursor.getString(0)
                    entryKata.Arti      = cursor.getString(1)
                    entryKata.Contoh    = cursor.getString(2)
                    entryKata.Credit    = cursor.getString(3)
                    entryKata.Link1     = cursor.getString(4)
                    entryKata.Link2     = cursor.getString(5)

                    listArtiKata.add(entryKata)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return listArtiKata
    }

    // ------------------------------------------------------------------------------
    // BACA KATA dari dalam Kamus : SEMUA -------------------------------------------
    fun listAllKata():ArrayList<ArtiKata> {
        val listArtiKata : ArrayList<ArtiKata> = ArrayList()
        val db = this.readableDatabase
        val selectKataQuery = "SELECT * FROM $DATABASE_TABLE ORDER BY $KATA ASC"
        val cursor = db.rawQuery(selectKataQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val entryKata = ArtiKata()

                    entryKata.Kata = cursor.getString(0)
                    entryKata.Arti = cursor.getString(1)
                    entryKata.Contoh = cursor.getString(2)
                    entryKata.Credit = cursor.getString(3)
                    entryKata.Link1= cursor.getString(4)
                    entryKata.Link2= cursor.getString(5)

                    listArtiKata.add(entryKata)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return listArtiKata
    }

    // ------------------------------------------------------------------------------
    // BACA Data KONTRIBUTOR dari dalam Kamus ---------------------------------------
    fun listAllKontrib():ArrayList<String> {
        val listKontrib: ArrayList<String> = ArrayList()
        val db = this.readableDatabase
        val selectKontribQuery = "SELECT DISTINCT $CREDIT FROM $DATABASE_TABLE WHERE $CREDIT<>'' ORDER BY $CREDIT ASC"
        //val selectKontribQuery = "SELECT $CREDIT FROM $DATABASE_TABLE WHERE $CREDIT<>'' ORDER BY $CREDIT ASC"
        val cursor = db.rawQuery(selectKontribQuery, null)

        Log.v("listAllKontrib:", selectKontribQuery)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    //Log.v("listAllKontrib:", cursor.getColumnIndex("$CREDIT").toString())
                    val tmpKontrib = cursor.getString(0)
                    //Log.v("listAllKontrib:", tmpKontrib)

                    listKontrib.add(tmpKontrib)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return listKontrib
    }

    // ------------------------------------------------------------------------------
    // BACA Data KONTRIBUTOR dari dalam Kamus ---------------------------------------
    fun listKataByKontrib(namaKontrib:String):ArrayList<String> {
        val listKata: ArrayList<String> = ArrayList()
        val db = this.readableDatabase
        val selectKontribQuery = "SELECT $KATA FROM $DATABASE_TABLE WHERE $CREDIT='"+ namaKontrib + "' ORDER BY $KATA ASC"
        val cursor = db.rawQuery(selectKontribQuery, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    listKata.add(cursor.getString(0))
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()

        return listKata
    }


}
// -----------------------------------------------------------------------------