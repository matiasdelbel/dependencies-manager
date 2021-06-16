package com.backbase.plugin.model

data class Dependency(val name: DependencyName, val version: String)

data class DependencyName(val group: String, val name: String)
