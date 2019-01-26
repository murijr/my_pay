package com.mofaia.mypay.app.data.mapper

import com.mofaia.mypay.app.common.DateManipulator
import com.mofaia.mypay.app.data.entity.Quotation

class QuotationBritaMapper(private val dataManipulator: DateManipulator): Mapper<Map<String, Any>?, Quotation?> {

    override infix fun toObject(obj: Map<String, Any>?): Quotation? {

        val quotationBritaList  = (obj?.get(Quotation.BRITA_ROOT_KEY) as ArrayList<*>)

        if(quotationBritaList.size <= 0) {
            return null
        }

        val map = quotationBritaList[0] as Map<*, *>
        val purchaseQuotation = map[Quotation.BRITA_PURCHASE_QUOTATION]
                .toString().toDoubleOrNull()
        val salesQuotation = map[Quotation.BRITA_SALES_QUOTATION]
                .toString().toDoubleOrNull()
        val dateHourQuotation = dataManipulator
                .stringToDate(dateString = map[Quotation.BRITA_DATEHOUR_QUOTATION].toString())
        return Quotation(purchaseQuotation, salesQuotation, dateHourQuotation)

    }

    override infix fun fromObject(obj: Quotation?): Map<String, Any>? {
        val map = mutableMapOf<String, Any>()
        map[Quotation.BRITA_PURCHASE_QUOTATION] = obj?.purchaseQuotation.toString()
        map[Quotation.BRITA_SALES_QUOTATION] = obj?.salesQuotation.toString()
        map[Quotation.BRITA_DATEHOUR_QUOTATION] = obj?.dateHourQuotation.toString()
        return  map.toMap()
    }

}