package repository

import api.ApiRequest
import api.CustomerAPI
import api.ServiceBuilder
import entity.Customer
import response.UserResponse

//class CustomerRepo {
//}

class CustomerRepo:ApiRequest() {
    val myApi = ServiceBuilder.buildService(CustomerAPI::class.java)

    suspend fun registerUser(customer: Customer): UserResponse {
        return apiRequest {
            myApi.registerUser(customer)
        }
    }

    suspend fun checkUser(phone: String, password: String): UserResponse {
        return apiRequest {
            myApi.checkUser(phone, password)
        }
    }
}