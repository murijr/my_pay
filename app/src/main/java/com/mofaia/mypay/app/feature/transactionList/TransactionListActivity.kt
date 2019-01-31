package com.mofaia.mypay.app.feature.transactionList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.databinding.ActivityTransactionListBinding
import com.mofaia.mypay.app.extension.setContentView
import kotlinx.android.synthetic.main.activity_transaction_list.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class TransactionListActivity : AppCompatActivity() {

    private val viewModel: TransactionListViewModel by viewModel()

    private val adapter: TransactionListAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityTransactionListBinding>(viewModel, R.layout.activity_transaction_list)
        handleUI()
        configureObservers()
    }

    private fun handleUI() {
        configureAdapter()
    }

    private fun configureObservers() {
        observerTransactionList()
    }

    private fun observerTransactionList() {
        viewModel.transactionList.observe(this, Observer {
            adapter.clear()
            adapter.add(it)
        })
    }

    private fun configureAdapter() {
        list_transaction.adapter = adapter
    }

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, TransactionListActivity::class.java)
            activity.startActivity(intent)
        }
    }

}
