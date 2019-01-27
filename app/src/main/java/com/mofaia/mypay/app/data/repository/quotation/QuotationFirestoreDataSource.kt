package com.mofaia.mypay.app.data.repository.quotation

import com.google.firebase.firestore.*
import com.mofaia.mypay.app.constant.DOCUMENT_PATH_BITCOIN_QUOTATION
import com.mofaia.mypay.app.constant.DOCUMENT_PATH_BRITA_QUOTATION
import com.mofaia.mypay.app.data.entity.Quotation
import com.mofaia.mypay.app.extension.toMoney

class QuotationFirestoreDataSource(
        private val collections: CollectionReference)
    : QuotationDataSource {

    private fun prepareQuotation(quotation: Quotation): Quotation {
        quotation.purchaseQuotation = quotation.purchaseQuotation?.toMoney()?.amount?.toDouble()
        quotation.purchaseQuotation = quotation.salesQuotation?.toMoney()?.amount?.toDouble()
        return quotation
    }

    override fun saveBritaQuotation(quotation: Quotation) {
        collections.document(DOCUMENT_PATH_BRITA_QUOTATION).set(prepareQuotation(quotation))
    }

    override fun saveBitcoinQuotation(quotation: Quotation) {
        collections.document(DOCUMENT_PATH_BITCOIN_QUOTATION).set(prepareQuotation(quotation))
    }

    override fun syncQuotations() {

    }

    override fun getBritaQuotation(onSuccess: (Quotation) -> Unit, onError: () -> Unit) {

        collections.document(DOCUMENT_PATH_BRITA_QUOTATION).addSnapshotListener { snapshot, exception ->

            if(exception != null || snapshot == null || snapshot.data.isNullOrEmpty()) {
                onError()
            }else {
                onSuccess(snapshot.toObject(Quotation::class.java)!!)
            }

        }

    }

    override fun getBitcoinQuotation(onSuccess: (Quotation) -> Unit, onError: () -> Unit) {

        collections.document(DOCUMENT_PATH_BITCOIN_QUOTATION).addSnapshotListener { snapshot, exception ->

            if(exception != null || snapshot == null || snapshot.data.isNullOrEmpty()) {
                onError()
            }else {
                onSuccess(snapshot.toObject(Quotation::class.java)!!)
            }

        }


    }

}