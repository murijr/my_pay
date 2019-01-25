package com.mofaia.mypay.app.di

import com.mofaia.mypay.app.common.DateManipulator
import org.koin.dsl.module.module
import java.util.*

val commonModule = module {

    factory { DateManipulator(Locale("pt", "BR")) }

}