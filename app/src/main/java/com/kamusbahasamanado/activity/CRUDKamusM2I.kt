package com.kamusbahasamanado.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.kamusbahasamanado.R
import com.kamusbahasamanado.data.DBHandler
import com.kamusbahasamanado.model.*
import kotlinx.android.synthetic.main.activity_crud_kamus_m2i.*

class CRUDKamusM2I : AppCompatActivity() {

    private lateinit var dbHandler: DBHandler
    private var crudStatus : String = ""
    private var entryKata = ArtiKata()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud_kamus_m2i)

        // Prepare DBHandler, set EditMode and Focus
        dbHandler = DBHandler(this)
        setReadMode()

        // CREATE Button Listener -------------
        btnCari.setOnClickListener() {
            if (txtKata.text.isNotEmpty()) {
                val hasilCari = dbHandler.cariKataPersis(txtKata.text.toString())

                if (hasilCari.size>0) {
                    txtArti.setText     (hasilCari[0].Arti  )
                    txtContoh.setText   (hasilCari[0].Contoh)
                    txtCredit.setText   (hasilCari[0].Credit)
                    txtLink1.setText    (hasilCari[0].Link1 )
                    txtLink2.setText    (hasilCari[0].Link2 )

                    btnCreate.setEnabled(false)
                    btnUpdate.setEnabled(true)
                    btnDelete.setEnabled(true)

                    // Set Status to NEUTRAL ----------
                    crudStatus = ""

                    // Set Focus on Kata --------------
                    txtKata.requestFocus()
                }
                else {
                    Toast.makeText(this, "Kata : "+txtKata.text.toString()+ " TIDAK DITEMUKAN!", Toast.LENGTH_LONG).show()
                }
            }
            else {
                Toast.makeText(this, "Entry Kata TIDAK BOLEH KOSONG!", Toast.LENGTH_LONG).show()
            }
        }

        // CREATE Button Listener -------------
        btnCreate.setOnClickListener() {
            // Set Status to CREATE -----------
            crudStatus = "CREATE"

            // Clear All Fields ---------------
            clearAllFields()

            // Set Edit Mode ------------------
            setEditMode()

            // Set Focus on Kata --------------
            txtKata.requestFocus()
        }

        // UPDATE Button Listener ----------------------------------------------
        btnUpdate.setOnClickListener() {
            // Set Status to UPDATE -----------
            crudStatus = "UPDATE"

            // Set Edit Mode ------------------
            setEditMode()

            // Set Focus on Kata --------------
            txtKata.requestFocus()
        }

        // DELETE Button Listener -------------
        btnDelete.setOnClickListener() {
            // Set Status to DELETE -----------
            crudStatus = "DELETE"

            // Set Edit Mode ------------------
            setEditMode()

            // Set Focus on Kata --------------
            txtKata.requestFocus()
        }

        // SAVE Button Listener ---------------
        btnSave.setOnClickListener() {
            // Get Kata from Fields -----------
            entryKata.Kata = txtKata.text.toString()
            entryKata.Arti = txtArti.text.toString()
            entryKata.Contoh = txtContoh.text.toString()
            entryKata.Credit = txtCredit.text.toString()
            entryKata.Link1= txtLink1.text.toString()
            entryKata.Link2= txtLink2.text.toString()

            if (txtKata.text.isNotEmpty() && txtArti.text.isNotEmpty()) {
                // Write to Database --------------
                when (crudStatus) {
                    "CREATE" -> {
                        if (dbHandler.createKata(entryKata)) {
                            Toast.makeText(
                                this,
                                "Kata : " + entryKata.Kata + " SUKSES Dibuat!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                "Kata : " + entryKata.Kata + " GAGAL Dibuat!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    "UPDATE" -> {
                        if (dbHandler.updateKata(entryKata)) {
                            Toast.makeText(
                                this,
                                "Kata : " + entryKata.Kata + " SUKSES Diupdate!",
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                this,
                                "Kata : " + entryKata.Kata + " GAGAL Diupdate!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                    else -> {
                        Toast.makeText(this, "Kata : UNKOWN Error!", Toast.LENGTH_LONG).show()
                    }
                }

                // Clear All Fields ---------------
                clearAllFields()

                // Set Read Mode ------------------
                setReadMode()

            } else {
                Toast.makeText(this, "Entry Kata TIDAK LENGKAP!", Toast.LENGTH_LONG).show()
            }
        }

        // CANCEL Button Listener -------------
        btnCancel.setOnClickListener() {
            // Clear All Fields ---------------
            clearAllFields()

            // Set Read Mode ------------------
            setReadMode()
        }

        // OK Button Listener -----------------
        btnYES.setOnClickListener() {
            if (txtKata.text.isNotEmpty()) {
                // Write to Database : DELETE -----
                if (dbHandler.deleteKata(txtKata.text.toString())) {
                    Toast.makeText(this,"Kata : "+txtKata.text.toString()+" SUKSES Dihapus!", Toast.LENGTH_LONG).show()
                }
                else {
                    Toast.makeText(this,"Kata : "+txtKata.text.toString()+" GAGAL Dihapus!", Toast.LENGTH_LONG).show()
                }

                // Clear All Fields ---------------
                clearAllFields()

                // Set Read Mode ------------------
                setReadMode()
            }
            else {
                Toast.makeText(this, "Entry Kata TIDAK BOLEH KOSONG!", Toast.LENGTH_LONG).show()
            }
        }

        // NOK Button Listener ----------------
        btnNO.setOnClickListener() {
            // Write to Database : NOTHING ----
            Toast.makeText(this,"Kata : "+txtKata.text.toString()+" BATAL Dihapus!", Toast.LENGTH_LONG).show()

            // Clear All Fields ---------------
            clearAllFields()

            // Set Read Mode ------------------
            setReadMode()
        }

        // BACK Button Listener ---------------
        btnBack.setOnClickListener() {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }
    }

    fun clearAllFields() {
        // Clear All Fields
        txtKata.setText("")
        txtArti.setText("")
        txtContoh.setText("")
        txtCredit.setText("")
        txtLink1.setText("")
        txtLink2.setText("")
    }

    fun setReadMode() {
        // Disabled EditText Fields
        txtKata.setEnabled(true)
        txtArti.setEnabled(false)
        txtContoh.setEnabled(false)
        txtCredit.setEnabled(false)
        txtLink1.setEnabled(false)
        txtLink2.setEnabled(false)

        // Disabled Associated Buttons
        btnCari.setEnabled(true)
        btnCreate.setEnabled(true)
        btnUpdate.setEnabled(false)
        btnSave.setEnabled(false)
        btnCancel.setEnabled(false)
        btnDelete.setEnabled(false)
        btnYES.setEnabled(false)
        btnNO.setEnabled(false)
        btnBack.setEnabled(true)

        // Set Focus
        btnCreate.requestFocus()
    }

    fun setEditMode() {
        // Enabled EditText Fields
        txtKata.setEnabled(true)
        txtArti.setEnabled(true)
        txtContoh.setEnabled(true)
        txtCredit.setEnabled(true)
        txtLink1.setEnabled(true)
        txtLink2.setEnabled(true)

        // Disabled Associated Buttons
        if (crudStatus=="DELETE") {
            btnCari.setEnabled(false)
            btnCreate.setEnabled(false)
            btnUpdate.setEnabled(false)
            btnSave.setEnabled(false)
            btnCancel.setEnabled(false)
            btnDelete.setEnabled(false)
            btnYES.setEnabled(true)
            btnNO.setEnabled(true)
        }
        else {
            btnCari.setEnabled(false)
            btnCreate.setEnabled(false)
            btnUpdate.setEnabled(false)
            btnSave.setEnabled(true)
            btnCancel.setEnabled(true)
            btnDelete.setEnabled(false)
            btnYES.setEnabled(false)
            btnNO.setEnabled(false)
        }
        btnBack.setEnabled(true)

        // Set Focus
        txtKata.requestFocus()
    }
}
