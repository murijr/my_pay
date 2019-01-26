package com.mofaia.mypay.app.data.mapper

interface Mapper<I, O> {
    fun toObject(obj: I): O
    fun fromObject(obj: O): I
}