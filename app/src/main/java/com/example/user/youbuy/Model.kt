package com.example.user.youbuy

object Model {

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
    )

    data class Category (
        val id: String?,
        val name: String?
    )

    data class Product(
        val name: String?,
        val location: String?,
        val postedDate: String?,
        val description: String?,
        val status: String?,
        val sold: Boolean?,
        val id: String?,
        val images: Array<String>?,
        val price: Number?,
        val categoryId: String?,
        val customerId: String?,
        val owner: Customer?,
        val customerToWish: Array<Customer>?,
        val category: Category?
    )

}
