package com.mofaia.mypay.app.data.mapper

import com.mofaia.mypay.app.common.DateManipulator
import com.mofaia.mypay.app.data.entity.Quotation

class QuotationBitcoinMapper(private val dataManipulator: DateManipulator): Mapper<Map<String, Any>?, Quotation> {

    override infix fun toObject(obj: Map<String, Any>?): Quotation {
        val map = obj?.get(Quotation.BITCOIN_ROOT_KEY) as Map<*, *>
        val purchaseQuotation = map[Quotation.BITCOIN_PURCHASE_QUOTATION]
                .toString().toDoubleOrNull()
        val salesQuotation = map[Quotation.BITCOIN_SALES_QUOTATION]
                .toString().toDoubleOrNull()
        val dateHourQuotation = dataManipulator
                .unixTimeStampToDate(map[Quotation.BITCOIN_DATEHOUR_QUOTATION].toString().toBigDecimal().longValueExact())
        return Quotation(purchaseQuotation, salesQuotation, dateHourQuotation)
    }

    override infix fun fromObject(obj: Quotation): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map[Quotation.BITCOIN_PURCHASE_QUOTATION] = obj.purchaseQuotation.toString()
        map[Quotation.BITCOIN_SALES_QUOTATION] = obj.salesQuotation.toString()
        map[Quotation.BITCOIN_DATEHOUR_QUOTATION] = obj.dateHourQuotation.toString()
        return  map.toMap()
    }

}