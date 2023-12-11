package com.example.client.model

enum class ClientStatus(
    val priceRange: IntRange
) {
    BRONZE(priceRange = Int.MIN_VALUE until 1000),
    SILVER(priceRange = 1000 until 5000),
    GOLD(priceRange = 5000 until 10000),
    PLATINUM(priceRange = 10000 until Int.MAX_VALUE),

    UNKNOWN(priceRange = Int.MIN_VALUE..Int.MAX_VALUE)
}

data class Client(
    val id: Long = 0L,
    val phone: String,
    val clientStatus: ClientStatus = ClientStatus.BRONZE
) {

    fun toEntity() = ClientEntity(
        phone = phone
    )

    companion object {
        fun fromEntity(entity: ClientEntity) = Client(
            id = entity.id,
            phone = entity.phone
        )
    }
}