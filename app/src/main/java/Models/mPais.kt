package Models

import java.util.ArrayList


data class mEntrada (
    var count: Int? = 0,
    val entries: ArrayList<mEntries> = arrayListOf()
)


data class mEntries(
    var API: String? = "",
    var Description: String? = "",
    var Auth: String? = "",
    var HTTPS: String? = "",
    var Cors: String? = "",
    var Link: String? = "",
    var Category: String? = "",

)


