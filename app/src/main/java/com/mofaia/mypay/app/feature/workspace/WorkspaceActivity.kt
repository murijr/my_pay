package com.mofaia.mypay.app.feature.workspace

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.databinding.ActivityWorkspaceBinding
import com.mofaia.mypay.app.extension.setContentView
import com.mofaia.mypay.app.extension.showToast
import com.mofaia.mypay.app.feature.transaction.TransactionActivity
import kotlinx.android.synthetic.main.activity_workspace.*
import org.koin.android.viewmodel.ext.android.viewModel
import android.view.Menu
import android.view.MenuItem
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.feature.transactionList.TransactionListActivity
import com.mofaia.mypay.app.feature.userAuth.UserAuthActivity

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
        observeClickBtnExchangeBitcoin()
        observeClickBtnExchangeBrita()
    }

    private fun handleClickBtnPurchaseBitcoin() {
        viewModel.purchaseQuotationBitcoin.get()?.let {
            TransactionActivity.start(this,  it, viewModel.balanceBRL.get()!!
                    , Transaction.Type.BITCOIN_CREDIT)
        }
    }

    private fun handleClickBtnPurchaseBrita() {
        viewModel.purchaseQuotationBrita.get()?.let {
            TransactionActivity.start(this,  it, viewModel.balanceBRL.get()!!
                    , Transaction.Type.BRITA_CREDIT)
        }
    }

    private fun handleClickBtnSellBitcoin() {
        viewModel.salesQuotationBitcoin.get()?.let {
            TransactionActivity.start(this,  it, viewModel.balanceBitcoin.get()!!
                    , Transaction.Type.BITCOIN_DEBIT)
        }
    }

    private fun handleClickBtnSellBrita() {
        viewModel.salesQuotationBrita.get()?.let {
            TransactionActivity.start(this,  it, viewModel.balanceBrita.get()!!
                    , Transaction.Type.BRITA_DEBIT)
        }
    }

    private fun handleClickBtnExchangeBitcoin() {
        viewModel.exchangeQuotationBitcoin.get()?.let {
            TransactionActivity.start(this,  it, viewModel.balanceBitcoin.get()!!
                    , Transaction.Type.BITCOIN_EXCHANGE)
        }
    }

    private fun handleClickBtnExchangeBrita() {
        viewModel.exchangeQuotationBrita.get()?.let {
            TransactionActivity.start(this,  it, viewModel.balanceBrita.get()!!
                    , Transaction.Type.BRITA_EXCHANGE)
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

    private fun observeClickBtnExchangeBitcoin() {
        btn_exchange_bitcoin.setOnClickListener { handleClickBtnExchangeBitcoin() }
    }

    private fun observeClickBtnExchangeBrita() {
        btn_exchange_brita.setOnClickListener {handleClickBtnExchangeBrita()}
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        handleTransactionWasPerformed(requestCode, resultCode)
    }

    private fun handleTransactionWasPerformed(requestCode: Int, resultCode: Int) {
        if(requestCode == TransactionActivity.REQUEST_TRANSACTION && resultCode
                == TransactionActivity.RESULT_TRANSACTION_OK) {
            showToast(R.string.transaction_was_performed)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.workspace_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {  handleItemMenuSelected(it.itemId) }
        return super.onOptionsItemSelected(item)
    }

    private fun handleItemMenuSelected(itemId: Int) {
        when(itemId) {
            R.id.report -> TransactionListActivity.start(this)
            R.id.logout -> performLogout()
        }
    }

    private fun performLogout() {
        viewModel.logout()
        UserAuthActivity.start(this)
        finish()
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, WorkspaceActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
