package com.example.effective_mobile_test_task.features.ticketsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.effective_mobile_test_task.R
import com.example.effective_mobile_test_task.domain.models.Ticket
import com.example.effective_mobile_test_task.features.allTicketsScreen.FlightItemCircle
import org.koin.androidx.compose.koinViewModel

@Composable
fun TicketsScreen(departureCity: String,
                  arrivedCity: String, date: String,
                  viewModel: TicketsViewModel = koinViewModel(),
                  onBackPressed: ()->Unit
) {

    val state = viewModel.screenState.collectAsState()


    Column(
        Modifier.padding(horizontal = 16.dp)
    ){
        if(state.value.isLoading){
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
                )
            }
        }else{
            TitleCard(
                departureCity,
                arrivedCity,
                date,
                onBackPressed
            )
            Spacer(Modifier.height(32.dp))
            LazyColumn {
                items(state.value.tickets){
                    TicketItem(ticket = it)
                }
            }
        }
    }

}

@Preview
@Composable
fun TicketItem(
    ticket: Ticket = Ticket(
        badge = "hard",
        price = 120,
        timeRange = "12:30-16:30",
        departureAirport = "AUZ",
        arrivalAirport = "SVO",
        time = "3.0",
        hasTransfer = "С пересадкой"
    )
){
    Box(
        Modifier.padding(bottom = 24.dp)
    ){
        val paddings = if(!ticket.badge.isNullOrEmpty())12.dp else 0.dp
        if(!ticket.badge.isNullOrEmpty()){
            Box(
                Modifier
                    .background(color = colorResource(R.color.blue), RoundedCornerShape(50.dp))
                    .padding(vertical = 2.dp, horizontal = 8.dp)
                    .zIndex(1f)
            ) {
                Text(ticket.badge,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontStyle = FontStyle.Italic
                    ))
            }
        }
        Column(Modifier
            .padding(top = paddings)
            .fillMaxWidth()
            .background(colorResource(R.color.dark_gray), RoundedCornerShape(8.dp))
            .padding(20.dp)
        ) {
            Text(
                text = "${ticket.price} ₽",
                style = MaterialTheme.typography.displayLarge
            )
            Row (
                Modifier.fillMaxWidth().padding(top = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                FlightItemCircle(colorResource(R.color.pink))
                Spacer(Modifier.width(12.dp))
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = ticket.timeRange,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontStyle = FontStyle.Italic
                        )
                    )
                    Text(
                        text = "${ticket.departureAirport}  ${ticket.arrivalAirport}",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontStyle = FontStyle.Italic,
                            color = colorResource(R.color.light_gray)
                        )
                    )
                }
                Spacer(Modifier.width(12.dp))
                Text(
                    modifier = Modifier.align(Alignment.Top),
                    text = "${ticket.time} в пути / ${ticket.hasTransfer}",
                    style = MaterialTheme.typography.titleLarge
                )
            }

        }

    }
}

@Composable
private fun TitleCard(
    departureCity: String,
    arrivedCity: String,
    date: String,
    onBackPressed: () -> Unit
){
    Row(
        Modifier.background(
            color = colorResource(R.color.dark_grey_background)
        ).padding(horizontal = 8.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.clickable { onBackPressed() },
            painter = painterResource(R.drawable.ic_back),
            contentDescription = null,
            tint = colorResource(R.color.blue)
        )
        Spacer(Modifier.width(12.dp))
        Column(
            Modifier.weight(1f)
        ) {
            Text(
                text = "$departureCity - $arrivedCity",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "$date, 1 пассажир",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = colorResource(R.color.light_gray)
                )
            )
        }
    }
}
