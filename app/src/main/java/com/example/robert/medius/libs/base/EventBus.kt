package com.example.robert.medius.libs.base

/**
 * Created by robert on 7.8.2017.
 */
interface EventBus {
    fun register(subscriber: Any)
    fun unregister(subscriber: Any)
    fun post(event: Any)
}