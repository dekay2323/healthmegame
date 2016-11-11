package com.dekay.healthmegame

class Challenge {
    String name
    String description
    int points = 1

    static belongsTo = [category: Category]

    static constraints = {
        name nullable: false, blank: false
        description nullable: true
        points nullable: false
    }

    @Override
    String toString() {
        "${name}"
    }

}
