package com.dekay.healthmegame

class Challenge {
    String name
    String description
    int points = 1
    Category category

    static belongsTo = [sprint: Sprint]

    static constraints = {
        name nullable: false, blank: false
        description nullable: true
        points nullable: false
        category nullable: true
    }

    @Override
    String toString() {
        "${name}"
    }

}
