package com.justForPractice.JustForPractice


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.justForPractice.JustForPractice.MyAdapter.Companion.EXTRA_DATE
import com.justForPractice.JustForPractice.MyAdapter.Companion.EXTRA_DESCRIPTION
import com.justForPractice.JustForPractice.MyAdapter.Companion.EXTRA_ID
import com.justForPractice.JustForPractice.MyAdapter.Companion.EXTRA_TITLE
import kotlinx.android.synthetic.main.add_edit_item.*
import java.text.SimpleDateFormat
import java.util.*


class AddEditItem :  MyActivity() {
    private lateinit var itemViewModel: ItemViewModel
    private var idItemSetMode: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_edit_item)
        setSupportActionBar(findViewById(R.id.toolbar2))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        idItemSetMode = intent.getLongExtra(EXTRA_ID, -1)
        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)



        if (idItemSetMode == (-1).toLong()) {
            title = getString(R.string.AddNew)
            txvDate.text = SimpleDateFormat("yyyy/MM/dd HH:mm",Locale.getDefault()).format(Date()).toString()
        } else {
            title = getString(R.string.EditItem)
            txvEditTitle.setText(intent.getStringExtra(EXTRA_TITLE))
            txvEditDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            txvDate.text =intent.getStringExtra(EXTRA_DATE)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_edit_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.save_item -> {
                if (txvEditTitle.text.isEmpty() || txvEditDescription.text.isEmpty()) {
                    Toast.makeText(this,getString( R.string.eTDisEmpty), Toast.LENGTH_SHORT)
                        .show()
                    return false
                }
                if (idItemSetMode == (-1).toLong()) {
                    val itemT = Item(
                        Title = txvEditTitle.text.toString(),
                        Description = txvEditDescription.text.toString(),
                        Date = Date()
                    )
                    itemViewModel.insert(itemT)
                    finish()
                } else {
                    val itemT = Item(
                        idItemSetMode,
                        Title = txvEditTitle.text.toString(),
                        Description = txvEditDescription.text.toString(),
                        Date = Date()
                    )
                    itemViewModel.update(itemT)
                    finish()
                }

            }
            android.R.id.home -> {
                finish()
            }
            else -> {
                return false
            }
        }
        return when (item.itemId) {
            R.id.save_item -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}