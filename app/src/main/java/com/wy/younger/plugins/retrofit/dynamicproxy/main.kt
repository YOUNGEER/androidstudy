package com.wy.younger.plugins.retrofit.dynamicproxy

fun main(args: Array<String>) {
    val student = DynamicProxy().bind(Student()) as Person

    val teacher = DynamicProxy().bind(Teacher()) as Person


    student.doWork("student")
    teacher.doWork("teacher")
}