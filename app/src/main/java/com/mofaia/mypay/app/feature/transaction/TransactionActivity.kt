package com.mofaia.mypay.app.feature.transaction

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.common.MonetaryTextWatcher
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.databinding.ActivityTransactionBinding
import com.mofaia.mypay.app.extension.configureToolbar
import com.mofaia.mypay.app.extension.setContentView
import com.mofaia.mypay.app.util.CurrencyFormatter
import kotlinx.android.synthetic.main.activity_transaction.*
import org.koin.android.viewmodel.ext.android.viewModel

class TransactionActivity : AppCompatActivity() {

    private val viewModel: TransactionViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityTransactionBinding>(viewModel, R.layout.activity_transaction)
        handleUI()
        configureObservers()
    }

    private fun handleUI() {
        configureToolbar()
        prepareExtraInfo()
        configureTitleAndBtnText()
        observerAmountTextChanged()
    }

    private fun configureObservers() {
        observerTransactionWasPerformed()
    }

    private fun observerTransactionWasPerformed() {
        viewModel.transactionWasPerformed.observe(this, Observer {
            performResultOK()
        })
    }

    private fun performResultOK() {
        setResult(RESULT_TRANSACTION_OK)
        finishAfterTransition()
    }

    private fun observerAmountTextChanged() {
        edit_text_amount.addTextChangedListener(MonetaryTextWatcher(edit_text_amount))
        edit_text_amount.doOnTextChanged { text, _, _, _ ->
            viewModel.amount.set(CurrencyFormatter.stringCurrencyToDouble(text.toString()))
        }
    }

    private fun configureTitleAndBtnText() {
        title_transaction.text = prepareTitleAndBtnText()
        btn_transaction.text = prepareTitleAndBtnText()
    }

    private fun prepareExtraInfo() {
        intent.extras?.getDouble(EXTRA_BALANCE)?.let { viewModel.balance.set(it) }
        intent.extras?.getDouble(EXTRA_QUOTATION)?.let { viewModel.quotation.set(it) }
        viewModel.transactionType.set(intent.extras?.getString(EXTRA_TRANSACTION_TYPE))
    }

    private fun prepareTitleAndBtnText() = when(viewModel.transactionType.get()) {
        Transaction.TRNSACTION_TYPE_BITCOIN_WALLET_CREDIT -> getString(R.string.text_bitcoin_wallet_credit)
        Transaction.TRNSACTION_TYPE_BRITA_WALLET_CREDIT -> getString(R.string.text_brita_wallet_credit)
        Transaction.TRNSACTION_TYPE_BITCOIN_WALLET_DEBIT -> getString(R.string.text_bitcoin_wallet_debit)
        Transaction.TRNSACTION_TYPE_BRITA_WALLET_DEBIT -> getString(R.string.text_brita_wallet_debit)
        else -> ""
    }

    companion object {

        const val REQUEST_TRANSACTION = 12
        const val RESULT_TRANSACTION_OK = 121
        const val EXTRA_QUOTATION = "EXTRA_QUOTATION"
        const val EXTRA_BALANCE = "EXTRA_BALANCE"
        const val EXTRA_TRANSACTION_TYPE = "EXTRA_TRANSACTION_TYPE"

        fun start(activity: Activity, quotation: Double, balance: Double, transactionType: String) {
            val intent = Intent(activity, TransactionActivity::class.java)
            intent.putExtra(EXTRA_TRANSACTION_TYPE, transactionType)
            intent.putExtra(EXTRA_QUOTATION, quotation)
            intent.putExtra(EXTRA_BALANCE, balance)
            activity.startActivityForResult(intent, REQUEST_TRANSACTION)
        }

    }

}
