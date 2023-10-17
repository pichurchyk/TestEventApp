package com.pichurchyk.common.base

interface BaseMapper<F, T> {

    fun mapFrom(from: F): T
}
