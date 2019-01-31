package com.mofaia.mypay.app.data.entity

import java.math.BigDecimal

data class Quotation(var purchaseQuotation: BigDecimal? = null
                     , var salesQuotation: BigDecimal? = null) {

    companion object {

        const val BRITA_ROOT_KEY = "value"
        const val BRITA_PURCHASE_QUOTATION = "cotacaoCompra"
        const val BRITA_SALES_QUOTATION = "cotacaoVenda"

        const val BITCOIN_ROOT_KEY = "ticker"
        const val BITCOIN_PURCHASE_QUOTATION = "buy"
        const val BITCOIN_SALES_QUOTATION = "sell"

    }

}