package com.github.zettianmaximus.generatetestcase.services

import com.intellij.openapi.project.Project
import com.github.zettianmaximus.generatetestcase.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
