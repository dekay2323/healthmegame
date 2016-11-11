package com.dekay.healthmegame

class Category {
    String name

    static constraints = {
        name nullable: false, blank: false
    }

    @Override
    String toString() {
        "${name}"
    }
}
