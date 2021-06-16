package com.backbase.plugin.model

sealed class DependencyConstraint(val issueLevel: IssueLevel) {

    abstract fun apply(dependency: Dependency): Boolean
}

enum class IssueLevel {
    WARNING, ERROR
}

class MinAllowVersion(issueLevel: IssueLevel, private val version: String) : DependencyConstraint(issueLevel) {

    override fun apply(dependency: Dependency) = dependency.version < version
}
