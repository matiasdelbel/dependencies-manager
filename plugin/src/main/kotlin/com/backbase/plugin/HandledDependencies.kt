package com.backbase.plugin

import com.backbase.plugin.model.DependencyConstraints
import com.backbase.plugin.model.DependencyName
import com.backbase.plugin.model.IssueLevel
import com.backbase.plugin.model.MinAllowVersion

val DEPENDENCIES = listOf(
    DependencyConstraints(
        dependency = DependencyName(
            group = "androidx.appcompat",
            name = "appcompat"
        ),
        constraints = listOf(
            MinAllowVersion(
                version = "1.3.0",
                issueLevel = IssueLevel.ERROR
            )
        )
    ),
    DependencyConstraints(
        dependency = DependencyName(
            group = "com.google.android.material",
            name = "material"
        ),
        constraints = listOf(
            MinAllowVersion(
                version = "1.2.1",
                issueLevel = IssueLevel.ERROR
            )
        )
    )
)
