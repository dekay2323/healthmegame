package com.dekay.healthmegame

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
@Transactional(readOnly = true)
class UserGroupController {
    static scaffold = UserGroup
}
