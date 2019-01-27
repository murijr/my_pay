package com.mofaia.mypay.app.feature.workspace

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.databinding.ActivityWorkspaceBinding
import com.mofaia.mypay.app.extension.setContentView
import com.mofaia.mypay.app.extension.showToast
import com.mofaia.mypay.app.feature.transaction.TransactionActivity
import kotlinx.android.synthetic.main.activity_workspace.*
import org.koin.android.viewmodel.ext.android.viewModel

class WorkspaceActivity : AppCompatActivity() {

    val viewModel: WorkspaceViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityWorkspaceBinding>(viewModel, R.layout.activity_workspace)
        handleUI()
    }

    private fun handleUI() {
        observeClickBtnPurchaseBitcoin()
        observeClickBtnPurchaseBrita()
        observeClickBtnSellBitcoin()
        observeClickBtnSellBrita()
    }

    private fun handleClickBtnPurchaseBitcoin() {
        viewModel.purchaseQuotationBitcoin.get()?.let {
            TransactionActivity.start(this,  it, viewModel.balanceBRL.get()!!
                    , Transaction.TRNSACTION_TYPE_BITCOIN_WALLET_CREDIT)
        }
    }

    private fun handleClickBtnPurchaseBrita() {
        viewModel.purchaseQuotationBrita.get()?.let {
            TransactionActivity.start(this,  it, viewModel.balanceBRL.get()!!
                    , Transaction.TRNSACTION_TYPE_BRITA_WALLET_CREDIT)
        }
    }

    private fun handleClickBtnSellBitcoin() {
        viewModel.salesQuotationBitcoin.get()?.let {
            TransactionActivity.start(this,  it, viewModel.balanceBRL.get()!!
                    , Transaction.TRNSACTION_TYPE_BITCOIN_WALLET_DEBIT)
        }
    }

    private fun handleClickBtnSellBrita() {
        viewModel.salesQuotationBrita.get()?.let {
            TransactionActivity.start(this,  it, viewModel.balanceBRL.get()!!
                    , Transaction.TRNSACTION_TYPE_BRITA_WALLET_DEBIT)
        }
    }

    private fun observeClickBtnPurchaseBitcoin() {
        btn_purchase_bitcoin.setOnClickListener { handleClickBtnPurchaseBitcoin() }
    }

    private fun observeClickBtnPurchaseBrita() {
        btn_purchase_brita.setOnClickListener {handleClickBtnPurchaseBrita()}
    }

    private fun observeClickBtnSellBitcoin() {
        btn_sell_bitcoin.setOnClickListener { handleClickBtnSellBitcoin() }
    }

    private fun observeClickBtnSellBrita() {
        btn_sell_brita.setOnClickListener {handleClickBtnSellBrita()}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        handleTransactionWasPerformed()
    }

    private fun handleTransactionWasPerformed() {
        showToast(R.string.transaction_was_performed)
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, WorkspaceActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
