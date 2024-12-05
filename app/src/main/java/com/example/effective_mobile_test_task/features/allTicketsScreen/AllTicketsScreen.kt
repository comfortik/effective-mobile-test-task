package com.example.effective_mobile_test_task.features.allTicketsScreen

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.effective_mobile_test_task.R
import com.example.effective_mobile_test_task.domain.models.TicketOffer
import com.example.effective_mobile_test_task.features.common.defaultTextFieldColors
import com.example.effective_mobile_test_task.features.main.SearchTextField
import com.example.effective_mobile_test_task.features.searchScreen.Line
import org.koin.androidx.compose.koinViewModel
import java.time.Instant
import java.time.LocalDate

@Composable
fun AllTicketsScreen (
    departureCity: String="",
    arrivedCity: String="",
    viewModel: AllTicketsViewModel= koinViewModel(),
    onButtonClicked: (String, String, String)->Unit,
    onBackPressed: ()->Unit
){
    LaunchedEffect(Unit) {
        viewModel.handleIntent(AllTicketsIntent.OnDepartureCityChanged(departureCity))
        viewModel.handleIntent(AllTicketsIntent.OnArrivedCityChanged(arrivedCity))
    }

    val state = viewModel.screenState.collectAsState()
    if (state.value.isDatePickerVisible) {
        DataPicker(
            onDateSelected = { selectedDate ->
                viewModel.handleIntent(AllTicketsIntent.OnDateChanged(selectedDate))
            },
            onClose = {
                viewModel.handleIntent(AllTicketsIntent.CloseDatePicker)
            }
        )
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ){
        if(state.value.isLoading){
            Box(Modifier.fillMaxSize()){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

        }else{
            Spacer(Modifier.height(32.dp))
            SearchItemAllTickets(
                departureCity = state.value.departureCity,
                arrivedCity = state.value.arrivedCity,
                onDepartureCityChanged = {viewModel.handleIntent(AllTicketsIntent.OnDepartureCityChanged(it))},
                onArrivedCityChanged = {viewModel.handleIntent(AllTicketsIntent.OnArrivedCityChanged(it))},
                onCrossClicked = {viewModel.handleIntent(AllTicketsIntent.OnArrivedCityChanged(""))},
                onViceVersa = {
                    val saveArrivedCity = state.value.arrivedCity
                    val saveDepartureCity = state.value.departureCity
                    viewModel.handleIntent(AllTicketsIntent.OnArrivedCityChanged(saveDepartureCity))
                    viewModel.handleIntent(AllTicketsIntent.OnDepartureCityChanged(saveArrivedCity))
                },
                onBackPressed
            )
            Spacer(Modifier.height(16.dp))
            HorizontalItems(
                crossText = state.value.backDate?.toString()?: stringResource(R.string.back),
                toDateText = state.value.toDate.toString(),
                onToDateClicked = {viewModel.handleIntent(AllTicketsIntent.OpenDatePicker(DatePickerType.ToDatePicker))},
                onCrossClicked = {viewModel.handleIntent(AllTicketsIntent.OpenDatePicker(DatePickerType.BackDatePicker))}
            )
            Spacer(Modifier.height(16.dp))
            DirectFlightsCard(
                ticketOffers = state.value.ticketOffers
            )
            Spacer(Modifier.height(16.dp))
            BlueButton {
                onButtonClicked(state.value.departureCity, state.value.arrivedCity, state.value.toDate.toString())
            }
            Spacer(modifier = Modifier.height(12.dp))
            SubscribeItem()
        }


    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataPicker(
    onDateSelected: (LocalDate) -> Unit,
    onClose: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(
        onDismissRequest = { onClose() },
        confirmButton = {
            TextButton(onClick = {
                val selectedDateMillis = datePickerState.selectedDateMillis
                if (selectedDateMillis != null) {
                    val selectedDate = Instant.ofEpochMilli(selectedDateMillis)
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate()
                    onDateSelected(selectedDate)
                }
                onClose()
            }) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = { onClose() }) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Composable
fun HorizontalItems(
    crossText: String,
    toDateText: String,
    onToDateClicked: ()->Unit,
    onCrossClicked: () -> Unit
){
    LazyRow {
       item {
           CrossItem(
               icon = R.drawable.ic_cross,
               text = crossText,
               onClick = onCrossClicked
           )
           Spacer(Modifier.width(12.dp))
       }
        item{
            CrossItem(
                text = toDateText,
                onClick = onToDateClicked
            )
            Spacer(Modifier.width(12.dp))
        }
        item {
            CrossItem(
                icon = R.drawable.ic_profile,
                text = stringResource(R.string.one_econom)
            )
            Spacer(Modifier.width(12.dp))
        }
        item {
            CrossItem(
                icon = R.drawable.ic_filtres,
                text = stringResource(R.string.filtres)
            )
            Spacer(Modifier.width(12.dp))
        }

    }
}

@Composable
fun CrossItem(
    icon: Int? =null,
    text: String= "",
    onClick: ()->Unit = {}
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .background(
                colorResource(R.color.item_dark_gray),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(icon!=null){
            val rotation = if(icon == R.drawable.ic_cross) 45f else 0f
            Icon(
                modifier = Modifier.size(16.dp).graphicsLayer(rotationZ = rotation),
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.White
            )
            Spacer(Modifier.width(12.dp))
        }


        Text(
            text = if(text.isNotBlank())text else stringResource(R.string.back),
            color = Color.White,
            style = MaterialTheme.typography.titleLarge.copy(fontStyle = FontStyle.Italic),
            modifier = Modifier
                .clickable {
                    onClick()
                }
        )

    }
}

@Composable
fun SubscribeItem(){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.dark_grey_background), RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 16.dp)
    ){
        Icon(
            painter = painterResource(R.drawable.ic_bell),
            contentDescription = null,
            tint = colorResource(R.color.blue)
        )
        Spacer(Modifier.width(12.dp))
        Text(text = "Подписка на цену")
        Spacer(Modifier.weight(1f))
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.toggle),
            contentDescription = null
        )
    }

}



