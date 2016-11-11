package com.dekay.healthmegame

class UserGroup {
    String name
    static hasMany = [users: User]

    static constraints = {
        name nullable: false, blank: false
    }

    @Override
    String toString() {
        "${name}"
    }
}
