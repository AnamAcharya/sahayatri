package entity

data class Customer(
    var _id:String? = null,
    var username: String? = null,
    var email:String?=null,
    var address:String?=null,
    var phone: String? = null,
    var password:String? = null,
    var profile_pic:String?= null,
    var userType:String?=null
)
