package com.fi.nucu.project1.com.fi.nucu.project1.customer

 object Customer {
    var customerId: Int = -1
    var customerName: String =""
    var address: String =""
    var scanQRcode: String= ""
    var customerEmail: String =""
    var customerphone: String = ""

    fun setCustomer(id: Int, name: String, address: String){
        this.customerId = id
        this.customerName = name
        this.address = address
    }
}