package com.dekay.healthmegame

class Sprint {
    String name
    Date startDate = new Date().clearTime()
    Date endDate = new Date().clearTime() + 7

    static constraints = {
        name nullable: true
        startDate nullable: false
        endDate nullable: false
    }
}
