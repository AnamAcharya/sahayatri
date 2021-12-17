//package com.anam.sahayatri.response
//
//class UserResponse {
//}

package response

import entity.Customer

data class UserResponse(
    val success : Boolean? = null,
    val token : String? = null,
    val message:String?=null,
    val user : Customer?=null,
)