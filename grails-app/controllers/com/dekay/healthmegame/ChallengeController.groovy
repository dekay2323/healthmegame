package com.dekay.healthmegame

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ChallengeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Challenge.list(params), model:[challengeCount: Challenge.count()]
    }

    def show(Challenge challenge) {
        respond challenge
    }

    def create() {
        respond new Challenge(params)
    }

    @Transactional
    def save(Challenge challenge) {
        if (challenge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (challenge.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond challenge.errors, view:'create'
            return
        }

        challenge.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'challenge.label', default: 'Challenge'), challenge.id])
                redirect challenge
            }
            '*' { respond challenge, [status: CREATED] }
        }
    }

    def edit(Challenge challenge) {
        respond challenge
    }

    @Transactional
    def update(Challenge challenge) {
        if (challenge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (challenge.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond challenge.errors, view:'edit'
            return
        }

        challenge.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'challenge.label', default: 'Challenge'), challenge.id])
                redirect challenge
            }
            '*'{ respond challenge, [status: OK] }
        }
    }

    @Transactional
    def delete(Challenge challenge) {

        if (challenge == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        challenge.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'challenge.label', default: 'Challenge'), challenge.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'challenge.label', default: 'Challenge'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