@Composable
fun BlueButton(
    onClick:()->Unit
){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.blue)
        )
    ) {
        Text(text = "Просмотреть все билеты",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge.copy(fontStyle = FontStyle.Italic),
        )
    }


}

@Preview(showBackground = true)
@Composable
fun DirectFlightsCard(
    ticketOffers: List<TicketOffer> = listOf(
        TicketOffer(
            id = 1,
            title = "aaa",
            time_range = "12:30, 20:00",
            price = 120
        )
    )
){
    Column(
        Modifier.fillMaxWidth()
            .background(colorResource(R.color.dark_gray), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Прямые рейсы",
            style = MaterialTheme.typography.displayMedium,
        )
        Spacer(Modifier.height(12.dp))
        ticketOffers.forEachIndexed{index, ticket->
            DirectFlightsItem(
                count = index,
                item = ticket
            )
            Line()
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Показать все",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge.copy(
                color = colorResource(R.color.blue)
            ),

        )
    }
}

@Preview
@Composable
fun DirectFlightsItem(
    count: Int = 1,
    item: TicketOffer = TicketOffer(
        id = 1,
        title = "aaa",
        time_range = "12:30, 20:00",
        price = 120
    )
){

    Row(Modifier.padding(vertical = 12.dp)){

        when(count){

            0-> FlightItemCircle(color = colorResource(R.color.pink))
            1-> FlightItemCircle(color = colorResource(R.color.blue))
            2-> FlightItemCircle(color = colorResource(R.color.white))
        }
        Spacer(Modifier.width(12.dp))
        Column {
            Row (
                Modifier.fillMaxWidth()
            ){
                Text(text = item.title,
                    style = MaterialTheme.typography.titleLarge.copy(fontStyle = FontStyle.Italic),
                    maxLines = 1
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "${item.price} ₽",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = colorResource(R.color.blue),
                        fontStyle = FontStyle.Italic
                    ),
                )
            }

            Text(text = item.time_range,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1
            )
        }


    }

}

@Composable
fun FlightItemCircle(
    color: Color
){
    Box(
        modifier = Modifier
            .padding(top = 4.dp)
            .size(24.dp)
            .background(color, CircleShape)
    ){}

}


@Composable
fun SearchItemAllTickets(
    departureCity: String ,
    arrivedCity: String ,
    onDepartureCityChanged: (String)->Unit ,
    onArrivedCityChanged: (String)->Unit,
    onCrossClicked: ()->Unit,
    onViceVersa: ()->Unit,
    onBackPressed: () -> Unit
){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.item_dark_gray), RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp)
        ){
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onBackPressed() }
                    .align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = Color.White
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SearchTextField(text = departureCity,
                        hint = stringResource(R.string.from),
                        onTextChanged = onDepartureCityChanged,
                        textFieldColors = defaultTextFieldColors().copy(
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = colorResource(R.color.item_dark_gray),
                            unfocusedContainerColor = colorResource(R.color.item_dark_gray)
                        )
                    )
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onViceVersa()
                            }
                            .size(12.dp),
                        painter = painterResource(R.drawable.ic_change),
                        contentDescription = null,
                        tint = Color.White
                    )

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Gray)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SearchTextField(text = arrivedCity,
                        hint = stringResource(R.string.to),
                        onTextChanged = onArrivedCityChanged,
                        textFieldColors = defaultTextFieldColors().copy(
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = colorResource(R.color.item_dark_gray),
                            unfocusedContainerColor = colorResource(R.color.item_dark_gray)
                        )
                    )
                    Icon(
                        modifier = Modifier
                            .clickable {
                                onCrossClicked()
                            }
                            .size(12.dp),
                        painter = painterResource(R.drawable.ic_cross),
                        contentDescription = null,
                        tint = Color.White
                    )

                }


            }
        }

}