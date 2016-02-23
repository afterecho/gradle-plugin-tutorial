package com.afterecho.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class BlogPlugin implements Plugin<Project> {

    @Override
    void apply(Project target) {
        target.tasks.create(name: "showDevices", type: ShowDevicesTask)

        target.android.applicationVariants.all { variant ->
            File inputWordFile = new File(target.projectDir, "plugin_words.txt")
            File outputDir = new File(target.buildDir, "generated/source/wordsToEnum/${variant.dirName}")
            def task = target.tasks.create(name: "wordsToEnum${variant.name.capitalize()}", type: WordsToEnumTask) {
                outDir = outputDir
                wordsFile = inputWordFile
            }
            variant.registerJavaGeneratingTask task, outputDir
        }
    }
}
