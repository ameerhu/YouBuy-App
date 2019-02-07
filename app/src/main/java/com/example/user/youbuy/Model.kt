package com.example.user.youbuy


object Model {

    data class UserLogin(
        val id:String?,
        val ttl:Number?,
        val created: String?,
        val userId: String?,
        val user: Customer
    ) : java.io.Serializable

    data class Customer(
        val id: String?,
        val name: String?,
        val address: String?,
        val phone: String?,
        val email: String?,
        val realm: String?,
        val username: String?,
        val emailVerified: Boolean?,
        val firstName: String?,
        val lastName: String?,
        val owns: Array<Product>?
    ) : java.io.Serializable

    data class Category (
        val id: String?,
        val name: String?
    ) : java.io.Serializable

    data class Product (
        val name: String? = null,
        val location: String? = null,
        val postedDate: String? = null,
        val description: String? = null,
        val status: String? = null,
        val sold: Boolean? = null,
        val id: String? = null,
        val images: Array<String>? = null,
        val price: Number? = null,
        val categoryId: String? = null,
        val customerId: String? = null,
        val owner: Customer? = null,
        val customerToWish: Array<Customer>? = null,
        val category: Category? = null
    ) : java.io.Serializable

}
