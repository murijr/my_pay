package com.mofaia.mypay.app.data.entity

import java.util.*

data class Quotation(var purchaseQuotation: Double? = null, var salesQuotation: Double? = null,
                     var dateHourQuotation: Date? = null) {

    companion object {

        const val BRITA_ROOT_KEY = "value"
        const val BRITA_PURCHASE_QUOTATION = "cotacaoCompra"
        const val BRITA_SALES_QUOTATION = "cotacaoVenda"
        const val BRITA_DATEHOUR_QUOTATION = "dataHoraCotacao"

        const val BITCOIN_ROOT_KEY = "ticker"
        const val BITCOIN_PURCHASE_QUOTATION = "buy"
        const val BITCOIN_SALES_QUOTATION = "sell"
        const val BITCOIN_DATEHOUR_QUOTATION = "date"

    }

}