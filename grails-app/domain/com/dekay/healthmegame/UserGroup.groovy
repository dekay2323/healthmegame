package com.dekay.healthmegame

class UserGroup {
    String name
    static hasMany = [users: User, sprints: Sprint]

    static constraints = {
        name nullable: false, blank: false
        users nullable: true
        sprints nullable: true
    }

    @Override
    String toString() {
        "${name}"
    }
}
