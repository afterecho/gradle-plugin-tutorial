package com.afterecho.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ShowDevicesTask extends DefaultTask {
    String group = "blogplugin"
    String description = "Runs adb devices command"

    @TaskAction
    def showDevices() {
        def adbExe = project.android.getAdbExe().toString()
        println "${adbExe} devices".execute().text
    }
}
