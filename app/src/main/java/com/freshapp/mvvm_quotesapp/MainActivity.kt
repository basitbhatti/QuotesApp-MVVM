package com.freshapp.mvvm_quotesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.freshapp.mvvm_quotesapp.db.Quote
import com.freshapp.mvvm_quotesapp.db.QuoteDatabase
import com.freshapp.mvvm_quotesapp.repository.QuoteRepository
import com.freshapp.mvvm_quotesapp.ui.theme.MVVMQuotesAppTheme
import com.freshapp.mvvm_quotesapp.viewmodel.MainVMFactory
import com.freshapp.mvvm_quotesapp.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {


    lateinit var viewModel: MainViewModel
    lateinit var db: QuoteDatabase
    lateinit var repository: QuoteRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = QuoteDatabase.getInstance(applicationContext)
        repository = QuoteRepository(db.getDao())

        viewModel =
            ViewModelProvider(this, MainVMFactory(repository)).get(MainViewModel::class.java)


        setContent {
            MVVMQuotesAppTheme {

                var quotes : List<Quote> by remember {
                    mutableStateOf(mutableListOf())
                }

                viewModel.getQuotes().observe(this){
                    quotes = it
                }

                LazyColumn {
                    items(quotes){
                        QuoteItem(quote = it)
                    }
                }
            }
        }
    }
}

@Composable
fun QuoteItem(modifier: Modifier = Modifier, quote: Quote) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = modifier.padding(start = 15.dp, top = 25.dp),
                text = quote.text,
                fontSize = 22.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )

            Text(
                modifier = modifier.padding(start = 15.dp, top = 10.dp, bottom = 25.dp),
                text = quote.author,
                color = Color.Gray,
                fontFamily = FontFamily.Serif
            )

        }
    }
}

