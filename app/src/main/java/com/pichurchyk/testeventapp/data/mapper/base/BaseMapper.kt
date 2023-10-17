package com.pichurchyk.testeventapp.data.mapper.base

interface BaseMapper<F, T> {
    fun mapFrom(from: F): T
}
