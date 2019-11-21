package com.kamusbahasamanado.model

// -----------------------------------------------------------------------------
// Kelas ARTIKATA, memuat semua data suatu kata dalam Bahasa Manado,
//   yang mewakili satu entry dalam Kamus Bahasa Manado
class ArtiKata {
    var Kata    : String? = null // Kata Bahasa Manado
    var Arti    : String? = null // Arti Kata dalam Bahasa Indonesia/Inggris
    var Contoh  : String? = null // Contoh Pemakaian Kata
    var Credit  : String? = null // Credit untuk Kata Tambahan
    var Link1   : String? = null // Link Internal untuk Gambar terkait Kata
    var Link2   : String? = null // Link External untuk Gambar Terkait Kata
}
// -----------------------------------------------------------------------------