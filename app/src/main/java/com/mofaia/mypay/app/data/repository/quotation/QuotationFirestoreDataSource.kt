package com.mofaia.mypay.app.data.repository.quotation

import com.google.firebase.firestore.*
import com.mofaia.mypay.app.constant.DOCUMENT_PATH_BITCOIN_QUOTATION
import com.mofaia.mypay.app.constant.DOCUMENT_PATH_BRITA_QUOTATION
import com.mofaia.mypay.app.data.entity.Quotation
import com.mofaia.mypay.app.data.mapper.QuotationBitcoinMapper
import com.mofaia.mypay.app.data.mapper.QuotationBritaMapper

class QuotationFirestoreDataSource(
        private val collections: CollectionReference
        , private val quotationBitcoinMapper: QuotationBitcoinMapper
        , private val quotationBritaMapper: QuotationBritaMapper)
    : QuotationDataSource {

    override fun saveBritaQuotation(quotation: Quotation) {
        collections.document(DOCUMENT_PATH_BRITA_QUOTATION).set(quotationBritaMapper fromObject quotation)
    }

    override fun saveBitcoinQuotation(quotation: Quotation) {
        collections.document(DOCUMENT_PATH_BITCOIN_QUOTATION).set(quotationBitcoinMapper fromObject quotation)
    }

    override fun syncQuotations() {

    }

    override fun getBritaQuotation(onSuccess: (Quotation) -> Unit, onError: () -> Unit) {

        collections.document(DOCUMENT_PATH_BRITA_QUOTATION).addSnapshotListener { snapshot, exception ->

            if(exception != null || snapshot == null || snapshot.data.isNullOrEmpty()) {
                onError()
            }else {
                (quotationBritaMapper toObject snapshot).let(onSuccess)
            }

        }

    }

    override fun getBitcoinQuotation(onSuccess: (Quotation) -> Unit, onError: () -> Unit) {

        collections.document(DOCUMENT_PATH_BITCOIN_QUOTATION).addSnapshotListener { snapshot, exception ->
            if(exception != null || snapshot == null || snapshot.data.isNullOrEmpty()) {
                onError()
            }else {
                (quotationBitcoinMapper toObject snapshot).let(onSuccess)
            }
        }
    }

}