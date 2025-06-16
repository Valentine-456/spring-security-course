package com.example.security.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jdk.jfr.Category

@Entity
@Table(name = "customer")
open class Customer protected constructor() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null
    @Column
    var email : String = ""
    @Column
    var pwd : String = ""
    @Column
    var role : String = ""

    constructor(
        email: String,
        password: String,
        role: String
    ) : this() {
        this.email = email
        this.pwd = password
        this.role = role
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Customer

        if (id != other.id) return false
        if (email != other.email) return false
        if (pwd != other.pwd) return false
        if (role != other.role) return false

        return true
    }
}