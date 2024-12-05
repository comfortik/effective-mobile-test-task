package com.example.effective_mobile_test_task.features.main


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.effective_mobile_test_task.R
import com.example.effective_mobile_test_task.domain.models.Offer
import com.example.effective_mobile_test_task.features.common.defaultTextFieldColors
import com.example.effective_mobile_test_task.features.common.isCyrillic
import com.example.effective_mobile_test_task.features.main.model.AirTicketAction
import com.example.effective_mobile_test_task.features.main.model.AirTicketsIntent
import org.koin.androidx.compose.koinViewModel

@Composable
fun AirTicketsScreen(
    viewModel: AirTicketsViewModel = koinViewModel(),
    navigateToSearchScreen:()->Unit
) {
    val state = viewModel.screenState.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.action.collect{
            when(it){
                is AirTicketAction.NavigateToSearchScreen-> navigateToSearchScreen()
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.value.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            AirTicketScreenContent(
                offers = state.value.offers,
                departureCity = state.value.departureCity,
                arrivedCity = state.value.arriveCity,
                onDepartureCityChanged = { viewModel.handleIntent(AirTicketsIntent.OnDepartureCityChanged(it)) },
                onModalViewVisible = navigateToSearchScreen
            )
        }
    }



}


@Composable
fun AirTicketScreenContent(
    offers: List<Offer>,
    departureCity: String,
    arrivedCity: String,
    onDepartureCityChanged: (String)->Unit,
    onModalViewVisible: () -> Unit
){
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ){
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 52.dp,
                    bottom = 38.dp),
            text= "Поиск дешевых \nавиабилетов",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 22.sp
            ),
            )
        SearchItem(
            departureCity = departureCity,
            arrivedCity = arrivedCity,
            onDepartureCityChanged = onDepartureCityChanged,
            onModalWindowVisibleChanged = onModalViewVisible,
        )
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 52.dp,
                    bottom = 16.dp),
            text= "Музыкально отлететь",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 22.sp
            )
        )
        LazyRow {
            val offers = offers
            items(offers){ offer->
                LazyRowItem(offer)
            }

        }

    }
}


@Composable
fun SearchItem(
    departureCity: String,
    arrivedCity: String,
    onDepartureCityChanged: (String)->Unit,
    onModalWindowVisibleChanged: ()->Unit
){
    Box(modifier = Modifier.fillMaxWidth()
        .background(colorResource(R.color.item_dark_gray), RoundedCornerShape(16.dp))
        .padding(16.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth()
            .background(colorResource(R.color.item_light_gray), RoundedCornerShape(16.dp))
            .padding(horizontal = 8.dp)
        ){
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                tint = colorResource(R.color.light_gray)
            )
            Spacer(Modifier.width(12.dp))
            Column {
                SearchTextField(text = departureCity,
                    hint = stringResource(R.string.from),
                    onTextChanged = onDepartureCityChanged
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Gray)
                )
                SearchTextField(
                    modifier = Modifier.clickable {
                        onModalWindowVisibleChanged()
                    },
                    text = arrivedCity,
                    hint = stringResource(R.string.to),
                    onTextChanged = {},
                    enabled = false
                )

            }
        }
    }
}



@Composable
fun LazyRowItem(offer:Offer){
    Column(Modifier.padding(8.dp)) {
        Image(
            modifier = Modifier.clip(RoundedCornerShape(16.dp)).size(132.dp),
            painter = painterResource(offer.image),
            contentDescription = null)
        Spacer(Modifier.height(8.dp))
        Text(text = offer.title, style = MaterialTheme.typography.displaySmall)
        Spacer(Modifier.height(8.dp))
        Text(text = offer.town, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(painter = painterResource(R.drawable.ic_airplane),
                tint = colorResource(R.color.light_gray),
                contentDescription = null)
            Text(text = "от ${offer.price}", style = MaterialTheme.typography.titleLarge)
        }



    }
}


@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String = "Москва",
    hint: String,
    textFieldColors: TextFieldColors = defaultTextFieldColors(),
    textStyle: TextStyle = MaterialTheme.typography.displaySmall,
    onTextChanged: (String)->Unit = {},
    enabled: Boolean = true
){


        TextField(
            modifier = modifier,
            value = text,
            textStyle  = textStyle,
            onValueChange ={
                if (it.all { it.isCyrillic() }) onTextChanged(it)
            },
            colors = textFieldColors,
            enabled = enabled,
            placeholder = { Text(text =hint, style = TextStyle(color = colorResource(R.color.light_gray)))}

        )



}