package com.mofaia.mypay.app.feature.transactionList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mofaia.mypay.app.R
import com.mofaia.mypay.app.common.DateManipulator
import com.mofaia.mypay.app.data.entity.Transaction
import com.mofaia.mypay.app.util.CurrencyFormatter
import kotlinx.android.synthetic.main.adapter_item_transaction_list.view.*

class TransactionListAdapter(private val inflater: LayoutInflater, private val dataManipulator: DateManipulator): RecyclerView.Adapter<TransactionListViewHolder>() {

    var dataStore: MutableList<Transaction> = mutableListOf()

    fun add(task: Transaction) {
        dataStore.add(task)
        notifyDataSetChanged()
    }

    fun add(taskList: List<Transaction>) {
        dataStore.addAll(taskList)
        notifyDataSetChanged()
    }

    fun clear(){
        dataStore.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionListViewHolder {
        val view = inflater.inflate(R.layout.adapter_item_transaction_list, parent, false)

        return TransactionListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionListViewHolder, position: Int) {

        val transaction: Transaction? = dataStore[position]
        holder.view.let {
            it.value.text =  transaction?.value?.let { value -> CurrencyFormatter.doubleToCurrency(value) }
            it.type.text =  transaction?.type.orEmpty()
            it.date.text =  transaction?.createAt?.let { date -> dataManipulator.formatt(date.toDate()) }
        }

    }

    override fun getItemCount(): Int = dataStore.size

}