package com.nillith.android.tools.build
import org.gradle.api.Plugin
import org.gradle.api.Project

class AutoVersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create('autoVersion', AutoVersion)
    }
}
