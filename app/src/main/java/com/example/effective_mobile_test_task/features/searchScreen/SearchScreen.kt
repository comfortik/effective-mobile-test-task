package com.example.effective_mobile_test_task.features.searchScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.effective_mobile_test_task.R
import com.example.effective_mobile_test_task.features.common.defaultTextFieldColors
import com.example.effective_mobile_test_task.features.main.SearchItem
import com.example.effective_mobile_test_task.features.main.SearchTextField
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel()
) {
    val state = viewModel.screenState.collectAsState()


    Column(Modifier.fillMaxSize().padding(top = 32.dp)
        .background(colorResource(R.color.dark_grey_background),
            RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        )
        .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Box(modifier = Modifier
            .background(colorResource(R.color.modal_top_gray))
            .align(Alignment.CenterHorizontally)
            .width(38.dp)
            .height(5.dp)
        )
        Spacer(modifier = Modifier.padding(top = 25.dp))
        SearchItemTickets(
            departureCity = state.value.departureCity,
            arrivedCity = state.value.arriveCity,
            onDepartureCityChanged = {viewModel.handleIntent(SearchIntent.onDepartureCityChanged(it))},
            onArriveCityChanged = {viewModel.handleIntent(SearchIntent.OnArrivedCityChanged(it))}
        )

    }
}


@Composable
fun SearchItemTickets(
    departureCity: String,
    arrivedCity: String ,
    onDepartureCityChanged: (String)->Unit = {},
    onArriveCityChanged: (String)->Unit = {}
){

    Column(modifier = Modifier.fillMaxWidth()
        .background(colorResource(R.color.item_dark_gray), RoundedCornerShape(16.dp))
        .padding(horizontal = 12.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_airplane_search),
                contentDescription = null,
                tint = colorResource(R.color.light_gray)
            )
            SearchTextField(
                textFieldColors = defaultTextFieldColors().copy(
                    unfocusedContainerColor = colorResource(R.color.item_dark_gray),
                    focusedContainerColor = colorResource(R.color.item_dark_gray),
                    disabledContainerColor = colorResource(R.color.item_dark_gray)
                ) ,
                text = departureCity,
                hint = stringResource(R.string.from),
                onTextChanged = onDepartureCityChanged
            )
            if(departureCity.isNotBlank()){
                Icon(
                    modifier = Modifier
                        .clickable { onDepartureCityChanged("") }
                        .align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(colorResource(R.color.item_light_gray))
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                painter = painterResource(R.drawable.ic_search),
                contentDescription = null,
                tint = Color.White,
            )
            SearchTextField(
                modifier = Modifier.background(colorResource(R.color.item_dark_gray)),
                text = arrivedCity,
                hint = stringResource(R.string.to),
                textFieldColors = defaultTextFieldColors().copy(
                    unfocusedContainerColor = colorResource(R.color.item_dark_gray),
                    focusedContainerColor = colorResource(R.color.item_dark_gray),
                    disabledContainerColor = colorResource(R.color.item_dark_gray)
                ),
                onTextChanged = onArriveCityChanged
            )
            if(arrivedCity.isNotBlank()){
                Icon(
                    modifier = Modifier
                        .clickable { onArriveCityChanged("") }
                        .align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }

    }
}

