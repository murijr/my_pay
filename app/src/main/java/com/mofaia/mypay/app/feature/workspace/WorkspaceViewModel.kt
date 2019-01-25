package com.mofaia.mypay.app.feature.workspace

import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.data.repository.quotation.QuotationDataSource

class WorkspaceViewModel(quotationRepository: QuotationDataSource): ViewModel() {

    init {
        quotationRepository.syncQuotations()
    }

}