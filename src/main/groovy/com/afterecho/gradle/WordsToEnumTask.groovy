package com.afterecho.gradle

import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

import javax.lang.model.element.Modifier

import static com.squareup.javapoet.TypeSpec.*

class WordsToEnumTask extends DefaultTask {
    String group = "blogplugin"
    String description = "Makes a list of words into an enum"

    @InputFile
    File wordsFile

    @OutputDirectory
    File outDir

    @TaskAction
    def makeWordsIntoEnums() {
        Builder wordsEnumBuilder = enumBuilder("WordsEnum").addModifiers(Modifier.PUBLIC)
        wordsFile.readLines().each {
            wordsEnumBuilder.addEnumConstant(it).build()
        }
        TypeSpec wordsEnum = wordsEnumBuilder.build();
        JavaFile javaFile = JavaFile.builder("com.afterecho.android.util", wordsEnum).build();
        javaFile.writeTo(outDir)
    }
}
