package com.dekay.healthmegame

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class HomeController {
    def springSecurityService

    def index() {
        log.debug("index ${params}")
        User user = springSecurityService.getCurrentUser()
        log.debug("user = ${user}")

        render controller: 'home', view: 'index', model:[user: user]
    }
}
