package com.fi.nucu.project1.com.fi.nucu.project1.customer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Person(@PrimaryKey
                 var customerId: Int = -1,
var customerName: String ="",
    var address: String ="",
    var scanQRcode: String= "",
    var customerEmail: String ="",
    var customerphone: String = ""
)
