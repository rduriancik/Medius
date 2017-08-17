package com.example.robert.medius.libs

import com.example.robert.medius.libs.base.EventBus

/**
 * Created by robert on 7.8.2017.
 */
class GreenRobotEventBus(val eventBus: org.greenrobot.eventbus.EventBus) : EventBus {

    override fun register(subscriber: Any) {
        eventBus.register(subscriber)
    }

    override fun unregister(subscriber: Any) {
        eventBus.unregister(subscriber)
    }

    override fun post(event: Any) {
        eventBus.post(event)
    }
}