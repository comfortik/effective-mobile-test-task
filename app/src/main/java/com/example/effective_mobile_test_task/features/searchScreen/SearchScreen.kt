package com.example.effective_mobile_test_task.features.searchScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.effective_mobile_test_task.R
import com.example.effective_mobile_test_task.features.common.defaultTextFieldColors
import com.example.effective_mobile_test_task.features.main.SearchItem
import com.example.effective_mobile_test_task.features.main.SearchTextField
import org.koin.androidx.compose.koinViewModel
import org.w3c.dom.Text

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = koinViewModel(),
    onPlugClick: () -> Unit,
    onSearchClick: (String, String)->Unit
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
            onArriveCityChanged = {viewModel.handleIntent(SearchIntent.OnArrivedCityChanged(it))},
            onSearchClicked = {onSearchClick(viewModel.screenState.value.departureCity, viewModel.screenState.value.arriveCity)}
        )
        Spacer(modifier = Modifier.height(32.dp))
        ButtonsRow(
            onPlanetClick = {
                viewModel.handleIntent(SearchIntent.OnArrivedCityChanged(it))
            },
            onPlugClick = onPlugClick
        )
        Spacer(modifier = Modifier.height(16.dp))
        val popular = "Популярное направление"
        val rows = listOf(
            Triple(
                R.drawable.stambul,
                "Стамбул",
                popular
            ), Triple(
                R.drawable.sochi,
                "Сочи",
                popular
            ),
            Triple(
                R.drawable.phucket,
                "Пхукет",
                popular
            ),
        )
        TownColumn(rows){
            viewModel.handleIntent(SearchIntent.OnArrivedCityChanged(it))
        }
    }
}
@Composable
fun TownColumn(
    rows: List<Triple<Int, String, String>>,
    thirdTextTextStyle: TextStyle = TextStyle(color = colorResource(R.color.modal_top_gray)),
    onCLick: (String)->Unit
){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.item_dark_gray), RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        rows.onEach { row->
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.clickable { onCLick(row.second) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image( modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .aspectRatio(1f)
                    .fillMaxSize(),
                    painter = painterResource(row.first),
                    contentDescription = null,
                    contentScale = ContentScale.Crop)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = row.second)
                    Spacer(Modifier.height(4.dp))
                    Text(text = row.third, style = thirdTextTextStyle)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(Modifier.height(4.dp))
            Line()
        }
    }
}

@Composable
fun ButtonsRow(
    onPlugClick:()->Unit,
    onPlanetClick:(String)->Unit
) {
    val buttons = listOf(
        Pair(R.drawable.ic_hard, "Сложный\nмаршрут"),
        Pair(R.drawable.ic_planet, "Куда угодно"),
        Pair(R.drawable.ic_calendar, "Выходные"),
        Pair(R.drawable.ic_fire, "Горящие\nбилеты")
    )

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        buttons.forEach { button ->
            Column(
                modifier = Modifier.clickable {
                    if(button.second=="Куда угодно"){
                        onPlanetClick("Куда угодно")
                    }else{
                        onPlugClick()
                    }
                },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(button.first),
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = button.second,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
fun SearchItemTickets(
    departureCity: String,
    arrivedCity: String ,
    onDepartureCityChanged: (String)->Unit = {},
    onArriveCityChanged: (String)->Unit = {},
    onSearchClicked:()->Unit
){

    Column(modifier = Modifier.fillMaxWidth()
        .background(colorResource(R.color.item_dark_gray), RoundedCornerShape(16.dp))
        .padding(horizontal = 12.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier.size(20.dp).align(Alignment.CenterVertically),
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
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier
                        .size(9.dp)
                        .clickable { onDepartureCityChanged("") }
                        .align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = null,
                    tint = colorResource(R.color.light_gray),
                )
            }
        }
        Line()

        Row(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier.clickable{
                    if(departureCity.isNotBlank()&& arrivedCity.isNotBlank()){
                        onSearchClicked()
                    }
                }.size(20.dp).align(Alignment.CenterVertically),
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
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier
                        .size(9.dp)
                        .clickable { onArriveCityChanged("") }
                        .align(Alignment.CenterVertically),
                    painter = painterResource(R.drawable.ic_cross),
                    contentDescription = null,
                    tint = colorResource(R.color.light_gray),
                )
            }
        }

    }
}

@Composable
fun Line(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(colorResource(R.color.item_light_gray))
    )
}

