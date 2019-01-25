package com.mofaia.mypay.app.data.repository.quotation

import com.google.firebase.firestore.CollectionReference

class QuotationFirestoreDataSource(
        private val collections: CollectionReference)
    : QuotationDataSource {

    override fun saveQuotation() {
    }

    override fun syncQuotations() {
    }

    override fun getAllQuotations() {
    }

}