package com.fi.nucu.project1.com.fi.nucu.project1.customer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Person (
    var customerId: Int = -1,
    var customerName: String?,
    var address: String?)
{
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    var scanQRcode: String= ""
    var customerEmail: String =""
    var customerphone: String = ""
}



