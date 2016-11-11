package com.dekay.healthmegame

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class FlowUserGroupController {
    def springSecurityService

    def show() {
        log.debug("list ${params}")
        User user = springSecurityService.getCurrentUser()
        log.debug("user = ${user}")

        render view: 'show', model:[userGroup: user?.userGroup]
    }

    def list(Integer max) {
        log.debug("list ${params}")
        params.max = Math.min(max ?: 10, 100)
        respond UserGroup.list(params), model:[userGroupCount: UserGroup.count()]
    }
}
