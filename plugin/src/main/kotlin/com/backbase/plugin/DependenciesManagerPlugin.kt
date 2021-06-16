package com.backbase.plugin

import com.backbase.plugin.model.Dependency
import com.backbase.plugin.model.DependencyName
import com.backbase.plugin.model.IssueLevel
import org.gradle.api.Plugin
import org.gradle.api.Project

class DependencyManagementPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        // Register task in charge of checking the dependencies version
        project.task(TASK_CHECK_DEPENDENCIES) {
            doLast {
                project
                    .configurations
                    .forEach { configuration ->
                        configuration
                            .dependencies
                            .forEach { dependency ->
                                val projectDependency = Dependency(
                                    name = DependencyName(group = dependency.group.orEmpty(), name = dependency.name),
                                    version = dependency.version.orEmpty()
                                )

                                val matchingDepConstraint = DEPENDENCIES
                                    .firstOrNull { it.dependency == projectDependency.name }

                                matchingDepConstraint
                                    ?.constraints
                                    ?.mapNotNull {
                                        if (it.apply(projectDependency)) it.issueLevel
                                        else null
                                    }
                                    ?.forEach {
                                        if (it == IssueLevel.WARNING) println("You should update this dependency: $projectDependency")
                                        else throw IllegalStateException("Invalid dependency version $projectDependency")
                                    }
                            }
                    }
            }
        }
        project.tasks.whenTaskAdded { if (name == TASK_COMPILE) dependsOn(TASK_CHECK_DEPENDENCIES) }
    }
}

private const val TASK_COMPILE = "compileDebugKotlin"
private const val TASK_CHECK_DEPENDENCIES = "checkDependencies"
