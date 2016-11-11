package com.dekay.healthmegame

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserGroupController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UserGroup.list(params), model:[userGroupCount: UserGroup.count()]
    }

    def show(UserGroup userGroup) {
        respond userGroup
    }

    def create() {
        respond new UserGroup(params)
    }

    @Transactional
    def save(UserGroup userGroup) {
        if (userGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userGroup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userGroup.errors, view:'create'
            return
        }

        userGroup.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userGroup.label', default: 'UserGroup'), userGroup.id])
                redirect userGroup
            }
            '*' { respond userGroup, [status: CREATED] }
        }
    }

    def edit(UserGroup userGroup) {
        respond userGroup
    }

    @Transactional
    def update(UserGroup userGroup) {
        if (userGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (userGroup.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond userGroup.errors, view:'edit'
            return
        }

        userGroup.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userGroup.label', default: 'UserGroup'), userGroup.id])
                redirect userGroup
            }
            '*'{ respond userGroup, [status: OK] }
        }
    }

    @Transactional
    def delete(UserGroup userGroup) {

        if (userGroup == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        userGroup.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userGroup.label', default: 'UserGroup'), userGroup.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userGroup.label', default: 'UserGroup'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
