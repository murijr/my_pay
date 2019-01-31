package com.mofaia.mypay.app.data.mapper

import com.google.firebase.firestore.DocumentSnapshot
import com.mofaia.mypay.app.data.entity.Quotation
import java.math.BigDecimal

class QuotationBritaMapper() {

    infix fun toObject(obj: Map<String, Any>): Quotation? {

        val quotationBritaList  = (obj[Quotation.BRITA_ROOT_KEY] as ArrayList<*>)

        if(quotationBritaList.size <= 0) {
            return null
        }

        val map = quotationBritaList[0] as Map<*, *>
        val purchaseQuotation = map[Quotation.BRITA_PURCHASE_QUOTATION]
                .toString().toBigDecimal()
        val salesQuotation = map[Quotation.BRITA_SALES_QUOTATION]
                .toString().toBigDecimal()
        return Quotation(purchaseQuotation, salesQuotation)

    }

    infix fun toObject(obj: DocumentSnapshot): Quotation {

        val quotation = Quotation()

        obj[Quotation.BRITA_PURCHASE_QUOTATION]?.let {
            quotation.purchaseQuotation = BigDecimal(it.toString())
        }
        obj[Quotation.BRITA_SALES_QUOTATION]?.let {
            quotation.salesQuotation = BigDecimal(it.toString())
        }
        return quotation

    }


    infix fun fromObject(obj: Quotation?): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map[Quotation.BRITA_PURCHASE_QUOTATION] = obj?.purchaseQuotation.toString()
        map[Quotation.BRITA_SALES_QUOTATION] = obj?.salesQuotation.toString()
        return  map.toMap()
    }

}