package com.appezzy.app.data.model

data class NewsList (
    val status: Int = 0,
    val jumlah: Int = 0,
    val data: List<News> = arrayListOf()
)