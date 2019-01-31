package com.mofaia.mypay.app.feature.transactionList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.data.repository.wallet.WalletDataSource

class TransactionListViewModel(private val walletRepository: WalletDataSource): ViewModel() {

    val transactionList = MutableLiveData<List<Transaction>>()

    init {

        walletRepository.getExtract({
            transactionList.value = it
        }, {})



    }

}