package com.mofaia.mypay.app.data.repository.quotation

import com.google.firebase.firestore.*
import com.mofaia.mypay.app.constant.DOCUMENT_PATH_BITCOIN_QUOTATION
import com.mofaia.mypay.app.constant.DOCUMENT_PATH_BRITA_QUOTATION
import com.mofaia.mypay.app.data.entity.Quotation

class QuotationFirestoreDataSource(
        private val collections: CollectionReference)
    : QuotationDataSource {

    override fun saveBritaQuotation(quotation: Quotation) {
        collections.document(DOCUMENT_PATH_BRITA_QUOTATION).set(quotation)
    }

    override fun saveBitcoinQuotation(quotation: Quotation) {
        collections.document(DOCUMENT_PATH_BITCOIN_QUOTATION).set(quotation)
    }

    override fun syncQuotations() {

    }

    override fun getBritaQuotation(onSuccess: (Quotation) -> Unit, onError: () -> Unit) {

        collections.document(DOCUMENT_PATH_BRITA_QUOTATION).addSnapshotListener { snapshot, exception ->

            if(exception != null && snapshot == null) {
                onError()
            }else {
                onSuccess(snapshot?.toObject(Quotation::class.java)!!)
            }

        }

    }

    override fun getBitcoinQuotation(onSuccess: (Quotation) -> Unit, onError: () -> Unit) {

        collections.document(DOCUMENT_PATH_BITCOIN_QUOTATION).addSnapshotListener { snapshot, exception ->

            if(exception != null && snapshot == null) {
                onError()
            }else {
                onSuccess(snapshot?.toObject(Quotation::class.java)!!)
            }

        }


    }

}