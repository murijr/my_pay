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
import java.math.BigDecimal

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
        finish()
    }

    private fun observerAmountTextChanged() {
        edit_text_amount.addTextChangedListener(MonetaryTextWatcher(edit_text_amount))
        edit_text_amount.doOnTextChanged { text, _, _, _ ->
            viewModel.amount.set(CurrencyFormatter.stringCurrencyToBigDecimal(text.toString()))
        }
    }

    private fun configureTitleAndBtnText() {
        title_transaction.text = prepareTitleAndBtnText()
        btn_transaction.text = prepareTitleAndBtnText()
    }

    private fun prepareExtraInfo() {
        intent.extras?.get(EXTRA_BALANCE)?.let { viewModel.balance.set(it as BigDecimal) }
        intent.extras?.get(EXTRA_QUOTATION)?.let { viewModel.quotation.set(it as BigDecimal) }
        viewModel.transactionType.set(intent.extras?.getSerializable(EXTRA_TRANSACTION_TYPE) as Transaction.Type)
    }

    private fun prepareTitleAndBtnText() = when(viewModel.transactionType.get()) {
        Transaction.Type.BITCOIN_CREDIT -> getString(R.string.text_bitcoin_wallet_credit)
        Transaction.Type.BRITA_CREDIT -> getString(R.string.text_brita_wallet_credit)
        Transaction.Type.BITCOIN_DEBIT -> getString(R.string.text_bitcoin_wallet_debit)
        Transaction.Type.BRITA_DEBIT -> getString(R.string.text_brita_wallet_debit)
        else -> ""
    }

    companion object {

        const val REQUEST_TRANSACTION = 12
        const val RESULT_TRANSACTION_OK = 121
        const val EXTRA_QUOTATION = "EXTRA_QUOTATION"
        const val EXTRA_BALANCE = "EXTRA_BALANCE"
        const val EXTRA_TRANSACTION_TYPE = "EXTRA_TRANSACTION_TYPE"

        fun start(activity: Activity, quotation: BigDecimal, balance: BigDecimal
                  , transactionType: Transaction.Type) {
            val intent = Intent(activity, TransactionActivity::class.java)
            intent.putExtra(EXTRA_TRANSACTION_TYPE, transactionType)
            intent.putExtra(EXTRA_QUOTATION, quotation)
            intent.putExtra(EXTRA_BALANCE, balance)
            activity.startActivityForResult(intent, REQUEST_TRANSACTION)
        }


    }

}
