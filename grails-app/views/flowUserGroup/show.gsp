<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userGroup.label', default: 'UserGroup')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-userGroup" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-userGroup" class="content scaffold-list" role="main">
            <h1>User Group</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <ol class="property-list strategy">

                <li class="fieldcontain">
                    <span id="name-label" class="property-label">Name</span>
                    <div class="property-value" aria-labelledby="name-label">${userGroup?.name}</div>
                </li>
            </ol>

            <h1>Sprints</h1>
            <table>
                <thead>
                <tr>
                    <g:sortableColumn property="name" title="Name" />
                    <td>Sprint</td>
                    <td>Start</td>
                    <td>End</td>
                </tr>
                </thead>
                <tbody>
                <g:each in="${userGroup?.sprints}" var="sprint" status="i">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${sprint?.name}</td>
                        <td>${sprint?.startDate}</td>
                        <td>${sprint?.endDate}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </body>
</html>